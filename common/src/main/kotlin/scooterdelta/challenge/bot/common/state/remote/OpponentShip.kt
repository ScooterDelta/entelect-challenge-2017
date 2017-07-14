package scooterdelta.challenge.bot.common.state.remote

import com.fasterxml.jackson.annotation.JsonProperty
import scooterdelta.challenge.bot.common.lookup.ShipType

data class OpponentShip(

        @JsonProperty("Destroyed")
        val destroyed: Boolean,

        @JsonProperty("ShipType")
        val shipType: ShipType
)