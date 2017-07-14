package scooterdelta.challenge.bot.common.state.local

import java.io.File

class FileState (val workingDirectory : File, val playerKey : String) {

    override fun toString(): String {
        return "FileState(workingDirectory=$workingDirectory, playerKey='$playerKey')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as FileState

        if (workingDirectory != other.workingDirectory) return false
        if (playerKey != other.playerKey) return false

        return true
    }

    override fun hashCode(): Int {
        var result = workingDirectory.hashCode()
        result = 31 * result + playerKey.hashCode()
        return result
    }
}