package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonProperty

abstract class BaseCell(

        @JsonProperty("X")
        val x: Int,

        @JsonProperty("Y")
        val y: Int
) {

    fun getPoint(): Point {
        return Point(x, y)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as BaseCell

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    override fun toString(): String {
        return "BaseCell(x=$x, y=$y)"
    }

    abstract fun printContent(): String

}