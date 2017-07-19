package scooterdelta.challenge.bot.process.processes.attack.special

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.process.processes.ProbabilityCalculator
import scooterdelta.challenge.bot.process.processes.basicCellProbability
import scooterdelta.challenge.bot.process.processes.generateMapFromString

class DoubleShotHorizontalCalculatorTest {

    private lateinit var testMap: Map<OpponentCell>
    private lateinit var probabilityCalculator: ProbabilityCalculator

    @Before fun setUp() {
        testMap = generateMapFromString(generateString())
        probabilityCalculator = DoubleShotHorizontalCalculator()
    }

    @Test fun testDoubleProbability() {
        val testCell = testMap.getCellFromMap(1, 0) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, testMap)

        val result = testCell.getAttackTypeProbability(Code.FIRE_DOUBLE_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(basicCellProbability * 2L))
    }

    @Test fun testSingleProbability() {
        val testCell = testMap.getCellFromMap(1, 1) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, testMap)

        val result = testCell.getAttackTypeProbability(Code.FIRE_DOUBLE_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(basicCellProbability))
    }

    @Test fun testSingleProbabilityAgain() {
        val testCell = testMap.getCellFromMap(4, 1) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, testMap)

        val result = testCell.getAttackTypeProbability(Code.FIRE_DOUBLE_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(basicCellProbability))
    }

    @Test fun testZeroProbability() {
        val testCell = testMap.getCellFromMap(1, 5) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, testMap)

        val result = testCell.getAttackTypeProbability(Code.FIRE_DOUBLE_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(0L))
    }

    @Test fun testZeroProbabilityAgain() {
        val testCell = testMap.getCellFromMap(1, 4) as OpponentCell
        probabilityCalculator.calculateProbability(testCell, testMap)

        val result = testCell.getAttackTypeProbability(Code.FIRE_DOUBLE_SHOT_HORIZONTAL)

        assertThat("Calculated list size is correct", result, equalTo(0L))
    }

    private fun generateString(): String {
        return "" +
                "0 0 0 0 0 0\n" +
                "X 0 0 + 0 0\n" +
                "0 0 X 0 0 +\n" +
                "X 0 X + 0 +\n" +
                "+ 0 X X 0 +\n" +
                "+ X + X + X"
    }

}