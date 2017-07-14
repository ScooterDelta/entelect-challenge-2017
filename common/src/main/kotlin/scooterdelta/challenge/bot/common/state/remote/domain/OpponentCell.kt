package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class OpponentCell(

        @JsonProperty("X")
        val x: Int,

        @JsonProperty("Y")
        val y: Int,

        @JsonProperty("Damaged")
        val damaged: Boolean,

        @JsonProperty("Missed")
        val missed: Boolean
)