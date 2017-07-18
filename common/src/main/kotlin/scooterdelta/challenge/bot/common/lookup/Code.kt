package scooterdelta.challenge.bot.common.lookup

enum class Code(val value: Int) {
    DO_NOTHING(0),
    FIRE_SHOT(1),
    FIRE_DOUBLE_SHOT_VERTICAL(2),
    FIRE_DOUBLE_SHOT_HORIZONTAL(3),
    FIRE_CORNER_SHOT(4),
    FIRE_CROSS_SHOT_DIAGONAL(5),
    FIRE_CROSS_SHOT_HORIZONTAL(6),
    FIRE_SEEKER_MISSILE(7);
}
