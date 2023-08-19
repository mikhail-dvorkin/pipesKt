import kotlinx.coroutines.*
import java.io.*
import kotlin.system.exitProcess

suspend fun main(args: Array<String>) {
	if ("--help" in args) return println("This is redirector")
	val isShowcase = "--showcase" in args
	if (isShowcase) println("This is redirector, ran in showcase mode.")
	fun exit() {
		if (isShowcase) {
			println("Redirector finished successfully. Will exit in 3s.")
			Thread.sleep(3_000)
		}
		exitProcess(0)
	}
	val fileName = "redirector"
	val inputFileName = "$fileName.out"
	val outputFileName = "$fileName.in"
	var input: BufferedReader? = null
	var output: PrintWriter? = null
	try {
		withContext(Dispatchers.IO) {
			var outputThread: Job? = null
			val inputThread = launch { runInterruptible {
				val inputPipe = createInputPipe(inputFileName)
				input = inputPipe.bufferedReader()
				while (true) {
					val s = input!!.readLine() ?: break
					println(s)
				}
				System.`in`.close()
				outputThread!!.cancel()
				exit() // dirty fix
			} }
			outputThread = launch { runInterruptible {
				val outputPipe = createOutputPipe(outputFileName)
				output = PrintWriter(outputPipe, true)
				while (true) {
					val s = readLine() ?: break
					output!!.println(s)
					output!!.flush()
				}
				inputThread.cancel()
				exit() // dirty fix
			} }
		}
	} catch (e: Exception) {
		cleanupPipeOnCancel(inputFileName)
		cleanupPipeOnCancel(outputFileName)
	} finally {
		withContext(Dispatchers.IO) {
			input?.close()
			output?.close()
		}
	}
}
