package scooterdelta.challenge.bot.module;

import dagger.Component;
import scooterdelta.challenge.bot.ChallengeApplication;
import scooterdelta.challenge.bot.process.module.ProcessModule;

@Component(
        modules = {
                ProcessModule.class
        }
)
public interface ChallengeComponent {

    ChallengeApplication getChallengeApplication();

}
