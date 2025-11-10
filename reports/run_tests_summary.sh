#!/bin/bash
set -e
echo "🧪 Ejecutando tests y generando summary..."

mkdir -p bin testbin reports

# Compilar código fuente
find src -name "*.java" > sources.txt
javac -d bin @sources.txt

# Compilar tests
find tests -name "*.java" > test_sources.txt
javac -cp "bin:lib/junit-platform-console-standalone-1.9.3.jar" -d testbin @test_sources.txt || true

# Ejecutar todos los tests y guardar salida
TEST_OUTPUT=$(java -jar lib/junit-platform-console-standalone-1.9.3.jar \
    --class-path "bin:testbin" \
    --scan-class-path \
    --details=none 2>&1)

echo "$TEST_OUTPUT" > reports/test_output.txt

# Extraer resumen final de la salida
TOTAL=$(echo "$TEST_OUTPUT" | grep -oP 'tests found\s+\K\d+' | tail -1)
PASSED=$(echo "$TEST_OUTPUT" | grep -oP 'tests successful\s+\K\d+' | tail -1)
FAILED=$((TOTAL - PASSED))

# Crear test_summary.html
SUMMARY_FILE="reports/test_summary.html"
echo "" > "$SUMMARY_FILE"

# Extraer por clase
echo "$TEST_OUTPUT" | grep -oP '▶️ Ejecutando \K.*' | while read CLASS; do
    # Buscar si tiene failure
    FAILED_COUNT=$(echo "$TEST_OUTPUT" | grep -A5 "▶️ Ejecutando $CLASS" | grep -c '✘')
    TOTAL_COUNT=$(echo "$TEST_OUTPUT" | grep -A5 "▶️ Ejecutando $CLASS" | grep -c '✔\|✘')
    PASSED_COUNT=$((TOTAL_COUNT - FAILED_COUNT))
    if [ "$FAILED_COUNT" -eq 0 ]; then
        STATUS="✅"
    else
        STATUS="❌"
    fi
    echo "${STATUS} ${CLASS} (${PASSED_COUNT}/${TOTAL_COUNT})<br>" >> "$SUMMARY_FILE"
done

echo "✅ test_summary.html generado en $SUMMARY_FILE"
echo "Total: $TOTAL, Passed: $PASSED, Failed: $FAILED"
