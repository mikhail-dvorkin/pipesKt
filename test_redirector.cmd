call gradlew build
start java -cp build\libs\visualizer-1.0.0-all.jar RedirectorKt --showcase
echo Redirector started. Input two lines there, it should receive their concatenation from A+B program.
pause
start java -cp build\libs\visualizer-1.0.0-all.jar Sample_aplusbKt
