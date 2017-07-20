package scooterdelta.challenge.bot.process.processes.attack.special

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.lookup.Code.FIRE_SHOT
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.process.processes.ProbabilityCalculator

abstract class AbstractProbabilityCalculator : ProbabilityCalculator {

    override fun calculateProbability(cell: OpponentCell, map: Map<OpponentCell>, gameState: GameState) {
        val targetCells: List<OpponentCell> = getTargetCells(cell, map)
        var probability: Long = 0

        targetCells
                .mapNotNull { cell.attackTypeProbability[FIRE_SHOT] }
                .forEach { probability += it }

        cell.attackTypeProbability[getCode()] = probability
    }

    protected abstract fun getTargetCells(cell: OpponentCell, map: Map<OpponentCell>): List<OpponentCell>
    protected abstract fun getCode(): Code
}