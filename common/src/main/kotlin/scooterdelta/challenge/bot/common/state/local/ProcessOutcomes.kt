package scooterdelta.challenge.bot.common.state.local

import scooterdelta.challenge.bot.common.command.Command
import scooterdelta.challenge.bot.common.command.NoActionCommand
import scooterdelta.challenge.bot.common.lookup.StateLookup

data class ProcessOutcomes(
        var command: Command,
        var stateLookup: StateLookup
) {
    constructor() : this(NoActionCommand(), StateLookup.COMMAND)
}
