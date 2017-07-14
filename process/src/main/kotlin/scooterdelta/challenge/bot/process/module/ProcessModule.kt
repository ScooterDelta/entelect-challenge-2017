package scooterdelta.challenge.bot.process.module

import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import scooterdelta.challenge.bot.common.state.local.FileState
import scooterdelta.challenge.bot.process.converter.GameStateDeserializer
import java.io.File

@Module
class ProcessModule(private val workingDirectory: File,
                    private val playerKey: String) {

    @Provides
    fun provideFileState(): FileState {
        return FileState(workingDirectory, playerKey)
    }

    @Provides
    fun provideObjectMapper(): ObjectMapper {
        return ObjectMapper()
    }

    @Provides
    fun provideDeserializer(objectMapper: ObjectMapper): GameStateDeserializer {
        return GameStateDeserializer(objectMapper)
    }
}
