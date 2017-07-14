package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Point(

        @JsonProperty("Y")
        val y: Int,

        @JsonProperty("X")
        val x: Int
)
