package scooterdelta.challenge.bot.process.module

import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import scooterdelta.challenge.bot.common.state.local.FileState
import scooterdelta.challenge.bot.process.converter.GameStateDeserializer
import scooterdelta.challenge.bot.process.processes.Process
import scooterdelta.challenge.bot.process.processes.attack.BuildProbabilityMapProcess
import scooterdelta.challenge.bot.process.processes.placement.BasicPlacementImpl
import java.io.File
import javax.inject.Named

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

    @Provides
    @Named("placementProcesses")
    fun providePlacementProcesses(): ArrayList<Process> {
        return arrayListOf(BasicPlacementImpl())
    }

    @Provides
    @Named("attackProcesses")
    fun provideAttackProcesses(): ArrayList<Process> {
        return arrayListOf(BuildProbabilityMapProcess())
    }
}
