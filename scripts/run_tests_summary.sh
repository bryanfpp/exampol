#!/bin/bash
echo "🧪 Ejecutando tests..."

mkdir -p bin
javac -d bin -cp "lib/*" src/*.java tests/*.java

# Inicializar resumen
SUMMARY=""
FAILED_TOTAL=0
PASSED_TOTAL=0

# Ejecutar cada test de la carpeta
for TESTFILE in tests/*.java; do
  TESTNAME=$(basename "$TESTFILE" .java)
  
  OUTPUT=$(java -cp "lib/*:bin" org.junit.runner.JUnitCore "$TESTNAME" 2>&1)
  TOTAL_TESTS=$(echo "$OUTPUT" | grep -oP "Tests run: \K\d+" | head -1)
  FAILS=$(echo "$OUTPUT" | grep -oP "Failures: \K\d+" | head -1)

  PASSED=$((TOTAL_TESTS - FAILS))
  
  if [ "$FAILS" -eq 0 ]; then
    SUMMARY+="✅ $TESTNAME ($PASSED/$TOTAL_TESTS)<br>"
  else
    SUMMARY+="❌ $TESTNAME ($PASSED/$TOTAL_TESTS)<br>"
  fi
done

# Guardar summary en archivo
mkdir -p reports
echo "$SUMMARY" > reports/test_summary.html
echo "✅ Resumen de tests generado:"
cat reports/test_summary.html
