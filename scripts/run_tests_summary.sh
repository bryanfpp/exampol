#!/bin/bash
set -e

echo "🧪 Ejecutando tests y generando summary..."

# Crear carpetas necesarias
mkdir -p reports
mkdir -p testbin

# Compilar tests si existen
if ls tests/*.java >/dev/null 2>&1; then
    javac -cp "lib/junit-platform-console-standalone-1.9.3.jar:bin" -d testbin tests/*.java
else
    echo "⚠️ No hay archivos de test en tests/"
    exit 0
fi

# Limpiar archivo de summary
> reports/test_summary.html

# Ejecutar cada test individualmente y generar el resumen
for classfile in testbin/*.class; do
    classname=$(basename "$classfile" .class)
    
    # Ejecutar la clase de test
    output=$(java -cp "bin:testbin:lib/junit-platform-console-standalone-1.9.3.jar" org.junit.runner.JUnitCore "$classname" 2>&1)

    # Contar total de tests y fallos
    total=$(echo "$output" | grep -oP 'Tests run: \K\d+')
    failures=$(echo "$output" | grep -oP 'Failures: \K\d+')
    passed=$((total - failures))

    # Marcar ✅ o ❌ según resultados
    if [ "$failures" -eq 0 ]; then
        echo "✅ $classname ($passed/$total)" >> reports/test_summary.html
    else
        echo "❌ $classname ($passed/$total)" >> reports/test_summary.html
    fi
done

echo "✅ Summary generado en reports/test_summary.html"
