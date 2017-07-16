package scooterdelta.challenge.bot.common.command

import scooterdelta.challenge.bot.common.lookup.Code

class NoActionCommand : Command {
    override fun printCommand(): String {
        return "${Code.DO_NOTHING.value}"
    }
}