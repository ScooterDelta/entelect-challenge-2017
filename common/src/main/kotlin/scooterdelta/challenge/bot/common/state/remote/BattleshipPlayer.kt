package scooterdelta.challenge.bot.common.state.remote

import com.fasterxml.jackson.annotation.JsonProperty
import scooterdelta.challenge.bot.common.state.remote.domain.Ship

data class BattleshipPlayer(

        @JsonProperty("FailedFirstPhaseCommands")
        val failedFirstPhaseCommands: Int,

        @JsonProperty("Name")
        val name: String,

        @JsonProperty("Ships")
        val ships: List<Ship>,

        @JsonProperty("Points")
        val points: Int,

        @JsonProperty("Energy")
        val energy: Int,

        @JsonProperty("Killed")
        val killed: Boolean,

        @JsonProperty("IsWinner")
        val isWinner: Boolean,

        @JsonProperty("ShotsFired")
        val shotsFired: Int,

        @JsonProperty("ShotsHit")
        val shotsHit: Int,

        @JsonProperty("ShipsRemaining")
        val shipsRemaining: Int,

        @JsonProperty("Key")
        val key: Char
)