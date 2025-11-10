#!/bin/bash
set -e
echo "🧪 Ejecutando tests y generando summary..."

# Crear carpetas si no existen
mkdir -p bin testbin reports

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

# Ejecutar todos los tests usando scan-class-path
OUTPUT=$(java -cp "bin:testbin:lib/junit-platform-console-standalone-1.9.3.jar" \
    org.junit.platform.console.ConsoleLauncher \
    --class-path "testbin" \
    --scan-class-path \
    --details=summary \
    2>&1 || true)

# Extraer resultados por clase
# Cada línea de salida de summary de JUnit 5 tiene formato:
# TestConversorMoneda ✔
# TestEcuacionTrio ✘
echo "$OUTPUT" | grep -E '^[^ ]+ [✔✘]' | while read -r line; do
    CLASS_NAME=$(echo "$line" | awk '{print $1}')
    STATUS=$(echo "$line" | awk '{print $2}')

    # Contar tests totales, fallidos y pasados
    CLASS_OUTPUT=$(echo "$OUTPUT" | awk "/$CLASS_NAME/,/^$/")
    TOTAL=$(echo "$CLASS_OUTPUT" | grep -c '()')
    FAILED=$(echo "$CLASS_OUTPUT" | grep -c '✘')
    PASSED=$((TOTAL - FAILED))

    if [ "$STATUS" = "✔" ]; then
        STATUS_EMOJI="✅"
    else
        STATUS_EMOJI="❌"
    fi

    echo "${STATUS_EMOJI} ${CLASS_NAME} (${PASSED}/${TOTAL})<br>" >> "$SUMMARY_FILE"
done

echo "✅ test_summary.html generado en $SUMMARY_FILE"
