package scooterdelta.challenge.bot.process.processes

import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell

interface ProbabilityCalculator {

    fun calculateProbability(cell: OpponentCell, map: Map<OpponentCell>, gameState: GameState)

}