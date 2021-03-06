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

    private val LOGGER: Logger = LoggerFactory.getLogger(RandomPlacementImpl::class.java)

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

        // Check valid borders
        if (validPoint(placeShipGroup.point, gameState) && validPoint(endPoint, gameState)) {
            return checkNoOverlappingShips(placeShipGroup.point, endPoint, shipPlacements)
        }
        return false
    }

    private fun checkNoOverlappingShips(startPoint: Point,
                                        endPoint: Point,
                                        shipPlacements: MutableList<PlaceShipGroup>): Boolean {
        for (shipPlacement in shipPlacements) {
            val otherShipEnd: Point = shipPlacement.getEndPoint()

            if (isOverlapping(startPoint, endPoint, shipPlacement.point, otherShipEnd)) {
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

    private fun validPoint(point: Point, gameState: GameState): Boolean {
        return point.x >= 0 && point.y >= 0 && point.x < gameState.mapDimension && point.y < gameState.mapDimension
    }

    private fun generateRandom(minVal: Int, maxVal: Int): Int {
        return randomGenerator.nextInt(maxVal - minVal) + minVal
    }
}
