package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Cell(

        @JsonProperty("X")
        val x: Int,

        @JsonProperty("Y")
        val y: Int,

        @JsonProperty("Occupied")
        val occupied: Boolean,

        @JsonProperty("Hit")
        val hit: Boolean
)