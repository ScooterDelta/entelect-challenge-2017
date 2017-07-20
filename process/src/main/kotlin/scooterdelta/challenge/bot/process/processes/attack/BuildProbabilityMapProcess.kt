package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.OpponentShip
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.common.state.remote.domain.Ship

class BuildProbabilityMapProcess : AbstractBuildProbabilityMapProcess() {

    override fun calculateProbability(cell: OpponentCell, map: Map<OpponentCell>, gameState: GameState) {
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
        var counter = Int.MAX_VALUE
        gameState.opponentMap.ships
                .filterNot { it.destroyed }
                .mapNotNull { findEquivalentLocalShip(it, gameState) }
                .map { it.cells.size }
                .filter { it < counter }
                .forEach { counter = it }
        // Return 2 - The current shortest ship if nothing else is found
        return if (counter == Int.MAX_VALUE) 2 else counter
    }

    private fun findEquivalentLocalShip(opponentShip: OpponentShip, gameState: GameState): Ship? {
        gameState.playerMap.owner.ships
                .filter { it.shipType == opponentShip.shipType }
                .forEach { return it }
        return null
    }

}
