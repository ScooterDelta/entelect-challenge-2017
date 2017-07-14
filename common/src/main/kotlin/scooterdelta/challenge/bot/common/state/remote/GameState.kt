package scooterdelta.challenge.bot.common.state.remote

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(
        value = *arrayOf("Player1Map", "Player2Map")
)
data class GameState(

        @JsonProperty("PlayerMap")
        val playerMap: PlayerMap,

        @JsonProperty("OpponentMap")
        val opponentMap: OpponentMap,

        @JsonProperty("GameVersion")
        val gameVersion: String,

        @JsonProperty("GameLevel")
        val gameLevel: Int,

        @JsonProperty("Round")
        val round: Int,

        @JsonProperty("MapDimension")
        val mapDimension: Int,

        @JsonProperty("Phase")
        val phase: Int
)
