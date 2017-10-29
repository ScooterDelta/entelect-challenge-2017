package scooterdelta.challenge.bot.process.converter

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import scooterdelta.challenge.bot.common.command.AttackCommand
import scooterdelta.challenge.bot.common.lookup.Code
import scooterdelta.challenge.bot.common.state.game.UserState
import scooterdelta.challenge.bot.common.state.remote.domain.Point

class GameStateDeserializerTest {

    lateinit var gameStateDeserializer: GameStateDeserializer

    @Before
    fun setUp() {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(KotlinModule())
        gameStateDeserializer = GameStateDeserializer(objectMapper)
    }

    @Test
    fun testReadWriteUserState() {
        val userState = UserState(mutableListOf(AttackCommand(Point(5, 5), Code.FIRE_SHOT)))

        gameStateDeserializer.deleteUserState()
        gameStateDeserializer.saveUserState(userState)

        val deserialized = gameStateDeserializer.readUserState()

        gameStateDeserializer.deleteUserState()
        assertThat("The user states are the same", deserialized, equalTo(userState))
    }

    @Test
    fun testReadWriteUpdateUserState() {
        val userState = UserState(mutableListOf(AttackCommand(Point(5, 5), Code.FIRE_SHOT)))

        gameStateDeserializer.deleteUserState()
        gameStateDeserializer.saveUserState(userState)

        val deserialized = gameStateDeserializer.readUserState()
        assertThat("The user states are the same", deserialized, equalTo(userState))

        deserialized.attackCommands!!.add(AttackCommand(Point(123, 1234), Code.FIRE_CORNER_SHOT))
        gameStateDeserializer.saveUserState(deserialized)

        val deserializedAgain = gameStateDeserializer.readUserState()
        gameStateDeserializer.deleteUserState()
        assertThat("The user states are the same", deserializedAgain, equalTo(deserialized))
    }

}