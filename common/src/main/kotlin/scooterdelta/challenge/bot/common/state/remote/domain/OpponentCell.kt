package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import scooterdelta.challenge.bot.common.lookup.Code

class OpponentCell(

        @JsonProperty("X")
        x: Int,

        @JsonProperty("Y")
        y: Int,

        @JsonProperty("Damaged")
        val damaged: Boolean,

        @JsonProperty("Missed")
        val missed: Boolean

) : BaseCell(x, y), Comparable<OpponentCell> {

    @JsonIgnore
    val attackTypeProbability: MutableMap<Code, Long> = mutableMapOf()

    override fun printContent(): String {
        when {
            missed -> {
                return "+"
            }
            damaged -> {
                return "X"
            }
            else -> {
                return "0"
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        if (!super.equals(other)) return false

        other as OpponentCell

        if (damaged != other.damaged) return false
        if (missed != other.missed) return false
        if (attackTypeProbability != other.attackTypeProbability) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + damaged.hashCode()
        result = 31 * result + missed.hashCode()
        result = 31 * result + attackTypeProbability.hashCode()
        return result
    }

    override fun toString(): String {
        return "OpponentCell(damaged=$damaged, missed=$missed, attackTypeProbability=$attackTypeProbability) ${super.toString()}"
    }

    override fun compareTo(other: OpponentCell): Int {
        var value = 0L
        var otherValue = 0L
        if (other.attackTypeProbability[Code.FIRE_SHOT] != null) {
            otherValue = other.attackTypeProbability[Code.FIRE_SHOT] as Long
        }
        if (attackTypeProbability[Code.FIRE_SHOT] != null) {
            value = attackTypeProbability[Code.FIRE_SHOT] as Long
        }
        return value.compareTo(otherValue)
    }

}
