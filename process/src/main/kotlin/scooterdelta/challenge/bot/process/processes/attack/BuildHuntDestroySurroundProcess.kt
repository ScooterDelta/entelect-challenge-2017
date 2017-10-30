package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell

class BuildHuntDestroySurroundProcess : AbstractBuildProbabilityMapProcess() {

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
