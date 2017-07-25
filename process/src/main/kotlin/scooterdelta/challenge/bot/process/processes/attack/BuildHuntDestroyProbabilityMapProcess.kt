package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell

class BuildHuntDestroyProbabilityMapProcess : AbstractBuildProbabilityMapProcess() {

    override fun calculateProbability(cell: OpponentCell, map: Map<OpponentCell>, gameState: GameState) {

        if (!cell.missed && !cell.damaged) {
            val subCells: List<OpponentCell> = map.findNAdjacentCells(cell, 1)
            val damaged: List<OpponentCell> = findAdjacentDamagedCells(cell, subCells)

            if (!damaged.isEmpty()) {
                if (checkDamagedCellsIsolated(damaged, map)) {
                    // Seek around isolated cell
                    cell.attackTypeProbability[Code.FIRE_SHOT] = cell.basicProbability * 3
                } else {
                    // Find adjacent cells
                    damaged
                            .mapNotNull { map.getCellInDirection(it, cell.determineDirection(it)) }
                            .filter { it.damaged }
                            .forEach { cell.attackTypeProbability[Code.FIRE_SHOT] = cell.basicProbability * 3 }
                }
            }
        }
    }

    private fun checkDamagedCellsIsolated(cells: List<OpponentCell>, map: Map<OpponentCell>): Boolean {
        return cells.any { checkDamagedCellIsolated(it, map) }
    }

    private fun checkDamagedCellIsolated(cell: OpponentCell, map: Map<OpponentCell>): Boolean {
        return map.findNAdjacentCells(cell, 1)
                .filter { it.getPoint() != cell.getPoint() }
                .filter { it.x == cell.x || it.y == cell.y }
                .filter { it.damaged }
                .map { it }
                .isEmpty()
    }

    private fun findAdjacentDamagedCells(cell: OpponentCell, subCells: List<OpponentCell>): List<OpponentCell> {
        return subCells.filter { it.damaged }
                .filter { it.getPoint() != cell.getPoint() }
                .filter { it.x == cell.x || it.y == cell.y }
                .map { it }
    }
}