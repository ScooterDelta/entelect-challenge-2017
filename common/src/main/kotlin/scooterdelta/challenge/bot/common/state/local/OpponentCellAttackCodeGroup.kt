package scooterdelta.challenge.bot.common.state.local

import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.common.state.remote.domain.Weapon

data class OpponentCellAttackCodeGroup(
        val code: Code,
        val cell: OpponentCell,
        val weapon: Weapon,
        val probability: Long
) : Comparable<OpponentCellAttackCodeGroup> {

    override fun compareTo(other: OpponentCellAttackCodeGroup): Int {
        return weapon.energyRequired.compareTo(other.weapon.energyRequired)
    }
}
