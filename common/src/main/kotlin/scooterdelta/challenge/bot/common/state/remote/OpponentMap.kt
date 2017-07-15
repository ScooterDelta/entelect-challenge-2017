package scooterdelta.challenge.bot.common.state.remote

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell

data class OpponentMap(

        @JsonProperty("Alive")
        val alive: Boolean,

        @JsonProperty("Points")
        val points: Int,

        @JsonProperty("Name")
        val name: String,

        @JsonProperty("Ships")
        val ships: ArrayList<OpponentShip>,

        @JsonProperty("Cells")
        val cells: ArrayList<OpponentCell>
) {
    @JsonIgnore
    val map: Map<OpponentCell> = Map(cells)
}
