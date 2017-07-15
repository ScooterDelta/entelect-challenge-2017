package scooterdelta.challenge.bot.common.state.remote

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import scooterdelta.challenge.bot.common.state.local.Map
import scooterdelta.challenge.bot.common.state.remote.domain.Cell

data class PlayerMap(

        @JsonProperty("Owner")
        val owner: BattleshipPlayer,

        @JsonProperty("Cells")
        val cells: ArrayList<Cell>,

        @JsonProperty("MapWidth")
        val mapWidth: Int,

        @JsonProperty("MapHeight")
        val mapHeight: Int
) {
    @JsonIgnore
    val map: Map<Cell> = Map(cells)
}
