package scooterdelta.challenge.bot.process.processes

import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell

interface ProbabilityCalculator {

    fun calculateProbability(cell: OpponentCell, gameState: GameState, processOutcomes: ProcessOutcomes)
}