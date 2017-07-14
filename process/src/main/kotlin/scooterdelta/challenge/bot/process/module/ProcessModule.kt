package scooterdelta.challenge.bot.process.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import scooterdelta.challenge.bot.common.state.local.FileState
import scooterdelta.challenge.bot.process.converter.GameStateDeserializer
import java.io.File

@Module
class ProcessModule (val workingDirectory : File, val playerKey : String) {

    @Provides
    fun provideFileState() : FileState {
        return FileState(workingDirectory, playerKey)
    }

    @Provides
    fun provideGson() : Gson {
        return Gson()
    }

    @Provides
    fun provideDeserializer(gson : Gson) : GameStateDeserializer {
        return GameStateDeserializer(gson)
    }
}
