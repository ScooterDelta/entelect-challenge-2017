package scooterdelta.challenge.bot.process.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.io.Files
import scooterdelta.challenge.bot.common.lookup.StateLookup
import scooterdelta.challenge.bot.common.state.game.UserState
import scooterdelta.challenge.bot.common.state.local.GameMode
import scooterdelta.challenge.bot.common.state.remote.GameState
import java.io.File
import java.nio.charset.Charset
import javax.inject.Inject

class GameStateDeserializer @Inject constructor(private val objectMapper: ObjectMapper) {

    fun deserialize(workingDirectory: File): GameState {
        return objectMapper.readValue(extractJson(workingDirectory, StateLookup.STATE.location), GameState::class.java)
    }

    private fun extractJson(workingDirectory: File, fileName: String): String {
        val workingFile = File(workingDirectory, fileName)
        return Files.asCharSource(workingFile, Charset.defaultCharset()).read()
    }

    fun saveUserState(userState: UserState) {
        objectMapper.writeValue(
                Files.newWriter(File(StateLookup.USER_STATE.location), Charset.defaultCharset()), userState)
    }

    fun readUserState(): UserState {
        val userStateFile = File(StateLookup.USER_STATE.location)
        if (userStateFile.isFile) {
            val userState = objectMapper.readValue(Files.asCharSource(userStateFile, Charset.defaultCharset()).read(), UserState::class.java)
            // Ensure user state is not null
            if (userState.attackCommands == null) {
                userState.attackCommands = mutableListOf()
            }
            return userState
        }
        return UserState(mutableListOf(), GameMode.SAVE)
    }

    fun deleteUserState() {
        val userStateFile = File(StateLookup.USER_STATE.location)
        userStateFile.delete()
    }
}
