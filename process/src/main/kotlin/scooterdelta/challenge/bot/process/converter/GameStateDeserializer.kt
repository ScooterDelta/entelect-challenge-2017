package scooterdelta.challenge.bot.process.converter

import com.google.common.io.Resources
import com.google.gson.Gson
import scooterdelta.challenge.bot.common.lookup.StateLookup
import scooterdelta.challenge.bot.common.state.GameState
import java.io.File
import java.nio.charset.Charset
import javax.inject.Inject

class GameStateDeserializer @Inject constructor(val gson: Gson) {

    fun deserialize(workingDirectory : File) : GameState {
        return gson.fromJson(extractJson(workingDirectory, StateLookup.STATE.location), GameState::class.java)
    }

    fun extractJson(workingDirectory: File, fileName : String) : String {
        val workingFile : File = File(workingDirectory, fileName)
        return Resources.toString(workingFile.toURI().toURL(), Charset.defaultCharset())
    }
}
