package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.BaseCell
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.process.processes.Process

class BuildProbabilityMapProcess : Process {

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {

        val opponentMap: Map<OpponentCell> = gameState.opponentMap.map

        opponentMap.cells
                .parallelStream()
                .forEach {
                    it.parallelStream()
                            .forEach { it.singleShotHitChance = calculateProbability(it, opponentMap) }
                }
    }

    private fun calculateProbability(cell: OpponentCell, map: Map<OpponentCell>): Long {
        val adjacentCells: List<OpponentCell> = findAdjacentCells(cell, map)
        var chance: Long = 0

        if (!cell.missed && !cell.damaged) {
            adjacentCells
                    .filter { !it.damaged && !it.missed }
                    .forEach { chance++ }
        }
        return chance
    }

    private fun findAdjacentCells(cell: BaseCell, map: Map<OpponentCell>): List<OpponentCell> {
        val cells: MutableList<OpponentCell> = mutableListOf()
        for (x in cell.x - 1..cell.x + 1) {
            (cell.y - 1..cell.y + 1)
                    .filter { x != cell.x && it != cell.y }
                    .mapNotNullTo(cells) { getCellFromMap(x, it, map) }
        }
        return cells.toList()
    }

    private fun getCellFromMap(x: Int, y: Int, map: Map<OpponentCell>): OpponentCell? {
        if (x in 0..map.cells[0].size - 1 && y in 0..map.cells.size - 1) {
            return map.cells[y][x]
        }
        return null
    }
}
