package scooterdelta.challenge.bot.common.state;

import scooterdelta.challenge.bot.common.state.domain.OpponentCell;

import java.util.ArrayList;
import java.util.Objects;

public class OpponentMap {

    private boolean alive;
    private int points;
    private String name;
    private ArrayList<OpponentShip> ships;
    private ArrayList<OpponentCell> cells;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(final boolean alive) {
        this.alive = alive;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(final int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<OpponentShip> getShips() {
        return ships;
    }

    public void setShips(final ArrayList<OpponentShip> ships) {
        this.ships = ships;
    }

    public ArrayList<OpponentCell> getCells() {
        return cells;
    }

    public void setCells(final ArrayList<OpponentCell> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "OpponentMap{" +
                "alive=" + alive +
                ", points=" + points +
                ", name='" + name + '\'' +
                ", ships=" + ships +
                ", cells=" + cells +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OpponentMap that = (OpponentMap) o;
        return alive == that.alive &&
                points == that.points &&
                Objects.equals(name, that.name) &&
                Objects.equals(ships, that.ships) &&
                Objects.equals(cells, that.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alive, points, name, ships, cells);
    }
}
