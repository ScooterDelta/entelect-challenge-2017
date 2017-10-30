package scooterdelta.challenge.bot.process.processes.attack

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.common.state.local.GameMode
import scooterdelta.challenge.bot.common.state.local.ProcessOutcomes
import scooterdelta.challenge.bot.common.state.remote.GameState
import scooterdelta.challenge.bot.common.state.remote.domain.Weapon
import scooterdelta.challenge.bot.process.processes.Process

class UpdateGameModeState : Process {

    private val logger: Logger = LoggerFactory.getLogger(UpdateGameModeState::class.java)

    override fun process(gameState: GameState, processOutcomes: ProcessOutcomes) {
        val cheapest = extractCheapestWeapon(gameState)
        val expensive = extractExpensiveWeapon(gameState)
        logger.info("Game mode is: {}", processOutcomes.gameMode)

        // Cannot extract cheapest and most expensive - fallback to spend behavior
        if (cheapest == null || expensive == null) {
            processOutcomes.gameMode = GameMode.SPEND
            logger.error("Changing game mode to {} due to weapon extraction error", processOutcomes.gameMode)
        } else {
            if (expensive.energyRequired * 2 < gameState.playerMap.owner.energy) {
                processOutcomes.gameMode = GameMode.SPEND
                logger.info("Setting game mode to {} due to high energy")
            }
            if (cheapest.energyRequired > gameState.playerMap.owner.energy) {
                processOutcomes.gameMode = GameMode.SAVE
                logger.info("Setting game mode to {} due to low energy")
            }
        }
    }

    private fun extractCheapestWeapon(gameState: GameState): Weapon? {
        val weapons: MutableList<Weapon> = mutableListOf()
        gameState.playerMap.owner.ships.flatMapTo(weapons, { it.weapons })
        return weapons.minBy { weapon -> weapon.energyRequired }
    }

    private fun extractExpensiveWeapon(gameState: GameState): Weapon? {
        val weapons: MutableList<Weapon> = mutableListOf()
        gameState.playerMap.owner.ships.flatMapTo(weapons, { it.weapons })
        return weapons.maxBy { weapon -> weapon.energyRequired }
    }
}
