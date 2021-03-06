package scooterdelta.challenge.bot.process.processes.attack.special

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell

class DoubleShotVerticalCalculator : AbstractProbabilityCalculator() {

    override fun getTargetCells(cell: OpponentCell, map: Map<OpponentCell>): List<OpponentCell> {
        return (cell.y - 1..cell.y + 1)
                .mapNotNull { map.getCellFromMap(cell.x, it) }
                .filter { cell.getPoint() != it.getPoint() }
                .filter { !it.damaged }
                .filter { !it.missed }
                .toList()
    }

    override fun getCode(): Code {
        return Code.FIRE_DOUBLE_SHOT_VERTICAL
    }

}