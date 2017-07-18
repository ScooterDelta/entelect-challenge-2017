package scooterdelta.challenge.bot.process

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.collect.SortedSetMultimap
import com.google.common.collect.TreeMultimap
import com.google.common.io.Resources
import org.junit.Before
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

    val PLAYER_KEY: String = "B"
    lateinit var processEngine: ProcessEngine

    @Test
    fun runProcessEngineAttack() {
        val workingDir: File = File(Resources.getResource("data/run").toURI())
        processEngine = buildProcessEngine(workingDir)
        processEngine.run()
    }

    @Test
    fun runProcessEngine() {
        val workingDir: File = File(Resources.getResource("data/start").toURI())
        processEngine = buildProcessEngine(workingDir)
        processEngine.run()
    }

    private fun buildProcessEngine(workingDir: File): ProcessEngine {
        return ProcessEngine(
                FileState(workingDir, PLAYER_KEY),
                GameStateDeserializer(ObjectMapper()),
                arrayListOf(
                        RandomPlacementImpl(Random())
                ),
                arrayListOf(
                        BuildProbabilityMapProcess(),
                        SpecialWeaponProbabilityMapProcess(arrayListOf(
                                DoubleShotVerticalCalculator(),
                                DoubleShotHorizontalCalculator(),
                                CornerShotCalculator(),
                                CrossDiagonalShotCalculator(),
                                CrossHorizontalShotCalculator(),
                                SeekerMissileCalculator()
                        )),

                        // RUN HUNT DESTROY LAST - Overrides previous probabilities while destroying ship
                        BuildHuntDestroyProbabilityMapProcess(),

                        // Select most likely weapon/location choice
                        SelectAttackCommandProcess()
                )
        )
    }
}