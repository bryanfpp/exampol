#!/bin/bash
set +e
echo "🧪 Ejecutando tests y generando summary..."

# Crear carpetas
mkdir -p bin testbin reports reports/junit

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

# Ejecutar tests y generar reportes XML
java -jar lib/junit-platform-console-standalone-1.9.3.jar \
    --class-path "bin:testbin" \
    --scan-class-path \
    --reports-dir reports/junit \
    --details=none > reports/test_output.txt 2>&1

# Crear o limpiar el summary HTML
SUMMARY_FILE="reports/test_summary.html"
echo "" > "$SUMMARY_FILE"

# Parsear archivos XML generados
for xml in reports/junit/*.xml; do
    [ -f "$xml" ] || continue

    # Nombre de la clase de test
    CLASS=$(xmllint --xpath 'string(//testsuite/@name)' "$xml")
    # Total de tests y fallidos
    TOTAL=$(xmllint --xpath 'string(//testsuite/@tests)' "$xml")
    FAILED=$(xmllint --xpath 'string(//testsuite/@failures)' "$xml")
    PASSED=$((TOTAL - FAILED))

    STATUS="✅"
    if [ "$FAILED" -gt 0 ]; then
        STATUS="❌"
    fi

    # Escribir en HTML
    echo "${STATUS} ${CLASS} (${PASSED}/${TOTAL})<br>" >> "$SUMMARY_FILE"
done

echo "✅ test_summary.html generado en $SUMMARY_FILE"
