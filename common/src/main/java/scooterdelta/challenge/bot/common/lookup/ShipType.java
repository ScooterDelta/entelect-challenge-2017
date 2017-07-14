package scooterdelta.challenge.bot.common.lookup;

import com.google.gson.annotations.SerializedName;

public enum ShipType {
    @SerializedName("Battleship")
    BATTLESHIP,
    @SerializedName("Carrier")
    CARRIER,
    @SerializedName("Cruiser")
    CRUISER,
    @SerializedName("Destroyer")
    DESTROYER,
    @SerializedName("Submarine")
    SUBMARINE
}
