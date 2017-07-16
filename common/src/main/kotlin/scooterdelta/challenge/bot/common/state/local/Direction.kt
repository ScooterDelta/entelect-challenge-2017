package scooterdelta.challenge.bot.common.state.local

import com.fasterxml.jackson.annotation.JsonProperty

enum class Direction {

    @JsonProperty("North")
    NORTH,
    @JsonProperty("East")
    EAST,
    @JsonProperty("South")
    SOUTH,
    @JsonProperty("West")
    WEST
}
