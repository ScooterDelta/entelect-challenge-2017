package scooterdelta.challenge.bot.process.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.io.Resources
import scooterdelta.challenge.bot.common.lookup.StateLookup
import scooterdelta.challenge.bot.common.state.remote.GameState
import java.io.File
import java.nio.charset.Charset
import javax.inject.Inject

class GameStateDeserializer @Inject constructor(private val objectMapper: ObjectMapper) {

    fun deserialize(workingDirectory : File) : GameState {
        return objectMapper.readValue(extractJson(workingDirectory, StateLookup.STATE.location), GameState::class.java)
    }

    fun extractJson(workingDirectory: File, fileName : String) : String {
        val workingFile : File = File(workingDirectory, fileName)
        return Resources.toString(workingFile.toURI().toURL(), Charset.defaultCharset())
    }
}
