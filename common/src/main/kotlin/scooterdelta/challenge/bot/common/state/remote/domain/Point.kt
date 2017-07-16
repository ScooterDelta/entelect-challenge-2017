package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonProperty

class Point(

        @JsonProperty("X")
        x: Int,

        @JsonProperty("Y")
        y: Int

) : BaseCell(x, y) {

    override fun printContent(): String {
        return "x"
    }

}
