package scooterdelta.challenge.bot.component

import dagger.Component
import scooterdelta.challenge.bot.process.module.ProcessModule
import scooterdelta.challenge.bot.runner.ChallengeRunner

@Component(
        modules = arrayOf(ProcessModule::class)
)
interface ChallengeComponent {

    fun getChallengeRunner() : ChallengeRunner

}
