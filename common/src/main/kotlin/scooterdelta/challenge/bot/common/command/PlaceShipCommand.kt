package scooterdelta.challenge.bot.common.command

import scooterdelta.challenge.bot.common.command.data.PlaceShipGroup

data class PlaceShipCommand(
        val placeShipGroups: List<PlaceShipGroup>
) : Command {

    override fun printCommand(): String {
        val sb: StringBuilder = StringBuilder()
        placeShipGroups.forEach { sb.append("${it.printCommand()}\r\n") }
        return sb.toString()
    }

}