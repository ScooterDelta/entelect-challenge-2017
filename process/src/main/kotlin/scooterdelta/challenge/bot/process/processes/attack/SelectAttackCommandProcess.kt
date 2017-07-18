package scooterdelta.challenge.bot.process.processes.attack

import com.google.common.collect.SortedSetMultimap
import com.google.common.collect.TreeMultimap
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.command.AttackCommand
import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.lookup.StateLookup
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.process.processes.Process
import java.util.*

class SelectAttackCommandProcess(private val randomGenerator: Random) : Process {

    val LOGGER: Logger = LoggerFactory.getLogger(SelectAttackCommandProcess::class.java)

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {
        val opponentCells: List<OpponentCell> = ArrayList(gameState.opponentMap.cells)
        val sortedMap: SortedSetMultimap<Long, OpponentCell> = determineSortedMap(opponentCells)
        val opponentCell: OpponentCell = getMaxRandom(sortedMap)

        processOutcomes.command = AttackCommand(opponentCell.getPoint(), Code.FIRE_SHOT)
        processOutcomes.stateLookup = StateLookup.COMMAND

        LOGGER.info("Sending attack command: ${processOutcomes.command}")
    }

    private fun getMaxRandom(sortedMap: SortedSetMultimap<Long, OpponentCell>): OpponentCell {
        val sortedSet: SortedSet<OpponentCell> = sortedMap[sortedMap.keySet().first()]

        return sortedSet.elementAt(randomGenerator.nextInt(sortedSet.size))
    }

    private fun determineSortedMap(cells: List<OpponentCell>): SortedSetMultimap<Long, OpponentCell> {
        val sortedMap: SortedSetMultimap<Long, OpponentCell> = TreeMultimap.create(
                { o1, o2 -> o2.compareTo(o1) },
                { o1, o2 -> o2.singleShotHitChance.compareTo(o1.singleShotHitChance) })

        cells.forEach { sortedMap.put(it.singleShotHitChance, it) }
        return sortedMap
    }

}