#!/bin/bash

mkdir -p reports
> reports/test_summary.html   # borrar resumen anterior

for TESTFILE in tests/*.java; do
  TESTNAME=$(basename "$TESTFILE" .java)
  
  # Ejecutar test individual con JUnit
  OUTPUT=$(java -cp "lib/*:bin:testbin" org.junit.runner.JUnitCore "$TESTNAME" 2>&1)
  
  TOTAL=$(echo "$OUTPUT" | grep -oP "Tests run: \K\d+")
  FAILS=$(echo "$OUTPUT" | grep -oP "Failures: \K\d+")
  PASSED=$((TOTAL-FAILS))
  
  if [ "$FAILS" -eq 0 ]; then
    echo "✅ $TESTNAME ($PASSED/$TOTAL)" >> reports/test_summary.html
  else
    echo "❌ $TESTNAME ($PASSED/$TOTAL)" >> reports/test_summary.html
  fi
done

echo "✅ Resumen generado en reports/test_summary.html"
