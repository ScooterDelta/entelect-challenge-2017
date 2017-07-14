package scooterdelta.challenge.bot.common.state.remote.domain

import com.fasterxml.jackson.annotation.JsonProperty
import scooterdelta.challenge.bot.common.lookup.WeaponType

data class Weapon(

        @JsonProperty("WeaponType")
        val weaponType: WeaponType,

        @JsonProperty("EnergyRequired")
        val energyRequired: Int
)