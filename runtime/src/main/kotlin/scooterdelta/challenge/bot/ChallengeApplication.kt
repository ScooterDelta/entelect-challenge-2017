package scooterdelta.challenge.bot

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import scooterdelta.challenge.bot.component.ChallengeComponent
import scooterdelta.challenge.bot.component.DaggerChallengeComponent
import scooterdelta.challenge.bot.process.module.ProcessModule
import java.io.File

object ChallengeApplication {

    val LOGGER : Logger = LoggerFactory.getLogger(ChallengeApplication.javaClass)

    @JvmStatic
    fun main(args: Array<String>) {
        val startTime : Long = System.nanoTime()
        runBot(args)
        val endTime : Long = System.nanoTime()

        LOGGER.debug(String.format("Bot finished in %d ms.", (endTime - startTime) / 1000000))
    }

    fun runBot(args: Array<String>) {

        if (args.size != 2) {
            printError()
            System.exit(1)
        }

        val key : String = args[0]
        val workingDir : File = File(args[1])

        if (!workingDir.exists()) {
            printError()
            LOGGER.error("Error: Working directory \" {} \" does not exist.", args[1])
            System.exit(1)
        }

        val challengeComponent : ChallengeComponent = DaggerChallengeComponent.builder()
                .processModule(ProcessModule(workingDir, key))
                .build()

        // Run the application
        challengeComponent.getChallengeRunner().run()
    }

    fun printError() {
        LOGGER.error("Java SampleBot usage: sample-bot.jar <PlayerKey> <WorkingDirectoryFilename>")
        LOGGER.error("\tPlayerKey\tThe key assigned to your bot.")
        LOGGER.error("\tWorkingDirectoryFilename\tThe working directory folder where the match runner will output map and state files and look for the move file.")
    }
}
