package scooterdelta.challenge.bot.process.processes.attack.special

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import java.lang.Math.sqrt

class SeekerMissileCalculator : AbstractProbabilityCalculator() {

    override fun getTargetCells(cell: OpponentCell, map: Map<OpponentCell>): List<OpponentCell> {
        val filteredList: MutableList<OpponentCell> = mutableListOf()
        for (y in cell.y - 2..cell.y + 2) {
            (cell.x - 2..cell.x + 2)
                    .mapNotNull { x -> map.getCellFromMap(x, y) }
                    .filter { checkValidDistance(cell, it) }
                    .filter { !it.damaged }
                    .filter { !it.missed }
                    .mapTo(filteredList) { it }
        }
        // Will re-hit a damaged cell in range if found
        if (isDamagedBlockInRange(filteredList)) {
            filteredList.clear()
        }
        return filteredList.toList()
    }

    private fun checkValidDistance(cell: OpponentCell, otherCell: OpponentCell): Boolean {
        val xDist: Double = (cell.x - otherCell.x).toDouble()
        val yDist: Double = (cell.y - otherCell.y).toDouble()

        return sqrt((xDist * xDist) + (yDist * yDist)).toInt() < 3
    }

    private fun isDamagedBlockInRange(cells: List<OpponentCell>): Boolean {
        return cells.any { it.damaged }
    }

    override fun getCode(): Code {
        return Code.FIRE_SEEKER_MISSILE
    }
}