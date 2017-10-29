package scooterdelta.challenge.bot.common.state.local

import scooterdelta.challenge.bot.common.state.remote.domain.Point

data class Rectangle(
        val point: Point,
        val width: Int,
        val height: Int
)
