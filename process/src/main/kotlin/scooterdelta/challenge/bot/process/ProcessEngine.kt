package scooterdelta.challenge.bot.process

import com.google.common.io.Files
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.command.Command
import scooterdelta.challenge.bot.common.command.NoActionCommand
import scooterdelta.challenge.bot.common.lookup.StateLookup
import scooterdelta.challenge.bot.common.state.local.FileState
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.process.converter.GameStateDeserializer
import scooterdelta.challenge.bot.process.processes.Process
import java.io.File
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Named

class ProcessEngine @Inject constructor(private val fileState: FileState,
                                        private val deserializer: GameStateDeserializer,
                                        @Named("placementProcesses") private val placementProcesses: ArrayList<Process>,
                                        @Named("attackProcesses") private val attackProcesses: ArrayList<Process>) : Runnable {

    val LOGGER: Logger = LoggerFactory.getLogger(ProcessEngine::class.java)

    override fun run() {
        LOGGER.info(
                "\n\n\n------------------------------------------\n" +
                "                BOT STARTING                \n" +
                "------------------------------------------\n\n\n")
        try {
            val gameState: GameState = deserializer.deserialize(fileState.workingDirectory)
            val processOutcomes: ProcessOutcomes = ProcessOutcomes(NoActionCommand(), StateLookup.COMMAND)

            LOGGER.info("Player Map:\n${gameState.playerMap.map.printMap()}")
            LOGGER.info("Opponent Map:\n${gameState.opponentMap.map.printMap()}")

            when (gameState.phase) {
                1 -> {
                    callPlacementProcess(gameState, processOutcomes)
                }
                else -> {
                    callAttackProcess(gameState, processOutcomes)
                }
            }

            printCommand(processOutcomes)

        } catch (ex: IOException) {
            LOGGER.error("Error extracting state object:", ex)
        } catch (ex: Exception) {
            LOGGER.error("Unknown Processing error", ex)
        }
    }

    private fun callPlacementProcess(gameState: GameState, processOutcomes: ProcessOutcomes) {
        LOGGER.info("Calling placement process chain!")
        for (placementProcess in placementProcesses) {
            placementProcess.process(gameState, processOutcomes)
        }
    }

    private fun callAttackProcess(gameState: GameState, processOutcomes: ProcessOutcomes) {
        LOGGER.info("Calling attack process chain!")
        for (attackProcess in attackProcesses) {
            attackProcess.process(gameState, processOutcomes)
        }
    }

    private fun printCommand(processOutcomes: ProcessOutcomes) {
        val file: File = File(fileState.workingDirectory, processOutcomes.stateLookup.location)
        Files.asCharSink(file, Charset.defaultCharset()).write(processOutcomes.command.printCommand())
    }
}
