package scooterdelta.challenge.bot.process.processes.attack

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.process.processes.Process

class BuildProbabilityMapProcess : Process {

    val LOGGER: Logger = LoggerFactory.getLogger(BuildProbabilityMapProcess::class.java)

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {

    }
}
