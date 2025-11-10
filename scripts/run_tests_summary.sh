#!/bin/bash
set -e
echo "🧪 Ejecutando tests y generando summary..."

# Crear carpetas necesarias
mkdir -p bin testbin reports/junit

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

# Ejecutar cada test individualmente
for TESTFILE in $(find tests -name "*.java"); do
    # Obtener paquete de la clase
    PACKAGE=$(grep -oP '^package \K[^;]+' "$TESTFILE" || true)
    CLASS_SIMPLE=$(basename "$TESTFILE" .java)
    if [ -n "$PACKAGE" ]; then
        CLASS_NAME="$PACKAGE.$CLASS_SIMPLE"
    else
        CLASS_NAME="$CLASS_SIMPLE"
    fi

    echo "▶️ Ejecutando $CLASS_NAME ..."

    # Ejecutar test y generar XML por clase
    java -jar lib/junit-platform-console-standalone-1.9.3.jar \
        --class-path "bin:testbin" \
        --select-class "$CLASS_NAME" \
        --reports-dir "reports/junit" \
        --details=none \
        2>&1 | tee reports/test_output.txt

    # Buscar XML generado para esta clase
    XMLFILE=$(ls reports/junit/*"$CLASS_SIMPLE"*.xml 2>/dev/null | head -1)

    if [ ! -f "$XMLFILE" ]; then
        echo "❌ $CLASS_SIMPLE (0/0)<br>" >> "$SUMMARY_FILE"
        continue
    fi

    # Extraer totales y fallidos
    TOTAL=$(grep -oP 'tests="\K\d+' "$XMLFILE" | head -1)
    FAILED=$(grep -oP 'failures="\K\d+' "$XMLFILE" | head -1)
    PASSED=$((TOTAL - FAILED))

    if [ "$FAILED" -eq 0 ]; then
        STATUS_EMOJI="✅"
    else
        STATUS_EMOJI="❌"
    fi

    echo "${STATUS_EMOJI} ${CLASS_SIMPLE} (${PASSED}/${TOTAL})<br>" >> "$SUMMARY_FILE"
done

echo "✅ test_summary.html generado en $SUMMARY_FILE"
