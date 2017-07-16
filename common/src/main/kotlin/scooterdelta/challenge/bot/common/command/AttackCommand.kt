package scooterdelta.challenge.bot.common.command

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.remote.domain.Point

class AttackCommand(
        val point: Point,
        val code: Code
) : Command {

    override fun printCommand(): String {
        return "${code.value},${point.x},${point.y}"
    }
}