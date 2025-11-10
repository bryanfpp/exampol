#!/bin/bash
set -e
echo "🧪 Ejecutando tests y generando summary..."

# Crear carpetas si no existen
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

# Crear o limpiar test_summary.html
SUMMARY_FILE="reports/test_summary.html"
echo "" > "$SUMMARY_FILE"

# Ejecutar todos los tests y generar XML
java -jar lib/junit-platform-console-standalone-1.9.3.jar \
    --class-path "bin:testbin" \
    --scan-class-path \
    --reports-dir reports/junit \
    2>&1 | tee reports/test_output.txt

# Parsear cada XML para generar resumen por clase
for XMLFILE in reports/junit/*.xml; do
    CLASS_NAME=$(basename "$XMLFILE" .xml)
    
    # Extraer totales y fallidos del XML
    TOTAL=$(grep -oP 'tests="\K\d+' "$XMLFILE" | head -1)
    FAILED=$(grep -oP 'failures="\K\d+' "$XMLFILE" | head -1)
    PASSED=$((TOTAL - FAILED))

    if [ "$FAILED" -eq 0 ]; then
        STATUS_EMOJI="✅"
    else
        STATUS_EMOJI="❌"
    fi

    # Escribir línea en test_summary.html
    echo "${STATUS_EMOJI} ${CLASS_NAME} (${PASSED}/${TOTAL})<br>" >> "$SUMMARY_FILE"
done

echo "✅ test_summary.html generado en $SUMMARY_FILE"
