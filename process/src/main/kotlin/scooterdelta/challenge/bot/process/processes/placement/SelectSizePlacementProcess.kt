package scooterdelta.challenge.bot.process.processes.placement

import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.process.processes.Process

class SelectSizePlacementProcess(private val placementProcesses: Map<Int, Process>,
                                 private val defaultProcess: Process) : Process {

    private val LOGGER = LoggerFactory.getLogger(SelectSizePlacementProcess::class.java)

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {
        if (placementProcesses.containsKey(gameState.mapDimension)) {
            LOGGER.info("Using Placement Process: {}, for map Dimension: {}",
                    placementProcesses[gameState.mapDimension]!!::class.toString(), gameState.mapDimension)
            placementProcesses[gameState.mapDimension]!!.process(gameState, processOutcomes)
        } else {
            LOGGER.info("Using *DEFAULT* Placement Process: {}, for map Dimension: {}",
                    defaultProcess::class.toString(), gameState.mapDimension)
            defaultProcess.process(gameState, processOutcomes)
        }
    }
}
