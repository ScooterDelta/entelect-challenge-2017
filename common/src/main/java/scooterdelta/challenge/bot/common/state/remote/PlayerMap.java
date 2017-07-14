package scooterdelta.challenge.bot.common.state.remote;

import com.google.gson.annotations.SerializedName;
import scooterdelta.challenge.bot.common.state.remote.domain.Cell;

import java.util.ArrayList;
import java.util.Objects;

public class PlayerMap {

    @SerializedName("Owner")
    private BattleshipPlayer owner;
    @SerializedName("Cells")
    private ArrayList<Cell> cells;
    @SerializedName("MapWidth")
    private int mapWidth;
    @SerializedName("MapHeight")
    private int mapHeight;

    public BattleshipPlayer getOwner() {
        return owner;
    }

    public void setOwner(final BattleshipPlayer owner) {
        this.owner = owner;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(final ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(final int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(final int mapHeight) {
        this.mapHeight = mapHeight;
    }

    @Override
    public String toString() {
        return "PlayerMap{" +
                "owner=" + owner +
                ", cells=" + cells +
                ", mapWidth=" + mapWidth +
                ", mapHeight=" + mapHeight +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PlayerMap playerMap = (PlayerMap) o;
        return mapWidth == playerMap.mapWidth &&
                mapHeight == playerMap.mapHeight &&
                Objects.equals(owner, playerMap.owner) &&
                Objects.equals(cells, playerMap.cells);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, cells, mapWidth, mapHeight);
    }
}
