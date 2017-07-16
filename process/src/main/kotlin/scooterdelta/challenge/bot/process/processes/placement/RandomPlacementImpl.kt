package scooterdelta.challenge.bot.process.processes.placement

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.command.PlaceShipCommand
import scooterdelta.challenge.bot.common.command.data.PlaceShipGroup
import scooterdelta.challenge.bot.common.lookup.StateLookup
import scooterdelta.challenge.bot.common.state.local.Direction
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.Point
import scooterdelta.challenge.bot.common.state.local.helper.fromInt
import scooterdelta.challenge.bot.process.processes.Process
import java.util.*

class RandomPlacementImpl(private val randomGenerator: Random) : Process {

    val LOGGER: Logger = LoggerFactory.getLogger(RandomPlacementImpl::class.java)

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {
        val placementCommands: PlaceShipCommand = PlaceShipCommand(
                placeShipsRandomly(gameState)
        )
        processOutcomes.command = placementCommands
        processOutcomes.stateLookup = StateLookup.PLACE_SHIP

        LOGGER.info("Ships are being placed at: $placementCommands")
    }

    fun placeShipsRandomly(gameState: GameState): List<PlaceShipGroup> {
        val shipPlacements: MutableList<PlaceShipGroup> = mutableListOf()
        for (ship in gameState.playerMap.owner.ships) {
            var placeShipGroup: PlaceShipGroup
            do {
                val point: Point = Point(
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

    fun validLocation(placeShipGroup: PlaceShipGroup,
                      gameState: GameState,
                      shipPlacements: MutableList<PlaceShipGroup>): Boolean {
        val endPoint: Point = getEndPoint(placeShipGroup.point, placeShipGroup.direction, placeShipGroup.shipLength)

        // Check valid borders
        if (validPoint(placeShipGroup.point, gameState) && validPoint(endPoint, gameState)) {
            return checkNoOverlappingShips(placeShipGroup.point, endPoint, shipPlacements)
        }
        return false
    }

    private fun checkNoOverlappingShips(startPoint: Point,
                                        endPoint: Point,
                                        shipPlacements: MutableList<PlaceShipGroup>): Boolean {
        for ((_, otherShipStart, direction, shipLength) in shipPlacements) {
            val otherShipEnd: Point = getEndPoint(otherShipStart, direction, shipLength)

            if (isOverlapping(startPoint, endPoint, otherShipStart, otherShipEnd)) {
                return false
            }
        }
        return true
    }

    private fun isOverlapping(a: Point, b: Point, c: Point, d: Point): Boolean {
        val denom: Int = ((b.x - a.x) * (d.y - c.y)) - ((b.y - a.y) * (d.x - c.x))
        val numOne: Int = ((a.y - c.y) * (d.x - c.x)) - ((a.x - c.x) * (d.y - c.y))
        val numTwo: Int = ((a.y - c.y) * (b.x - a.x)) - ((a.x - c.x) * (b.y - a.y))

        if (denom == 0) return numOne == 0 && numTwo == 0

        val r: Double = numOne.toDouble() / denom.toDouble()
        val s: Double = numTwo.toDouble() / denom.toDouble()

        return (r in 0.0..1.0) && (s in 0.0..1.0)
    }

    fun validPoint(point: Point, gameState: GameState): Boolean {
        return point.x >= 0 && point.y >= 0 && point.x < gameState.mapDimension && point.y < gameState.mapDimension
    }

    fun getEndPoint(point: Point, direction: Direction, size: Int): Point {
        when (direction) {
            Direction.NORTH -> {
                return Point(point.x, point.y + size)
            }
            Direction.EAST -> {
                return Point(point.x + size, point.y)
            }
            Direction.WEST -> {
                return Point(point.x - size, point.y)
            }
            Direction.SOUTH -> {
                return Point(point.x, point.y - size)
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    fun generateRandom(minVal: Int, maxVal: Int): Int {
        return randomGenerator.nextInt(maxVal - minVal) + minVal
    }
}
