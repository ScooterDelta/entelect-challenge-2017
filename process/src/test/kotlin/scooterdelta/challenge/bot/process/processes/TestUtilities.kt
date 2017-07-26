package scooterdelta.challenge.bot.process.processes

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.lookup.ShipType
import scooterdelta.challenge.bot.common.lookup.WeaponType
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.*
import scooterdelta.challenge.bot.common.state.remote.domain.Cell
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.common.state.remote.domain.Ship
import scooterdelta.challenge.bot.common.state.remote.domain.Weapon

val basicCellProbability: Long = 10L

fun generateMapFromString(map: String): Map<OpponentCell> {
    val cells: List<OpponentCell> = generateCellsFromString(map)

    return Map(cells)
}

fun generateCellsFromString(map: String): List<OpponentCell> {
    val cells: MutableList<OpponentCell> = mutableListOf()

    for ((rowIter, row) in map.split("\n").withIndex()) {
        row.split(" ")
                .mapIndexedTo(cells) { colIter, col -> createOpponentCellFromMap(colIter, rowIter, col) }
    }

    return cells.toList()
}

fun generateNbyNMapOpponentCell(xSize: Int, ySize: Int): Map<OpponentCell> {
    val cells: MutableList<OpponentCell> = mutableListOf()
    for (x in 0..xSize - 1) {
        (0..ySize - 1).mapTo(cells) { createOpponentCellFlatProbability(x, it, false, false) }
    }
    return Map(cells)
}

fun createGameState(cells: List<OpponentCell>): GameState {
    return createGameState(
            cells,
            listOf(createSingleShopShip(ShipType.BATTLESHIP, 2)),
            listOf(OpponentShip(false, ShipType.BATTLESHIP))
    )
}

fun createGameState(cells: List<OpponentCell>, ships: List<Ship>, opponentShips: List<OpponentShip>): GameState {
    val gameState: GameState = GameState(
            PlayerMap(
                    BattleshipPlayer(0, "", ships, 0, 2,
                            false, false, 0, 0, 1, 'A'),
                    listOf(), 0, 0),
            OpponentMap(true, 0, "", opponentShips, cells),
            "0",
            1,
            1,
            2,
            2)

    return gameState
}

fun createSingleShopShip(shipType: ShipType, shipSize: Int): Ship {
    val cells: List<Cell> = (0 until shipSize).map { Cell(0, it, false, false) }
    return Ship(false, true, shipType, arrayListOf(Weapon(WeaponType.SINGLE_SHOT, 1)), cells)
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
