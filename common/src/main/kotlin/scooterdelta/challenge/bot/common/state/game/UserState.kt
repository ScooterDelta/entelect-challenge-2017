package scooterdelta.challenge.bot.common.state.game

import com.fasterxml.jackson.annotation.JsonProperty
import scooterdelta.challenge.bot.common.command.AttackCommand
import scooterdelta.challenge.bot.common.state.local.GameMode

data class UserState(

        @JsonProperty("AttackCommands")
        var attackCommands: MutableList<AttackCommand>?,

        @JsonProperty("GameMode")
        var gameMode: GameMode
)
