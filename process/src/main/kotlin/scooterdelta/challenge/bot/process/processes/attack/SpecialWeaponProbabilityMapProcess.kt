package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.process.processes.ProbabilityCalculator
import javax.inject.Named

class SpecialWeaponProbabilityMapProcess(

        @Named("probabilityCalculators")
        private val probabilityCalculators: ArrayList<ProbabilityCalculator>

) : AbstractBuildProbabilityMapProcess() {

    override fun calculateProbability(cell: OpponentCell, map: Map<OpponentCell>) {
        // Calculate probability for each weapon type
        for (probabilityCalculator in probabilityCalculators) {
            probabilityCalculator.calculateProbability(cell, map)
        }
    }

}