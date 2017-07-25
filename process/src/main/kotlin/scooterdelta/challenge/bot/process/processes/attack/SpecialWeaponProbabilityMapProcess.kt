package scooterdelta.challenge.bot.process.processes.attack

import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.process.processes.ProbabilityCalculator
import javax.inject.Named

class SpecialWeaponProbabilityMapProcess(

        @Named("probabilityCalculators")
        private val probabilityCalculators: ArrayList<ProbabilityCalculator>

) : AbstractBuildProbabilityMapProcess() {

    override fun calculateProbability(cell: OpponentCell, gameState: GameState, processOutcomes: ProcessOutcomes) {
        // Calculate probability for each weapon type
        for (probabilityCalculator in probabilityCalculators) {
            probabilityCalculator.calculateProbability(cell, gameState, processOutcomes)
        }
    }

}