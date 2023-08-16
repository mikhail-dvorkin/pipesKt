import java.io.File

fun main() {
	val fileName = "redirector"
	val inputFileName = "$fileName.out"
	val outputFileName = "$fileName.in"
	val input = File(getPipePrefix() + outputFileName).bufferedReader()
	val output = File(getPipePrefix() + inputFileName).printWriter()
	val a = input.readLine()
	val b = input.readLine()
	output.println(a + b)
	output.close()
}
