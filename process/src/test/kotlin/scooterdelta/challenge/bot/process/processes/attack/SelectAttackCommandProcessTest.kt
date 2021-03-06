package scooterdelta.challenge.bot.process.processes.attack

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import scooterdelta.challenge.bot.common.command.AttackCommand
import scooterdelta.challenge.bot.common.command.Command
import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.common.state.remote.domain.Point
import scooterdelta.challenge.bot.process.processes.createGameState
import java.util.*

class SelectAttackCommandProcessTest {

    private lateinit var selectAttackCommandProcess: SelectAttackCommandProcess

    @Before fun setUp() {
        selectAttackCommandProcess = SelectAttackCommandProcess(Random())
    }

    @Test fun testProbabilityOrdering() {
        val cells: List<OpponentCell> = arrayListOf(
                buildOpponentCellWithProbability(0, 0, false, false, 0),
                buildOpponentCellWithProbability(0, 1, false, false, 1),
                buildOpponentCellWithProbability(1, 0, false, false, 1),
                buildOpponentCellWithProbability(1, 1, false, false, 2)
        )
        val gameState: GameState = createGameState(cells)
        val processOutcomes = ProcessOutcomes()

        selectAttackCommandProcess.process(gameState, processOutcomes)

        val expectedValue: Command = AttackCommand(Point(1, 1), Code.FIRE_SHOT)
        assertThat("The correct cell is selected", processOutcomes.command, equalTo(expectedValue))
    }

    @Test fun testProbabilityOrderingReversed() {
        val cells: List<OpponentCell> = arrayListOf(
                buildOpponentCellWithProbability(0, 0, false, false, 2),
                buildOpponentCellWithProbability(0, 1, false, false, 1),
                buildOpponentCellWithProbability(1, 0, false, false, 1),
                buildOpponentCellWithProbability(1, 1, false, false, 0)
        )
        val gameState: GameState = createGameState(cells)
        val processOutcomes = ProcessOutcomes()

        selectAttackCommandProcess.process(gameState, processOutcomes)

        val expectedValue: Command = AttackCommand(Point(0, 0), Code.FIRE_SHOT)
        assertThat("The correct cell is selected", processOutcomes.command, equalTo(expectedValue))
    }

    private fun buildOpponentCellWithProbability(x: Int, y: Int, damaged: Boolean, missed: Boolean, chance: Long): OpponentCell {
        val opponentCell = OpponentCell(x, y, damaged, missed, false)
        opponentCell.attackTypeProbability[Code.FIRE_SHOT] = chance
        return opponentCell
    }

}