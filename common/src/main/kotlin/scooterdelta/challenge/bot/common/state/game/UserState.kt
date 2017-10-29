package scooterdelta.challenge.bot.common.state.game

import com.fasterxml.jackson.annotation.JsonProperty
import scooterdelta.challenge.bot.common.command.AttackCommand

data class UserState(

        @JsonProperty("attackCommands")
        var attackCommands: MutableList<AttackCommand>?
)
