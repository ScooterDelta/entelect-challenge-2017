package scooterdelta.challenge.bot.common.lookup

import com.fasterxml.jackson.annotation.JsonProperty

enum class WeaponType {

    @JsonProperty("SingleShot")
    SINGLE_SHOT,

    @JsonProperty("SeekerMissile")
    SEEKER_MISSILE,

    @JsonProperty("DoubleShot")
    DOUBLE_SHOT,

    @JsonProperty("DiagonalCrossShot")
    DIAGONAL_CROSS_SHOT,

    @JsonProperty("CornerShot")
    CORNER_SHOT,

    @JsonProperty("CrossShot")
    CROSS_SHOT,

    @JsonProperty("None")
    NONE

}