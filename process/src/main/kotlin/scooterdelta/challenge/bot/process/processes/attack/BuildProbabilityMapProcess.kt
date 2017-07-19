package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell

class BuildProbabilityMapProcess : AbstractBuildProbabilityMapProcess() {

    override fun calculateProbability(cell: OpponentCell, map: Map<OpponentCell>) {
        val chance: Long

        if (!cell.missed && !cell.damaged) {
            chance = cell.basicProbability
        } else {
            chance = 0
        }
        cell.attackTypeProbability[Code.FIRE_SHOT] = chance
    }

}
