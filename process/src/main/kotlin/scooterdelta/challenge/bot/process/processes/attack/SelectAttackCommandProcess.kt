package scooterdelta.challenge.bot.process.processes.attack

import com.google.common.collect.SortedSetMultimap
import com.google.common.collect.TreeMultimap
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

class SelectAttackCommandProcess : Process {

    val LOGGER: Logger = LoggerFactory.getLogger(SelectAttackCommandProcess::class.java)

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {
        val opponentCells: List<OpponentCell> = ArrayList(gameState.opponentMap.cells)
        val sortedMap: SortedSetMultimap<Long, OpponentCellAttackCodeGroup> = determineSortedMap(opponentCells, gameState.playerMap.owner)

        val cellGroup: OpponentCellAttackCodeGroup = getMaxProbabilityCheapestEnergy(sortedMap)

        processOutcomes.command = AttackCommand(cellGroup.cell.getPoint(), cellGroup.code)
        processOutcomes.stateLookup = StateLookup.COMMAND

        LOGGER.info("Sending attack command: ${processOutcomes.command}")
    }

    private fun getMaxProbabilityCheapestEnergy(sortedMap: SortedSetMultimap<Long, OpponentCellAttackCodeGroup>): OpponentCellAttackCodeGroup {
        val sortedSet: SortedSet<OpponentCellAttackCodeGroup> = sortedMap[sortedMap.keySet().first()]

        return sortedSet.first()
    }

    /**
     * Places all attacks with energy enough to place in probability map
     */
    private fun determineSortedMap(cells: List<OpponentCell>, player: BattleshipPlayer): SortedSetMultimap<Long, OpponentCellAttackCodeGroup> {
        val sortedMap: SortedSetMultimap<Long, OpponentCellAttackCodeGroup> = TreeMultimap.create(
                { o1, o2 -> o2.compareTo(o1) },
                { o1, o2 -> o1.compareTo(o2) })

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