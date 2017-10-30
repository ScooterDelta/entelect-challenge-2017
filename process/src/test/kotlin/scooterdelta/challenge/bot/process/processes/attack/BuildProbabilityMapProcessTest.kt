package scooterdelta.challenge.bot.process.processes.attack

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.command.NoActionCommand
import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.lookup.ShipType
import scooterdelta.challenge.bot.common.lookup.StateLookup
import scooterdelta.challenge.bot.common.state.local.GameMode
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.OpponentShip
import scooterdelta.challenge.bot.process.processes.Process
import scooterdelta.challenge.bot.process.processes.createGameState
import scooterdelta.challenge.bot.process.processes.createSingleShopShip
import scooterdelta.challenge.bot.process.processes.generateCellsFromString

class BuildProbabilityMapProcessTest {

    private val LOGGER: Logger = LoggerFactory.getLogger(BuildProbabilityMapProcessTest::class.java)

    private lateinit var gameState: GameState
    private lateinit var processOutcomes: ProcessOutcomes
    private lateinit var process: Process

    @Before
    fun setUp() {
        process = BuildProbabilityMapProcess()
        processOutcomes = ProcessOutcomes(NoActionCommand(), StateLookup.COMMAND, GameMode.SPEND)
    }

    @Test
    fun testBasicProbabilityMapSizeTwo() {
        gameState = createGameState(generateCellsFromString(generateString()))

        process.process(gameState, processOutcomes)
        LOGGER.info("Map: \n{}", printProbabilities(gameState))
        assertThat("The map is build correctly", getTargetedCells(gameState), equalTo(getMapCellCount(gameState) / 2))
    }

    @Test
    fun testBasicProbabilityMapSizeThree() {
        gameState = createGameState(generateCellsFromString(generateString()),
                listOf(createSingleShopShip(ShipType.BATTLESHIP, 3)),
                listOf(OpponentShip(false, ShipType.BATTLESHIP)))

        process.process(gameState, processOutcomes)
        LOGGER.info("Map: \n{}", printProbabilities(gameState))
        assertThat("The map is build correctly", getTargetedCells(gameState), equalTo(getMapCellCount(gameState) / 3))
    }

    @Test
    fun testBasicProbabilityMapSizeFour() {
        gameState = createGameState(generateCellsFromString(generateString()),
                listOf(createSingleShopShip(ShipType.BATTLESHIP, 4)),
                listOf(OpponentShip(false, ShipType.BATTLESHIP)))

        process.process(gameState, processOutcomes)
        LOGGER.info("Map: \n{}", printProbabilities(gameState))
        assertThat("The map is build correctly", getTargetedCells(gameState), equalTo(getMapCellCount(gameState) / 4))
    }

    @Test
    fun testHitCellsWithMissTwo() {
        gameState = createGameState(generateCellsFromString("" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "+ 0 0 0 0 0\n" +
                "0 X 0 0 0 0"),
                listOf(createSingleShopShip(ShipType.BATTLESHIP, 2)),
                listOf(OpponentShip(false, ShipType.BATTLESHIP)))

        process.process(gameState, processOutcomes)
        LOGGER.info("Map: \n{}", printProbabilities(gameState))
        assertThat("The map is build correctly", getTargetedCells(gameState), equalTo(getMapCellCount(gameState) / 2 - 2))
    }

    @Test
    fun testHitCellsWithMissThree() {
        gameState = createGameState(generateCellsFromString("" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 X 0 0 0 0\n" +
                "+ 0 0 + 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 X 0 0 0 0"),
                listOf(createSingleShopShip(ShipType.BATTLESHIP, 3)),
                listOf(OpponentShip(false, ShipType.BATTLESHIP)))

        process.process(gameState, processOutcomes)
        LOGGER.info("Map: \n{}", printProbabilities(gameState))
        assertThat("The map is build correctly", getTargetedCells(gameState), equalTo(getMapCellCount(gameState) / 3 - 4))
    }

    private fun printProbabilities(gameState: GameState): String {
        return gameState.opponentMap.map.cells
                .map { y -> y.map { x -> if (x.attackTypeProbability[Code.FIRE_SHOT] == 0L) "0" else "X" }.reduce { a, b -> a + " " + b } }
                .reduce { a, b -> a + "\n" + b }
    }

    private fun getMapCellCount(gameState: GameState): Int {
        return gameState.opponentMap.map.totalCells
    }

    private fun getTargetedCells(gameState: GameState): Int {
        return gameState.opponentMap.map.cells
                .map { y -> y.map { x -> if (x.attackTypeProbability[Code.FIRE_SHOT] == 0L) 0 else 1 }.reduce { acc, b -> acc + b } }
                .reduce { acc, i -> acc + i }
    }

    private fun generateString(): String {
        return "" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0\n" +
                "0 0 0 0 0 0"
    }

}