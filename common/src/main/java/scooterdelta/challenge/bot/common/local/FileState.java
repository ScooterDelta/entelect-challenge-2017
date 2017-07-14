package scooterdelta.challenge.bot.common.local;

import java.io.File;
import java.util.Objects;

public class FileState {

    private final File workingDirectory;
    private final String playerKey;

    public FileState(File workingDirectory, String playerKey) {
        this.workingDirectory = workingDirectory;
        this.playerKey = playerKey;
    }

    public File getWorkingDirectory() {
        return workingDirectory;
    }

    public String getPlayerKey() {
        return playerKey;
    }

    @Override
    public String toString() {
        return "FileState{" +
                "workingDirectory=" + workingDirectory +
                ", playerKey='" + playerKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileState fileState = (FileState) o;
        return Objects.equals(workingDirectory, fileState.workingDirectory) &&
                Objects.equals(playerKey, fileState.playerKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workingDirectory, playerKey);
    }
}
