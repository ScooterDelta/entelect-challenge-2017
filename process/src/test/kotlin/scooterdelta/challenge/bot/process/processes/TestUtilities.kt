package scooterdelta.challenge.bot.process.processes

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell

val basicCellProbability: Long = 10L

fun generateMapFromString(map: String): Map<OpponentCell> {
    val cells: MutableList<OpponentCell> = mutableListOf()

    for ((rowIter, row) in map.split("\n").withIndex()) {
        row.split(" ")
                .mapIndexedTo(cells) { colIter, col -> createOpponentCellFromMap(colIter, rowIter, col) }
    }

    return Map(cells)
}

fun generateNbyNMapOpponentCell(xSize: Int, ySize: Int): Map<OpponentCell> {
    val cells: MutableList<OpponentCell> = mutableListOf()
    for (x in 0..xSize - 1) {
        (0..ySize - 1).mapTo(cells) { createOpponentCellFlatProbability(x, it, false, false) }
    }
    return Map(cells)
}

private fun createOpponentCellFlatProbability(x: Int, y: Int, damaged: Boolean, missed: Boolean): OpponentCell {
    val opponentCell: OpponentCell = OpponentCell(x, y, damaged, missed)
    opponentCell.attackTypeProbability[Code.FIRE_SHOT] = basicCellProbability
    return opponentCell
}

private fun createOpponentCellFromMap(x: Int, y: Int, cell: String): OpponentCell {
    val opponentCell: OpponentCell
    when (cell) {
        "X" -> {
            opponentCell = OpponentCell(x, y, true, false)
            opponentCell.attackTypeProbability[Code.FIRE_SHOT] = 0L
        }
        "+" -> {
            opponentCell = OpponentCell(x, y, false, true)
            opponentCell.attackTypeProbability[Code.FIRE_SHOT] = 0L
        }
        else -> {
            opponentCell = OpponentCell(x, y, false, false)
            opponentCell.attackTypeProbability[Code.FIRE_SHOT] = basicCellProbability
        }
    }
    return opponentCell
}
