package scooterdelta.challenge.bot.runner

import scooterdelta.challenge.bot.process.ProcessEngine
import javax.inject.Inject

class ChallengeRunner @Inject constructor(val processEngine: ProcessEngine) : Runnable {

    override fun run() {
        processEngine.run()
    }
}
