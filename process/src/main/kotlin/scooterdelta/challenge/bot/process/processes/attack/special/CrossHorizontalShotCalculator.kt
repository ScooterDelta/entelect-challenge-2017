package scooterdelta.challenge.bot.process.processes.attack.special

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell

class CrossHorizontalShotCalculator : AbstractProbabilityCalculator() {

    override fun getTargetCells(cell: OpponentCell, map: Map<OpponentCell>): List<OpponentCell> {
        val filteredList: MutableList<OpponentCell> = mutableListOf()
        for (y in cell.y - 1..cell.y + 1) {
            (cell.x - 1..cell.x + 1)
                    .mapNotNull { x -> map.getCellFromMap(x, y) }
                    .filter { it.x == cell.x || it.y == cell.y }
                    .filter { !it.damaged }
                    .filter { !it.missed }
                    .mapTo(filteredList) { it }
        }
        return filteredList.toList()
    }

    override fun getCode(): Code {
        return Code.FIRE_CROSS_SHOT_HORIZONTAL
    }
}