package scooterdelta.challenge.bot.process.processes

import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState

interface Process {

    fun process(gameState: GameState, processOutcomes: ProcessOutcomes)

}
