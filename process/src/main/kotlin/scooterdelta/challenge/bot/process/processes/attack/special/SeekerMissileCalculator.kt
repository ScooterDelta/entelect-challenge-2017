package scooterdelta.challenge.bot.process.processes.attack.special

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import java.lang.Math.sqrt

class SeekerMissileCalculator : AbstractProbabilityCalculator() {

    override fun getTargetCells(cell: OpponentCell, map: Map<OpponentCell>): List<OpponentCell> {
        val cellsInRange: List<OpponentCell> = getCellsInMissileRange(cell, map)

        // Will re-hit a damaged cell in range if found
        if (isDamagedBlockInRange(cellsInRange)) {
            return emptyList()
        }

        val filteredList: List<OpponentCell> = cellsInRange
                .filterNot { it.damaged }
                .filterNot { it.missed }
                .map { it }
        return filteredList.toList()
    }

    private fun checkValidDistance(cell: OpponentCell, otherCell: OpponentCell): Boolean {
        val xDist: Double = (cell.x - otherCell.x).toDouble()
        val yDist: Double = (cell.y - otherCell.y).toDouble()

        return sqrt((xDist * xDist) + (yDist * yDist)) <= 2.0
    }

    private fun getCellsInMissileRange(cell: OpponentCell, map: Map<OpponentCell>): List<OpponentCell> {
        return map.findNAdjacentCells(cell, 2)
                .filter { checkValidDistance(cell, it) }
                .map { it }
    }

    private fun isDamagedBlockInRange(cells: List<OpponentCell>): Boolean {
        return cells.any { it.damaged }
    }

    override fun getCode(): Code {
        return Code.FIRE_SEEKER_MISSILE
    }
}