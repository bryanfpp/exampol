for TESTFILE in $(find tests -name "*.java"); do
    TESTNAME=$(basename "$TESTFILE" .java)
    echo "▶️ Ejecutando $TESTNAME ..."

    OUTPUT=$(java -cp "bin:testbin:lib/junit-platform-console-standalone-1.9.3.jar" org.junit.runner.JUnitCore "$TESTNAME" 2>&1 || true)


    # Extraer totales
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

    echo "${STATUS_EMOJI} ${TESTNAME} (${PASSED_TESTS}/${TOTAL_TESTS})<br>" >> "$SUMMARY_FILE"
done
