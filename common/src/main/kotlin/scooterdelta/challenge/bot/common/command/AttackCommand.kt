package scooterdelta.challenge.bot.common.command

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.remote.domain.Point

data class AttackCommand(

        @JsonProperty("Point")
        val point: Point,

        @JsonProperty("Code")
        val code: Code

) : Command {

    @JsonIgnore
    override fun printCommand(): String {
        return "${code.value},${point.x},${point.y}"
    }
}