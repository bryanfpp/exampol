#!/bin/bash
set +e
echo "🧪 Ejecutando tests y generando summary..."

mkdir -p bin testbin reports

# Compilar código fuente
find src -name "*.java" > sources.txt
javac -d bin @sources.txt || { echo "Error compilando src"; exit 1; }

# Compilar tests
if ls tests/*.java >/dev/null 2>&1; then
    find tests -name "*.java" > test_sources.txt
    javac -cp "bin:lib/junit-platform-console-standalone-1.9.3.jar" -d testbin @test_sources.txt || echo "Error compilando tests"
else
    echo "⚠️ No hay archivos de test en tests/"
fi

# Ejecutar todos los tests con detalles en tree
TEST_OUTPUT=$(java -jar lib/junit-platform-console-standalone-1.9.3.jar \
    --class-path "bin:testbin" \
    --scan-class-path \
    --details=tree 2>&1)

echo "$TEST_OUTPUT" > reports/test_output.txt

# Crear test_summary.html vacío
SUMMARY_FILE="reports/test_summary.html"
echo "" > "$SUMMARY_FILE"

# Parsear por clase y test
# Cada línea con ✔ o ✘ representa un test
# Cada línea sin indentación representa una clase

CURRENT_CLASS=""
while IFS= read -r line; do
    if [[ "$line" =~ ^[A-Za-z].* ]]; then
        CURRENT_CLASS="$line"
        TOTAL_COUNT=0
        FAILED_COUNT=0
    fi
    if [[ "$line" =~ "✔" ]]; then
        TOTAL_COUNT=$((TOTAL_COUNT + 1))
    fi
    if [[ "$line" =~ "✘" ]]; then
        TOTAL_COUNT=$((TOTAL_COUNT + 1))
        FAILED_COUNT=$((FAILED_COUNT + 1))
    fi
    # Cuando termina la clase o llega a la última línea
    if [[ "$line" == "" && "$CURRENT_CLASS" != "" ]]; then
        PASSED_COUNT=$((TOTAL_COUNT - FAILED_COUNT))
        STATUS="✅"
        if [ "$FAILED_COUNT" -gt 0 ]; then
            STATUS="❌"
        fi
        echo "${STATUS} ${CURRENT_CLASS} (${PASSED_COUNT}/${TOTAL_COUNT})<br>" >> "$SUMMARY_FILE"
        CURRENT_CLASS=""
    fi
done <<< "$TEST_OUTPUT"

echo "✅ test_summary.html generado en $SUMMARY_FILE"
