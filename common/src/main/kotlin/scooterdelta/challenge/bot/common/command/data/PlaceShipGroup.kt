package scooterdelta.challenge.bot.common.command.data

import scooterdelta.challenge.bot.common.command.Command
import scooterdelta.challenge.bot.common.lookup.ShipType
import scooterdelta.challenge.bot.common.state.local.Direction
import scooterdelta.challenge.bot.common.state.local.Rectangle
import scooterdelta.challenge.bot.common.state.remote.domain.Point

data class PlaceShipGroup(
        val shipType: ShipType,
        val point: Point,
        val direction: Direction,
        val shipLength: Int
) : Command {

    override fun printCommand(): String {
        return "$shipType ${point.x} ${point.y} $direction"
    }

    fun getEndPoint(): Point {
        when (direction) {
            Direction.NORTH -> {
                return Point(point.x, point.y + shipLength)
            }
            Direction.EAST -> {
                return Point(point.x + shipLength, point.y)
            }
            Direction.WEST -> {
                return Point(point.x - shipLength, point.y)
            }
            Direction.SOUTH -> {
                return Point(point.x, point.y - shipLength)
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    fun getPaddedRect(padding: Int): Rectangle {
        when (direction) {
            Direction.NORTH -> {
                return Rectangle(Point(point.x - padding, point.y - padding), padding * 2, padding * 2 + shipLength)
            }
            Direction.EAST -> {
                return Rectangle(Point(point.x - padding, point.y - padding), padding * 2 + shipLength, padding * 2)
            }
            Direction.WEST -> {
                return Rectangle(Point(point.x - padding - shipLength, point.y - padding), padding * 2 + shipLength, padding * 2)
            }
            Direction.SOUTH -> {
                return Rectangle(Point(point.x - padding, point.y - shipLength - padding), padding * 2, padding * 2 + shipLength)
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }
}