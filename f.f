          perl -CS -0777 -i -pe 'BEGIN{local $/; open(F,"reports/test_summary.html") or die $!; $x=<F>} s/\{\{TEST_CLASS_SUMMARY\}\}/$x/ge' reporte.html
