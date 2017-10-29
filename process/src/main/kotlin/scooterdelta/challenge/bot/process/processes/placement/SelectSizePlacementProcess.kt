package scooterdelta.challenge.bot.process.processes.placement

import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.process.processes.Process

class SelectSizePlacementProcess(private val placementProcesses: Map<Int, Process>,
                                 private val defaultProcess: Process) : Process {

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {
        if (placementProcesses.containsKey(gameState.mapDimension)) {
            placementProcesses[gameState.mapDimension]!!.process(gameState, processOutcomes)
        } else {
            defaultProcess.process(gameState, processOutcomes)
        }
    }
}
