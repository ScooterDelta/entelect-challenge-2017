package scooterdelta.challenge.bot.common.state.local.helper

import scooterdelta.challenge.bot.common.state.local.Direction

fun fromInt(int: Int): Direction {
    assert(int < Direction.values().size)

    Direction.values()
            .filterIndexed { counter, _ -> counter == int }
            .forEach { return it }

    throw IllegalStateException()
}
