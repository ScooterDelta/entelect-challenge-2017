package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonProperty

class Cell(

        @JsonProperty("X")
        x: Int,

        @JsonProperty("Y")
        y: Int,

        @JsonProperty("Occupied")
        private val occupied: Boolean,

        @JsonProperty("Hit")
        private val hit: Boolean,

        @JsonProperty("Shielded")
        private val shielded: Boolean,

        @JsonProperty("ShieldHit")
        private val shieldHit: Boolean

) : BaseCell(x, y) {
    override fun printContent(): String {
        return when {
            occupied -> {
                "+"
            }
            hit -> {
                "X"
            }
            shieldHit -> {
                "@"
            }
            shielded -> {
                "%"
            }
            else -> {
                "0"
            }
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
