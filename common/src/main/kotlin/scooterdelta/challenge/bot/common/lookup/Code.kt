package scooterdelta.challenge.bot.common.lookup

enum class Code(val value: Int,
                val weaponType: WeaponType) {

    DO_NOTHING(0, WeaponType.NONE),

    /**
     * Fire a single shot at target point
     *
     *     (*)
     */
    FIRE_SHOT(1, WeaponType.SINGLE_SHOT),

    /**
     * Creates a vertical line pattern (excludes center point)
     *
     *      *
     *     ( )
     *      *
     */
    FIRE_DOUBLE_SHOT_VERTICAL(2, WeaponType.DOUBLE_SHOT),

    /**
     * Creates a horizontal line pattern (excludes center point)
     *
     *   * ( ) *
     */
    FIRE_DOUBLE_SHOT_HORIZONTAL(3, WeaponType.DOUBLE_SHOT),

    /**
     * Creates a corner shot pattern (excludes center point)
     *
     *    *   *
     *     ( )
     *    *   *
     */
    FIRE_CORNER_SHOT(4, WeaponType.CORNER_SHOT),

    /**
     * Creates a diagonal cross shot pattern
     *
     *    *   *
     *     (*)
     *    *   *
     */
    FIRE_CROSS_SHOT_DIAGONAL(5, WeaponType.DIAGONAL_CROSS_SHOT),

    /**
     * Creates a horizontal cross shot pattern
     *
     *      *
     *   * (*) *
     *      *
     */
    FIRE_CROSS_SHOT_HORIZONTAL(6, WeaponType.CROSS_SHOT),

    /**
     * Hits a cell in a block of possible locations:
     *
     *       0
     *     0 0 0
     *   0 0(*)0 0
     *     0 0 0
     *       0
     */
    FIRE_SEEKER_MISSILE(7, WeaponType.SEEKER_MISSILE),

    /**
     * Shield a ship.
     */
    SHIELD(8, WeaponType.NONE);
}
