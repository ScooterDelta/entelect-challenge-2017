package scooterdelta.challenge.bot.process

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.Test
import scooterdelta.challenge.bot.common.state.local.FileState
import scooterdelta.challenge.bot.process.converter.GameStateDeserializer
import scooterdelta.challenge.bot.process.processes.attack.BuildHuntDestroyProbabilityMapProcess
import scooterdelta.challenge.bot.process.processes.attack.BuildProbabilityMapProcess
import scooterdelta.challenge.bot.process.processes.attack.SelectAttackCommandProcess
import scooterdelta.challenge.bot.process.processes.attack.SpecialWeaponProbabilityMapProcess
import scooterdelta.challenge.bot.process.processes.attack.special.*
import scooterdelta.challenge.bot.process.processes.placement.RandomPlacementImpl
import java.io.File
import java.util.*

class ProcessEngineTest {

    private val PLAYER_KEY: String = "B"
    private lateinit var processEngine: ProcessEngine

    @Test
    fun runProcessEngineAttack() {
//        val workingDir = File(Resources.getResource("data/run").toURI())
//        processEngine = buildProcessEngine(workingDir)
//        processEngine.run()
    }

    @Test
    fun runProcessEngine() {
//        val workingDir = File(Resources.getResource("data/start").toURI())
//        processEngine = buildProcessEngine(workingDir)
//        processEngine.run()
    }

    private fun buildProcessEngine(workingDir: File): ProcessEngine {
        return ProcessEngine(
                FileState(workingDir, PLAYER_KEY),
                GameStateDeserializer(ObjectMapper().registerKotlinModule()),
                arrayListOf(
                        RandomPlacementImpl(Random())
                ),
                arrayListOf(
                        BuildProbabilityMapProcess(),
                        BuildHuntDestroyProbabilityMapProcess(),
                        SpecialWeaponProbabilityMapProcess(arrayListOf(
                                DoubleShotVerticalCalculator(),
                                DoubleShotHorizontalCalculator(),
                                CornerShotCalculator(),
                                CrossDiagonalShotCalculator(),
                                CrossHorizontalShotCalculator(),
                                SeekerMissileCalculator()
                        )),
                        // Select most likely weapon/location choice
                        SelectAttackCommandProcess(Random())
                )
        )
    }
}