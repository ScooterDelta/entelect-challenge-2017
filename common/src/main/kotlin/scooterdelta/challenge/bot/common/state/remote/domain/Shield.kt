package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Shield(
        @JsonProperty("ChargeTime")
        val chargeTime: Int,

        @JsonProperty("CurrentCharges")
        val currentCharges: Int,

        @JsonProperty("Active")
        val active: Boolean,

        @JsonProperty("CurrentRadius")
        val currentRadius: Int,

        @JsonProperty("MaxRadius")
        val maxRadius: Int,

        @JsonProperty("CenterPoint")
        val centerPoint: String,

        @JsonProperty("RoundLastUsed")
        val roundLastUsed: Int
)
