package scooterdelta.challenge.bot.process.processes.attack

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.command.AttackCommand
import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.lookup.StateLookup
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.process.processes.Process

class SelectAttackCommandProcess : Process {

    val LOGGER: Logger = LoggerFactory.getLogger(SelectAttackCommandProcess::class.java)

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {
        val opponentCells: List<OpponentCell> = ArrayList(gameState.opponentMap.cells)
        val opponentCell: OpponentCell = opponentCells.sortedBy { -it.singleShotHitChance }[0]

        processOutcomes.command = AttackCommand(opponentCell.getPoint(), Code.FIRESHOT)
        processOutcomes.stateLookup = StateLookup.COMMAND

        LOGGER.info("Sending attack command: ${processOutcomes.command}")
    }
}