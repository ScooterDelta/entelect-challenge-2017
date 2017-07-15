package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonProperty

class OpponentCell(

        @JsonProperty("X")
        x: Int,

        @JsonProperty("Y")
        y: Int,

        @JsonProperty("Damaged")
        val damaged: Boolean,

        @JsonProperty("Missed")
        val missed: Boolean

) : BaseCell(x, y) {
    override fun printContent(): String {
        when {
            missed -> {return "+"}
            damaged -> {return "X"}
            else -> {return "0"}
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        if (!super.equals(other)) return false

        other as OpponentCell

        if (damaged != other.damaged) return false
        if (missed != other.missed) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + damaged.hashCode()
        result = 31 * result + missed.hashCode()
        return result
    }

    override fun toString(): String {
        return "OpponentCell(damaged=$damaged, missed=$missed) ${super.toString()}"
    }

}
