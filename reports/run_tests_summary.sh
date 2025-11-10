#!/bin/bash
set +e
echo "🧪 Ejecutando tests y generando summary simplificado..."

# Crear carpetas si no existen
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

# Ejecutar tests con JUnit 5
java -jar lib/junit-platform-console-standalone-1.9.3.jar \
    --class-path "bin:testbin" \
    --scan-class-path \
    --details=none > reports/test_output.txt 2>&1

# Crear o limpiar test_summary.html
SUMMARY_FILE="reports/test_summary.html"
echo "" > "$SUMMARY_FILE"

# Listar clases de test y mostrar solo ✅/❌ sin totales
for TESTFILE in $(find tests -name "*.java"); do
    TESTNAME=$(basename "$TESTFILE" .java)

    # Verificar si hay fallo en el output
    if grep -q "Failures.*$TESTNAME" reports/test_output.txt; then
        STATUS="❌"
    else
        STATUS="✅"
    fi

    # Escribir en HTML sin totales
    echo "${STATUS} ${TESTNAME}<br>" >> "$SUMMARY_FILE"
done

echo "✅ test_summary.html generado en $SUMMARY_FILE"
