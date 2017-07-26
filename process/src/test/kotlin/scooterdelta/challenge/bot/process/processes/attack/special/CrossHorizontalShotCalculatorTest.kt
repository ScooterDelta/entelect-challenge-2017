package scooterdelta.challenge.bot.process.processes.attack.special

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.process.processes.ProbabilityCalculator
import scooterdelta.challenge.bot.process.processes.basicCellProbability
import scooterdelta.challenge.bot.process.processes.createGameState
import scooterdelta.challenge.bot.process.processes.generateCellsFromString

class CrossHorizontalShotCalculatorTest {

    private lateinit var gameState: GameState
    private lateinit var probabilityCalculator: ProbabilityCalculator
    private lateinit var processOutcomes: ProcessOutcomes

    @Before fun setUp() {
        gameState = createGameState(generateCellsFromString(generateString()))
        probabilityCalculator = CrossHorizontalShotCalculator()
        processOutcomes = ProcessOutcomes()
    }

    @Test fun testFullProbability() {
        val testCell = gameState.opponentMap.map.getCellFromMap(1, 1) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, gameState, processOutcomes)

        val result = testCell.getAttackTypeProbability(Code.FIRE_CROSS_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(basicCellProbability * 5L))
    }

    @Test fun testDoublePriorityCenterHit() {
        val testCell = gameState.opponentMap.map.getCellFromMap(2, 2) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, gameState, processOutcomes)

        val result = testCell.getAttackTypeProbability(Code.FIRE_CROSS_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(basicCellProbability * 4L))
    }

    @Test fun testSingleAndCenterProbability() {
        val testCell = gameState.opponentMap.map.getCellFromMap(2, 3) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, gameState, processOutcomes)

        val result = testCell.getAttackTypeProbability(Code.FIRE_CROSS_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(basicCellProbability * 2L))
    }

    @Test fun testSingleProbability() {
        val testCell = gameState.opponentMap.map.getCellFromMap(3, 4) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, gameState, processOutcomes)

        val result = testCell.getAttackTypeProbability(Code.FIRE_CROSS_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(basicCellProbability))
    }

    @Test fun testSingleProbabilityEdgeTest() {
        val testCell = gameState.opponentMap.map.getCellFromMap(0, 0) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, gameState, processOutcomes)

        val result = testCell.getAttackTypeProbability(Code.FIRE_CROSS_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(basicCellProbability * 3))
    }

    @Test fun testSingleProbabilityEdgeTestTargetHit() {
        val testCell = gameState.opponentMap.map.getCellFromMap(0, 4) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, gameState, processOutcomes)

        val result = testCell.getAttackTypeProbability(Code.FIRE_CROSS_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(basicCellProbability))
    }

    @Test fun testZeroProbability() {
        val testCell = gameState.opponentMap.map.getCellFromMap(2, 5) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, gameState, processOutcomes)

        val result = testCell.getAttackTypeProbability(Code.FIRE_CROSS_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(0L))
    }

    @Test fun testZeroProbabilityAgain() {
        val testCell = gameState.opponentMap.map.getCellFromMap(0, 5) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, gameState, processOutcomes)

        val result = testCell.getAttackTypeProbability(Code.FIRE_CROSS_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(0L))
    }

    private fun generateString(): String {
        return "" +
                "0 0 0 0 0 0\n" +
                "0 0 0 + 0 0\n" +
                "0 0 X 0 0 +\n" +
                "X 0 0 + 0 +\n" +
                "+ 0 X X 0 +\n" +
                "+ X + X + X"
    }

}