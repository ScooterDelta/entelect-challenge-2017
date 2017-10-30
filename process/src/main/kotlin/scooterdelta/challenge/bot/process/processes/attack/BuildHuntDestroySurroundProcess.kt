package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.process.processes.ProbabilityCalculator
import scooterdelta.challenge.bot.process.processes.Process

class BuildHuntDestroySurroundProcess : Process, ProbabilityCalculator {

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {

        val opponentMap: Map<OpponentCell> = gameState.opponentMap.map

        // Only apply probability map processes if the game mode is set to spend
        opponentMap.cells
                .parallelStream()
                .forEach {
                    it
                            .parallelStream()
                            .forEach { calculateProbability(it, gameState, processOutcomes) }
                }
    }

    override fun calculateProbability(cell: OpponentCell, gameState: GameState, processOutcomes: ProcessOutcomes) {

        if (!cell.missed && !cell.damaged) {
            val map: Map<OpponentCell> = gameState.opponentMap.map

            val subCells: List<OpponentCell> = map.findNAdjacentCells(cell, 1)
            val damaged: List<OpponentCell> = findAdjacentDamagedCells(cell, subCells)

            if (!damaged.isEmpty() &&
                    (cell.attackTypeProbability[Code.FIRE_SHOT] == null ||
                            cell.attackTypeProbability[Code.FIRE_SHOT]!! <= cell.basicProbability)) {
                cell.attackTypeProbability[Code.FIRE_SHOT] = cell.basicProbability
            }
        }
    }

    private fun findAdjacentDamagedCells(cell: OpponentCell, subCells: List<OpponentCell>): List<OpponentCell> {
        return subCells.filter { it.damaged }
                .filter { it.getPoint() != cell.getPoint() }
                .filter { it.x == cell.x || it.y == cell.y }
                .map { it }
    }
}
