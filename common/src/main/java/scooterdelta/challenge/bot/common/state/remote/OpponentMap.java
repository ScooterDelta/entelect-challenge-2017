package scooterdelta.challenge.bot.common.state.remote;

import com.google.gson.annotations.SerializedName;
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell;

import java.util.ArrayList;
import java.util.Objects;

public class OpponentMap {

    @SerializedName("Alive")
    private boolean alive;
    @SerializedName("Points")
    private int points;
    @SerializedName("Name")
    private String name;
    @SerializedName("Ships")
    private ArrayList<OpponentShip> ships;
    @SerializedName("Cells")
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
