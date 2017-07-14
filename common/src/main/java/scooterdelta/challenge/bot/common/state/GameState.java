package scooterdelta.challenge.bot.common.state;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class GameState {

    @SerializedName("PlayerMap")
    private PlayerMap playerMap;
    @SerializedName("OpponentMap")
    private OpponentMap opponentMap;
    @SerializedName("GameVersion")
    private String gameVersion;
    @SerializedName("GameLevel")
    private int gameLevel;
    @SerializedName("Round")
    private int round;
    @SerializedName("MapDimension")
    private int mapDimension;
    @SerializedName("Phase")
    private int phase;

    public PlayerMap getPlayerMap() {
        return playerMap;
    }

    public void setPlayerMap(final PlayerMap playerMap) {
        this.playerMap = playerMap;
    }

    public OpponentMap getOpponentMap() {
        return opponentMap;
    }

    public void setOpponentMap(final OpponentMap opponentMap) {
        this.opponentMap = opponentMap;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(final String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public int getGameLevel() {
        return gameLevel;
    }

    public void setGameLevel(final int gameLevel) {
        this.gameLevel = gameLevel;
    }

    public int getRound() {
        return round;
    }

    public void setRound(final int round) {
        this.round = round;
    }

    public int getMapDimension() {
        return mapDimension;
    }

    public void setMapDimension(final int mapDimension) {
        this.mapDimension = mapDimension;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(final int phase) {
        this.phase = phase;
    }

    @Override
    public String toString() {
        return "GameState{" +
                "playerMap=" + playerMap +
                ", opponentMap=" + opponentMap +
                ", gameVersion='" + gameVersion + '\'' +
                ", gameLevel=" + gameLevel +
                ", round=" + round +
                ", mapDimension=" + mapDimension +
                ", phase=" + phase +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final GameState gameState = (GameState) o;
        return gameLevel == gameState.gameLevel &&
                round == gameState.round &&
                mapDimension == gameState.mapDimension &&
                phase == gameState.phase &&
                Objects.equals(playerMap, gameState.playerMap) &&
                Objects.equals(opponentMap, gameState.opponentMap) &&
                Objects.equals(gameVersion, gameState.gameVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerMap, opponentMap, gameVersion, gameLevel, round, mapDimension, phase);
    }
}
