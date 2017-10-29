package scooterdelta.challenge.bot.process.processes.placement

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.command.PlaceShipCommand
import scooterdelta.challenge.bot.common.command.data.PlaceShipGroup
import scooterdelta.challenge.bot.common.lookup.StateLookup
import scooterdelta.challenge.bot.common.state.local.Direction
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.local.Rectangle
import scooterdelta.challenge.bot.common.state.local.helper.fromInt
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.Point
import scooterdelta.challenge.bot.process.processes.Process
import java.util.*

class RandomPaddedPlacement(private val randomGenerator: Random,
                            private val padding: Int) : Process {

    private val LOGGER: Logger = LoggerFactory.getLogger(RandomPaddedPlacement::class.java)

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {
        val placementCommands = PlaceShipCommand(
                placeShipsRandomly(gameState)
        )
        processOutcomes.command = placementCommands
        processOutcomes.stateLookup = StateLookup.PLACE_SHIP

        LOGGER.info("Ships are being placed at: $placementCommands")
    }

    private fun placeShipsRandomly(gameState: GameState): List<PlaceShipGroup> {
        val shipPlacements: MutableList<PlaceShipGroup> = mutableListOf()
        for (ship in gameState.playerMap.owner.ships) {
            var placeShipGroup: PlaceShipGroup
            do {
                val point = Point(
                        generateRandom(0, gameState.mapDimension),
                        generateRandom(0, gameState.mapDimension)
                )
                val direction: Direction = fromInt(generateRandom(0, Direction.values().size))

                placeShipGroup = PlaceShipGroup(ship.shipType, point, direction, ship.cells.size)
            } while (!validLocation(placeShipGroup, gameState, shipPlacements))

            shipPlacements.add(placeShipGroup)
        }
        return shipPlacements.toList()
    }

    private fun validLocation(placeShipGroup: PlaceShipGroup,
                              gameState: GameState,
                              shipPlacements: MutableList<PlaceShipGroup>): Boolean {
        val endPoint: Point = placeShipGroup.getEndPoint()
        val rect: Rectangle = placeShipGroup.getPaddedRect(padding)

        // Check valid borders
        if (validPoint(placeShipGroup.point, gameState) && validPoint(endPoint, gameState)) {
            return checkNoOverlappingShips(rect, shipPlacements)
        }
        return false
    }

    private fun checkNoOverlappingShips(rectangle: Rectangle,
                                        shipPlacements: MutableList<PlaceShipGroup>): Boolean {
        return shipPlacements
                .map { it.getPaddedRect(padding) }
                .none { isOverlapping(rectangle, it) }
    }

    private fun isOverlapping(rectangle: Rectangle, otherRectangle: Rectangle): Boolean {
        val xOverlap: Boolean = valueInRange(rectangle.point.x, otherRectangle.point.x, otherRectangle.point.x + otherRectangle.width)
                || valueInRange(otherRectangle.point.x, rectangle.point.x, rectangle.point.x + rectangle.width)

        val yOverlap: Boolean = valueInRange(rectangle.point.y, otherRectangle.point.y, otherRectangle.point.y + otherRectangle.height)
                || valueInRange(otherRectangle.point.y, rectangle.point.y, rectangle.point.y + rectangle.height)

        return xOverlap && yOverlap
    }

    private fun valueInRange(value: Int, minVal: Int, maxVal: Int): Boolean {
        return value in minVal..maxVal
    }

    private fun validPoint(point: Point, gameState: GameState): Boolean {
        return point.x >= 0 && point.y >= 0 && point.x < gameState.mapDimension && point.y < gameState.mapDimension
    }

    private fun generateRandom(minVal: Int, maxVal: Int): Int {
        return randomGenerator.nextInt(maxVal - minVal) + minVal
    }
}