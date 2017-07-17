package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.BaseCell
import scooterdelta.challenge.bot.common.state.remote.domain.Cell
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.process.processes.Process

class BuildProbabilityMapProcess : Process {

    private val probabilityMultiple: Long = 1000L

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {

        val opponentMap: Map<OpponentCell> = gameState.opponentMap.map
        val opponentCells: List<OpponentCell> = gameState.opponentMap.cells
        val totalHitAndMissed: Long = calculateTotalDamagedAndMissed(opponentCells)

        opponentMap.cells
                .parallelStream()
                .forEach {
                    it.parallelStream()
                            .forEach { it.singleShotHitChance = calculateProbability(it, totalHitAndMissed, opponentMap) }
                }
    }

    private fun calculateProbability(cell: OpponentCell, totalHitAndMissed: Long, map: Map<OpponentCell>): Long {
        var chance: Long = 0

        if (!cell.missed && !cell.damaged) {
            chance++
        }
        return chance * probabilityMultiple / totalHitAndMissed
    }

    private fun calculateTotalDamagedAndMissed(cells: List<OpponentCell>): Long {
        var count: Long = 0
        cells.filter { it.missed || it.damaged }.forEach { count++ }
        return count
    }

}
