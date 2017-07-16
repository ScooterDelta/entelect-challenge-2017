package scooterdelta.challenge.bot.process

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.io.Resources
import org.junit.Before
import org.junit.Test
import scooterdelta.challenge.bot.common.state.local.FileState
import scooterdelta.challenge.bot.process.converter.GameStateDeserializer
import scooterdelta.challenge.bot.process.processes.attack.BuildProbabilityMapProcess
import scooterdelta.challenge.bot.process.processes.placement.RandomPlacementImpl
import java.io.File
import java.util.*

class ProcessEngineTest {

    val PLAYER_KEY: String = "B"
    lateinit var processEngine: ProcessEngine

    @Before
    fun setUp() {
        val workingDir: File = File(Resources.getResource("data/").toURI())
        processEngine = ProcessEngine(
                FileState(workingDir, PLAYER_KEY),
                GameStateDeserializer(ObjectMapper()),
                arrayListOf(RandomPlacementImpl(Random())),
                arrayListOf(BuildProbabilityMapProcess()))
    }

    @Test
    fun runProcessEngine() {
        processEngine.run()
    }
}