#!/bin/bash
set +e
echo "🧪 Ejecutando tests y generando summary..."

# Crear carpetas necesarias
mkdir -p bin testbin reports

# Compilar src/*.java
echo "Compilando src/*.java..."
find src -name "*.java" > sources.txt
javac -d bin @sources.txt || { echo "Error compilando src"; exit 1; }

# Compilar tests/*.java
echo "Compilando tests/*.java..."
if ls tests/*.java >/dev/null 2>&1; then
    find tests -name "*.java" > test_sources.txt
    javac -cp "bin:lib/junit-platform-console-standalone-1.9.3.jar" -d testbin @test_sources.txt || echo "Error compilando tests"
else
    echo "⚠️ No hay archivos de test en tests/"
fi

# Ejecutar todos los tests de JUnit 5 y guardar salida
TEST_OUTPUT=$(java -jar lib/junit-platform-console-standalone-1.9.3.jar \
    --class-path "bin:testbin" \
    --scan-class-path \
    --details=none 2>&1)

echo "$TEST_OUTPUT" > reports/test_output.txt

# Crear test_summary.html vacío
SUMMARY_FILE="reports/test_summary.html"
echo "" > "$SUMMARY_FILE"

# Extraer resumen por clase
CLASSES=$(echo "$TEST_OUTPUT" | grep -oP '▶️ Ejecutando \K.*')
for CLASS in $CLASSES; do
    # Contar tests pasados/fallidos en la sección de esta clase
    SECTION=$(echo "$TEST_OUTPUT" | awk "/▶️ Ejecutando $CLASS/,/▶️ Ejecutando/" | head -n -1)
    TOTAL_COUNT=$(echo "$SECTION" | grep -c '✔\|✘')
    FAILED_COUNT=$(echo "$SECTION" | grep -c '✘')
    PASSED_COUNT=$((TOTAL_COUNT - FAILED_COUNT))

    if [ "$FAILED_COUNT" -eq 0 ]; then
        STATUS="✅"
    else
        STATUS="❌"
    fi

    echo "${STATUS} ${CLASS} (${PASSED_COUNT}/${TOTAL_COUNT})<br>" >> "$SUMMARY_FILE"
done

# Información final en consola
TOTAL=$(echo "$TEST_OUTPUT" | grep -oP 'tests found\s+\K\d+' | tail -1)
PASSED=$(echo "$TEST_OUTPUT" | grep -oP 'tests successful\s+\K\d+' | tail -1)
FAILED=$((TOTAL - PASSED))

echo "✅ test_summary.html generado en $SUMMARY_FILE"
echo "Total: $TOTAL, Passed: $PASSED, Failed: $FAILED"
