package scooterdelta.challenge.bot.process

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.state.local.FileState
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.process.converter.GameStateDeserializer
import java.io.IOException
import javax.inject.Inject

class ProcessEngine @Inject constructor(private val fileState: FileState,
                                        private val deserializer: GameStateDeserializer) : Runnable {

    val LOGGER: Logger = LoggerFactory.getLogger(ProcessEngine::class.java)

    override fun run() {
        LOGGER.debug("Kotlin Injection is working! Got these parameters: {} and {}", fileState.playerKey, fileState.workingDirectory)

        try {
            val gameState: GameState = deserializer.deserialize(fileState.workingDirectory)
            LOGGER.info("Deserialized game state: {}", gameState)
            LOGGER.info("Player Map:\n${gameState.playerMap.map.printMap()}")
            LOGGER.info("Opponent Map:\n${gameState.opponentMap.map.printMap()}")
        } catch (ex: IOException) {
            LOGGER.error("Error extracting state object: {}", ex)
        }
    }
}