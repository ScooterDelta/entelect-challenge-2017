package scooterdelta.challenge.bot.module;

import dagger.Module;
import scooterdelta.challenge.bot.ChallengeApplication;
import scooterdelta.challenge.bot.process.module.ProcessModule;

@Module(
        injects = ChallengeApplication.class,
        includes = ProcessModule.class
)
public class ChallengeModule {
}
