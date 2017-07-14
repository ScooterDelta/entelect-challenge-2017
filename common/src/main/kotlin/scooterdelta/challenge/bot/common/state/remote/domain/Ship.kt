package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonProperty
import scooterdelta.challenge.bot.common.lookup.ShipType

data class Ship(

        @JsonProperty("Destroyed")
        val destroyed: Boolean,

        @JsonProperty("Placed")
        val placed: Boolean,

        @JsonProperty("ShipType")
        val shipType: ShipType,

        @JsonProperty("Weapons")
        val weapons: ArrayList<Weapon>,

        @JsonProperty("Cells")
        val cells: ArrayList<Cell>
)