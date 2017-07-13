package scooterdelta.challenge.bot.common.state.domain;

import scooterdelta.challenge.bot.common.lookup.ShipType;

import java.util.ArrayList;
import java.util.Objects;

public class Ship {

    private boolean destroyed;
    private boolean placed;
    private ShipType shipType;

    private ArrayList<Weapon> weapons;
    private ArrayList<Cell> cells;

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(final boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(final boolean placed) {
        this.placed = placed;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(final ShipType shipType) {
        this.shipType = shipType;
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(final ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(final ArrayList<Cell> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "destroyed=" + destroyed +
                ", placed=" + placed +
                ", shipType=" + shipType +
                ", weapons=" + weapons +
                ", cells=" + cells +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Ship ship = (Ship) o;
        return destroyed == ship.destroyed &&
                placed == ship.placed &&
                shipType == ship.shipType &&
                Objects.equals(weapons, ship.weapons) &&
                Objects.equals(cells, ship.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destroyed, placed, shipType, weapons, cells);
    }
}
