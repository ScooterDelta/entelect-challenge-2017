package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonProperty

class Cell(

        @JsonProperty("X")
        x: Int,

        @JsonProperty("Y")
        y: Int,

        @JsonProperty("Occupied")
        val occupied: Boolean,

        @JsonProperty("Hit")
        val hit: Boolean

) : BaseCell(x, y) {
    override fun printContent(): String {
        when {
            occupied -> {return "+"}
            hit -> {return "X"}
            else -> {return "0"}
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false
        if (!super.equals(other)) return false

        other as Cell

        if (occupied != other.occupied) return false
        if (hit != other.hit) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + occupied.hashCode()
        result = 31 * result + hit.hashCode()
        return result
    }

    override fun toString(): String {
        return "Cell(occupied=$occupied, hit=$hit) ${super.toString()}"
    }

}
