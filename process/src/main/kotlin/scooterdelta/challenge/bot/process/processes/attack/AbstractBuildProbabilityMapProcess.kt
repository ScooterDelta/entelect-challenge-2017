package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.state.local.GameMode
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.process.processes.ProbabilityCalculator
import scooterdelta.challenge.bot.process.processes.Process

abstract class AbstractBuildProbabilityMapProcess : Process, ProbabilityCalculator {

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {

        val opponentMap: Map<OpponentCell> = gameState.opponentMap.map

        // Only apply probability map processes if the game mode is set to spend
        if (processOutcomes.gameMode == GameMode.SPEND) {
            opponentMap.cells
                    .parallelStream()
                    .forEach {
                        it
                                .parallelStream()
                                .forEach { calculateProbability(it, gameState, processOutcomes) }
                    }
        }
    }
}