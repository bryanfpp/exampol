#!/bin/bash
set -e
echo "🧪 Ejecutando tests y generando summary..."

# Crear carpetas necesarias
mkdir -p bin
mkdir -p testbin
mkdir -p reports

# Compilar código fuente
echo "Compilando src/*.java..."
javac -d bin src/*.java

# Compilar tests
if ls tests/*.java >/dev/null 2>&1; then
    echo "Compilando tests/*.java..."
    javac -cp "bin:lib/junit-platform-console-standalone-1.9.3.jar" -d testbin tests/*.java
else
    echo "⚠️ No hay archivos de test en tests/"
    exit 0
fi

# Inicializar resumen
SUMMARY_FILE="reports/test_summary.html"
echo "" > "$SUMMARY_FILE"

# Ejecutar tests uno por uno
for TESTFILE in tests/*.java; do
    TESTNAME=$(basename "$TESTFILE" .java)
    echo "▶️ Ejecutando $TESTNAME ..."
    
    OUTPUT=$(java -cp "bin:testbin:lib/junit-platform-console-standalone-1.9.3.jar" org.junit.runner.JUnitCore "$TESTNAME" 2>&1)
    
    # Detectar si falló
    if echo "$OUTPUT" | grep -q "FAILURES!!!"; then
        echo "❌ $TESTNAME (0/?)" >> "$SUMMARY_FILE"
    else
        # Contar tests ejecutados y exitosos usando JUnit output
        TOTAL=$(echo "$OUTPUT" | grep -oP "\d+(?=\s+tests)" | head -1)
        PASSED=$(echo "$OUTPUT" | grep -oP "\d+(?=\s+successful)" | head -1)
        TOTAL=${TOTAL:-?}
        PASSED=${PASSED:-?}
        echo "✅ $TESTNAME ($PASSED/$TOTAL)" >> "$SUMMARY_FILE"
    fi
done

echo "✅ test_summary.html generado correctamente en reports/"
