package scooterdelta.challenge.bot.common.state.local

import scooterdelta.challenge.bot.common.state.remote.domain.BaseCell
import scooterdelta.challenge.bot.common.state.remote.domain.Point

class Map<out T : BaseCell>(cells: List<T>) {

    val cells: List<List<T>>
    val totalCells: Int

    // Map state utilities
    private val min: BaseCell
    private val max: BaseCell

    init {
        if (cells.isNotEmpty()) {
            val col: MutableList<MutableList<T>> = mutableListOf()

            min = cells[0]
            max = cells[cells.size - 1]

            for (cell in cells) {
                if (cell.x == min.x) {
                    col.add(mutableListOf())
                }
                col[cell.y - min.y].add(cell)
            }

            val cols: MutableList<List<T>> = mutableListOf()
            col.mapTo(cols) { it.toList() }

            this.cells = cols.reversed().toList()
            this.totalCells = cells.size
        } else {
            this.cells = listOf()
            this.totalCells = 0
            this.min = Point(0, 0)
            this.max = Point(0, 0)
        }
    }

    fun findNAdjacentCells(cell: BaseCell, n: Int): List<T> {
        val cells: MutableList<T> = mutableListOf()
        for (x in cell.x - n..cell.x + n) {
            (cell.y - n..cell.y + n)
                    .mapNotNullTo(cells) { getCellFromMap(x, it) }
        }
        return cells.toList()
    }

    fun findNAdjacentCellsMap(cell: BaseCell, n: Int): Map<T> {
        return Map(findNAdjacentCells(cell, n))
    }

    fun getCellInDirection(cell: BaseCell, direction: Direction): T? {
        return when (direction) {
            Direction.NORTH -> getCellFromMap(cell.x, cell.y + 1)
            Direction.EAST -> getCellFromMap(cell.x + 1, cell.y)
            Direction.SOUTH -> getCellFromMap(cell.x, cell.y - 1)
            Direction.WEST -> getCellFromMap(cell.x - 1, cell.y)
        }
    }

    fun getCellFromMap(x: Int, y: Int): T? {
        val coordinateX = x - min.x
        val coordinateY = y - min.y
        if (coordinateX in 0..cells[0].size - 1 && coordinateY in 0..cells.size - 1) {
            return cells[cells.size - 1 - coordinateY][coordinateX]
        }
        return null
    }

    fun printMap(): String {
        val sb: StringBuilder = StringBuilder()
        for (col in cells) {
            for (cell in col) {
                sb.append(" ${cell.printContent()}")
            }
            sb.append("\n")
        }
        return sb.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Map<*>

        if (cells != other.cells) return false
        if (totalCells != other.totalCells) return false
        if (min != other.min) return false
        if (max != other.max) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cells.hashCode()
        result = 31 * result + totalCells
        result = 31 * result + min.hashCode()
        result = 31 * result + max.hashCode()
        return result
    }

    override fun toString(): String {
        return "Map(cells=$cells, totalCells=$totalCells, min=$min, max=$max)"
    }

}