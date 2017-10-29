package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import scooterdelta.challenge.bot.common.state.local.Direction

abstract class BaseCell(

        @JsonProperty("X")
        val x: Int,

        @JsonProperty("Y")
        val y: Int
) {

    @JsonIgnore
    fun getPoint(): Point {
        return Point(x, y)
    }

    fun determineDirection(otherCell: BaseCell): Direction {
        return when {
            x - otherCell.x < 0 -> { Direction.EAST }
            x - otherCell.x > 0 -> { Direction.WEST }
            y - otherCell.y < 0 -> { Direction.NORTH }
            y - otherCell.y > 0 -> { Direction.SOUTH }
            else -> {
                throw RuntimeException("Cannot determine direction - Cell same location")
            }
        }
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