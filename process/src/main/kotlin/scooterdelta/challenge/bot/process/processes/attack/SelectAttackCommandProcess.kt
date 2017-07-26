package scooterdelta.challenge.bot.process.processes.attack

import com.google.common.collect.HashMultimap
import com.google.common.collect.SetMultimap
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.command.AttackCommand
import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.lookup.StateLookup
import scooterdelta.challenge.bot.common.lookup.WeaponType
import scooterdelta.challenge.bot.common.state.local.OpponentCellAttackCodeGroup
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.BattleshipPlayer
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.OpponentCell
import scooterdelta.challenge.bot.common.state.remote.domain.Weapon
import scooterdelta.challenge.bot.process.processes.Process
import java.util.*

class SelectAttackCommandProcess(private val random: Random) : Process {

    val LOGGER: Logger = LoggerFactory.getLogger(SelectAttackCommandProcess::class.java)

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {
        val opponentCells: List<OpponentCell> = ArrayList(gameState.opponentMap.cells)
        val sortedMap: SetMultimap<Long, OpponentCellAttackCodeGroup> = determineSortedMap(opponentCells, gameState.playerMap.owner)

        val cellGroup: OpponentCellAttackCodeGroup = getMaxProbabilityCheapestEnergy(sortedMap)

        processOutcomes.command = AttackCommand(cellGroup.cell.getPoint(), cellGroup.code)
        processOutcomes.stateLookup = StateLookup.COMMAND

        LOGGER.info("Sending attack command: ${processOutcomes.command}")
    }

    private fun getMaxProbabilityCheapestEnergy(sortedMap: SetMultimap<Long, OpponentCellAttackCodeGroup>): OpponentCellAttackCodeGroup {
        val keySet: SortedSet<Long> = TreeSet(sortedMap.keySet())
        val sortedSet: MutableSet<OpponentCellAttackCodeGroup> = sortedMap[keySet.last()]

        val energyMap: SetMultimap<Int, OpponentCellAttackCodeGroup> = HashMultimap.create()
        sortedSet.forEach { energyMap.put(it.weapon.energyRequired, it) }

        val energyKeys: SortedSet<Int> = TreeSet(energyMap.keySet())
        val energySet: MutableSet<OpponentCellAttackCodeGroup> = energyMap[energyKeys.first()]
        return energySet.elementAt(random.nextInt(energySet.size))
    }

    /**
     * Places all attacks with energy enough to place in probability map
     */
    private fun determineSortedMap(cells: List<OpponentCell>, player: BattleshipPlayer): SetMultimap<Long, OpponentCellAttackCodeGroup> {
        val sortedMap: SetMultimap<Long, OpponentCellAttackCodeGroup> = HashMultimap.create()

        cells.forEach {
            cell ->
            cell.attackTypeProbability.entries
                    .mapNotNull { (code, probability) -> createOpponentCellAttackGroup(code, cell, player, probability) }
                    .filter { group -> player.energy >= group.weapon.energyRequired }
                    .forEach { sortedMap.put(it.probability, it) }
        }
        return sortedMap
    }

    private fun createOpponentCellAttackGroup(code: Code, cell: OpponentCell, player: BattleshipPlayer, probability: Long): OpponentCellAttackCodeGroup? {
        val weapon: Weapon? = retrieveWeapon(code, player)

        if (weapon != null) {
            return OpponentCellAttackCodeGroup(code, cell, weapon, probability)
        }
        return null
    }

    /**
     * Only retrieves weapons of ships that are not destroyed and are placed
     */
    private fun retrieveWeapon(code: Code, player: BattleshipPlayer): Weapon? {
        val weaponType: WeaponType = code.weaponType

        return player.ships
                .filter { !it.destroyed }
                .filter { it.placed }
                .flatMap { it.weapons }
                .firstOrNull { it.weaponType == weaponType }
    }

}