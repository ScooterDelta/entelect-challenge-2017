package scooterdelta.challenge.bot.process.processes.placement

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.process.processes.Process

class BasicPlacementImpl : Process {

    val LOGGER: Logger = LoggerFactory.getLogger(BasicPlacementImpl::class.java)

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {

    }
}
