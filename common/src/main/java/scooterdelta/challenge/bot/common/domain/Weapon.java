package scooterdelta.challenge.bot.common.domain;

import scooterdelta.challenge.bot.common.lookup.WeaponType;

import java.util.Objects;

public class Weapon {

    private WeaponType weaponType;

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(final WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "weaponType=" + weaponType +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Weapon weapon = (Weapon) o;
        return weaponType == weapon.weaponType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weaponType);
    }
}
