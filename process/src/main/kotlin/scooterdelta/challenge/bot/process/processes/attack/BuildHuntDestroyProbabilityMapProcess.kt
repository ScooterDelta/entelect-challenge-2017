package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell

class BuildHuntDestroyProbabilityMapProcess : AbstractBuildProbabilityMapProcess() {

    override fun calculateProbability(cell: OpponentCell, map: Map<OpponentCell>) {

        if (!cell.missed) {
            val subCells: List<OpponentCell> = map.findNAdjacentCells(cell, 1)
            val damaged: List<OpponentCell> = findAdjacentDamagedCells(cell, subCells)

            if (cell.damaged) {

                if (damaged.isEmpty()) {
                    cell.attackTypeProbability[Code.FIRE_CROSS_SHOT_HORIZONTAL] = cell.getAttackTypeProbability(Code.FIRE_SHOT) * 10
                    cell.attackTypeProbability[Code.FIRE_DOUBLE_SHOT_HORIZONTAL] = cell.getAttackTypeProbability(Code.FIRE_SHOT) * 5
                    cell.attackTypeProbability[Code.FIRE_DOUBLE_SHOT_VERTICAL] = cell.getAttackTypeProbability(Code.FIRE_SHOT) * 5
                }

            } else {

                val verticalHorizontalCells: List<OpponentCell> = subCells
                        .filter { it.x == cell.x || it.y == cell.y }
                        .map { it }

                if (!verticalHorizontalCells.isEmpty()) {
                    cell.attackTypeProbability[Code.FIRE_SHOT] = cell.getAttackTypeProbability(Code.FIRE_SHOT) * 3
                }
            }
        }
    }

    private fun findAdjacentDamagedCells(cell: OpponentCell, subCells: List<OpponentCell>): List<OpponentCell> {
        return subCells.filter { it.damaged }
                .filter { it.getPoint() != cell.getPoint() }
                .map { it }
    }
}