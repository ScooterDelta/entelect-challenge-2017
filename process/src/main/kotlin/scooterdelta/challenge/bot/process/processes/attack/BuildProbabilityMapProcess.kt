package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.OpponentShip
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.common.state.remote.domain.Ship

class BuildProbabilityMapProcess : AbstractBuildProbabilityMapProcess() {

    override fun calculateProbability(cell: OpponentCell, gameState: GameState, processOutcomes: ProcessOutcomes) {
        val chance: Long

        val minShipLength = getMinEnemyShipLength(gameState)
        if (!cell.missed && !cell.damaged && cellInCheckerBlock(cell, minShipLength)) {
            chance = cell.basicProbability
        } else {
            chance = 0
        }
        cell.attackTypeProbability[Code.FIRE_SHOT] = chance
    }

    private fun cellInCheckerBlock(cell: OpponentCell, minShipLength: Int): Boolean {
        if ((cell.y + cell.x) % minShipLength == 0) {
            return true
        }
        return false
    }

    private fun getMinEnemyShipLength(gameState: GameState): Int {
        return gameState.opponentMap.ships
                .filterNot { it.destroyed }
                .mapNotNull { findEquivalentLocalShip(it, gameState) }
                .map { it.cells.size }
                .min() ?: 2
    }

    private fun findEquivalentLocalShip(opponentShip: OpponentShip, gameState: GameState): Ship? {
        gameState.playerMap.owner.ships
                .filter { it.shipType == opponentShip.shipType }
                .forEach { return it }
        return null
    }

}
