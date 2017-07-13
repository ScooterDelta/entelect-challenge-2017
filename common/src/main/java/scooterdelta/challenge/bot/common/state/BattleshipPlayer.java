package scooterdelta.challenge.bot.common.state;

import scooterdelta.challenge.bot.common.state.domain.Ship;

import java.util.ArrayList;
import java.util.Objects;

public class BattleshipPlayer {

    private int failedFirstRoundCommands;
    private String name;
    private ArrayList<Ship> ships;
    private int points;
    private int energy;
    private boolean killed;
    private boolean isWinner;
    private int shotsFired;
    private int shotsHit;
    private int shipsRemaining;
    private char key;

    public int getFailedFirstRoundCommands() {
        return failedFirstRoundCommands;
    }

    public void setFailedFirstRoundCommands(final int failedFirstRoundCommands) {
        this.failedFirstRoundCommands = failedFirstRoundCommands;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(final ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(final int points) {
        this.points = points;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(final int energy) {
        this.energy = energy;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(final boolean killed) {
        this.killed = killed;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(final boolean winner) {
        isWinner = winner;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public void setShotsFired(final int shotsFired) {
        this.shotsFired = shotsFired;
    }

    public int getShotsHit() {
        return shotsHit;
    }

    public void setShotsHit(final int shotsHit) {
        this.shotsHit = shotsHit;
    }

    public int getShipsRemaining() {
        return shipsRemaining;
    }

    public void setShipsRemaining(final int shipsRemaining) {
        this.shipsRemaining = shipsRemaining;
    }

    public char getKey() {
        return key;
    }

    public void setKey(final char key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "BattleshipPlayer{" +
                "failedFirstRoundCommands=" + failedFirstRoundCommands +
                ", name='" + name + '\'' +
                ", ships=" + ships +
                ", points=" + points +
                ", energy=" + energy +
                ", killed=" + killed +
                ", isWinner=" + isWinner +
                ", shotsFired=" + shotsFired +
                ", shotsHit=" + shotsHit +
                ", shipsRemaining=" + shipsRemaining +
                ", key=" + key +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BattleshipPlayer that = (BattleshipPlayer) o;
        return failedFirstRoundCommands == that.failedFirstRoundCommands &&
                points == that.points &&
                energy == that.energy &&
                killed == that.killed &&
                isWinner == that.isWinner &&
                shotsFired == that.shotsFired &&
                shotsHit == that.shotsHit &&
                shipsRemaining == that.shipsRemaining &&
                key == that.key &&
                Objects.equals(name, that.name) &&
                Objects.equals(ships, that.ships);
    }

    @Override
    public int hashCode() {
        return Objects.hash(failedFirstRoundCommands, name, ships, points, energy, killed, isWinner, shotsFired, shotsHit, shipsRemaining, key);
    }
}
