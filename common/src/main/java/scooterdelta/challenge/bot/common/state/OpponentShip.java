package scooterdelta.challenge.bot.common.state;

import scooterdelta.challenge.bot.common.lookup.ShipType;

import java.util.Objects;

public class OpponentShip {

    private boolean destroyed;
    private ShipType shipType;

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(final boolean destroyed) {
        this.destroyed = destroyed;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(final ShipType shipType) {
        this.shipType = shipType;
    }

    @Override
    public String toString() {
        return "OpponentShip{" +
                "destroyed=" + destroyed +
                ", shipType=" + shipType +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OpponentShip that = (OpponentShip) o;
        return destroyed == that.destroyed &&
                shipType == that.shipType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(destroyed, shipType);
    }
}