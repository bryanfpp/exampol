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
    # Convertir ruta a nombre de clase completo (con paquete si existe)
    TESTNAME=$(echo "$TESTFILE" | sed 's|^tests/||; s|/|.|g; s|\.java$||')

    echo "▶️ Ejecutando $TESTNAME ..."

    # Ejecutar test con JUnit 5 ConsoleLauncher
    OUTPUT=$(java -cp "bin:testbin:lib/junit-platform-console-standalone-1.9.3.jar" \
        org.junit.platform.console.ConsoleLauncher -c "$TESTNAME" 2>&1 || true)

    # Extraer totales de la salida
    TOTAL_TESTS=$(echo "$OUTPUT" | grep -oP 'Tests found: \K\d+' | head -1)
    PASSED_TESTS=$(echo "$OUTPUT" | grep -oP 'Tests succeeded: \K\d+' | head -1)
    FAILED_TESTS=$(echo "$OUTPUT" | grep -oP 'Tests failed: \K\d+' | head -1)

    TOTAL_TESTS=${TOTAL_TESTS:-0}
    PASSED_TESTS=${PASSED_TESTS:-0}
    FAILED_TESTS=${FAILED_TESTS:-0}

    if [ "$FAILED_TESTS" -eq 0 ]; then
        STATUS_EMOJI="✅"
    else
        STATUS_EMOJI="❌"
    fi

    # Escribir línea en test_summary.html
    echo "${STATUS_EMOJI} ${TESTNAME} (${PASSED_TESTS}/${TOTAL_TESTS})<br>" >> "$SUMMARY_FILE"
done

echo "✅ test_summary.html generado en $SUMMARY_FILE"
