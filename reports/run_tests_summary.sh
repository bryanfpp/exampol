#!/bin/bash
set -e
echo "🧪 Ejecutando tests y generando summary..."

# Crear carpetas necesarias
mkdir -p bin testbin reports/junit

# Compilar src/*.java
echo "Compilando src/*.java..."
find src -name "*.java" > sources.txt
javac -d bin @sources.txt

# Compilar tests/*.java
echo "Compilando tests/*.java..."
if ls tests/*.java >/dev/null 2>&1; then
    find tests -name "*.java" > test_sources.txt
    javac -cp "bin:lib/junit-platform-console-standalone-1.9.3.jar" -d testbin @test_sources.txt || true
else
    echo "⚠️ No hay archivos de test en tests/"
fi

# Ejecutar todos los tests de JUnit 5 y generar XML
java -jar lib/junit-platform-console-standalone-1.9.3.jar \
    --class-path "bin:testbin" \
    --scan-class-path \
    --reports-dir "reports/junit" \
    --details=none

# Crear test_summary.html vacío
SUMMARY_FILE="reports/test_summary.html"
echo "" > "$SUMMARY_FILE"

# Leer cada XML generado
for XML in reports/junit/TEST-*.xml; do
    CLASS_NAME=$(basename "$XML" .xml | sed 's/^TEST-//')
    TOTAL=$(grep -oP 'tests="\K\d+' "$XML" | head -1)
    FAILED=$(grep -oP 'failures="\K\d+' "$XML" | head -1)
    TOTAL=${TOTAL:-0}
    FAILED=${FAILED:-0}
    PASSED=$((TOTAL - FAILED))

    if [ "$FAILED" -eq 0 ]; then
        STATUS_EMOJI="✅"
    else
        STATUS_EMOJI="❌"
    fi

    echo "${STATUS_EMOJI} ${CLASS_NAME} (${PASSED}/${TOTAL})<br>" >> "$SUMMARY_FILE"
done

echo "✅ test_summary.html generado en $SUMMARY_FILE"
