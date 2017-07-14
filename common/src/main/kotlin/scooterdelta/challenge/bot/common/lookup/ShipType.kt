package scooterdelta.challenge.bot.common.lookup

import com.fasterxml.jackson.annotation.JsonProperty

enum class ShipType {
    @JsonProperty("Battleship")
    BATTLESHIP,
    @JsonProperty("Carrier")
    CARRIER,
    @JsonProperty("Cruiser")
    CRUISER,
    @JsonProperty("Destroyer")
    DESTROYER,
    @JsonProperty("Submarine")
    SUBMARINE
}