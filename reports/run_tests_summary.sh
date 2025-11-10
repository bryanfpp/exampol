#!/bin/bash
set -e
echo "🧪 Ejecutando tests y generando summary..."

# Crear carpetas si no existen
mkdir -p bin testbin reports

# Compilar src/*.java incluyendo subcarpetas
echo "Compilando src/*.java..."
find src -name "*.java" > sources.txt
javac -d bin @sources.txt

# Compilar tests/*.java incluyendo subcarpetas
echo "Compilando tests/*.java..."
if ls tests/*.java >/dev/null 2>&1; then
    find tests -name "*.java" > test_sources.txt
    javac -cp "bin:lib/junit-platform-console-standalone-1.9.3.jar" -d testbin @test_sources.txt || true
else
    echo "⚠️ No hay archivos de test en tests/"
fi

# Crear o limpiar test_summary.html
SUMMARY_FILE="reports/test_summary.html"
echo "" > "$SUMMARY_FILE"

# Ejecutar cada test de la carpeta tests
for TESTFILE in $(find tests -name "*.java"); do
    TESTNAME=$(basename "$TESTFILE" .java)
    echo "▶️ Ejecutando $TESTNAME ..."
    OUTPUT=$(java -cp "bin:testbin:lib/junit-platform-console-standalone-1.9.3.jar" org.junit.runner.JUnitCore "$TESTNAME" 2>&1 || true)

    # Contar pruebas ejecutadas y fallidas
    TOTAL_TESTS=$(echo "$OUTPUT" | grep -oP '\d+(?= test(s)? run)' | head -1)
    FAILED_TESTS=$(echo "$OUTPUT" | grep -oP '\d+(?= failure(s)?)' | head -1)
    PASSED_TESTS=$((TOTAL_TESTS - FAILED_TESTS))

    TOTAL_TESTS=${TOTAL_TESTS:-0}
    FAILED_TESTS=${FAILED_TESTS:-0}
    PASSED_TESTS=${PASSED_TESTS:-0}

    if [ "$FAILED_TESTS" -eq 0 ]; then
        STATUS_EMOJI="✅"
    else
        STATUS_EMOJI="❌"
    fi

    # Escribir línea en test_summary.html
    echo "${STATUS_EMOJI} ${TESTNAME} (${PASSED_TESTS}/${TOTAL_TESTS})<br>" >> "$SUMMARY_FILE"
done

echo "✅ test_summary.html generado en $SUMMARY_FILE"
