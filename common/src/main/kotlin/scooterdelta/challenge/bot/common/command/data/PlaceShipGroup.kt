package scooterdelta.challenge.bot.common.command.data

import scooterdelta.challenge.bot.common.command.Command
import scooterdelta.challenge.bot.common.lookup.ShipType
import scooterdelta.challenge.bot.common.state.local.Direction
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

}