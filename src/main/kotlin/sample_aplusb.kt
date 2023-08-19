import java.io.File

fun main() {
	val fileName = "redirector"
	val inputFileName = "$fileName.in"
	val outputFileName = "$fileName.out"
	val input = File(getPipePrefix() + inputFileName).bufferedReader()
	val output = File(getPipePrefix() + outputFileName).printWriter()
	val a = input.readLine()
	val b = input.readLine()
	output.println(a + b)
	output.close()
}
