package scooterdelta.challenge.bot.common.state.local

import scooterdelta.challenge.bot.common.state.remote.domain.BaseCell

class Map<out T : BaseCell>(cells: List<T>) {

    val cells: List<List<T>>
    val totalCells: Int

    init {
        val col: MutableList<MutableList<T>> = mutableListOf()

        for (cell in cells) {
            if (cell.x == 0) {
                col.add(mutableListOf())
            }
            col[cell.y].add(cell)
        }

        val cols: MutableList<List<T>> = mutableListOf()
        col.mapTo(cols) { it.toList() }

        this.cells = cols.reversed().toList()
        this.totalCells = cells.size
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Map<*>

        if (cells != other.cells) return false

        return true
    }

    fun findNAdjacentCells(cell: BaseCell, n: Int): List<T> {
        val cells: MutableList<T> = mutableListOf()
        for (x in cell.x - n..cell.x + n) {
            (cell.y - n..cell.y + n)
                    .filter { x != cell.x && it != cell.y }
                    .mapNotNullTo(cells) { getCellFromMap(x, it) }
        }
        return cells.toList()
    }

    fun getCellFromMap(x: Int, y: Int): T? {
        if (x in 0..cells[0].size - 1 && y in 0..cells.size - 1) {
            return cells[y][x]
        }
        return null
    }

    override fun hashCode(): Int {
        return cells.hashCode()
    }

    override fun toString(): String {
        return "Map(cells=$cells)"
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
}