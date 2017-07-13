package scooterdelta.challenge.bot.process.module;

import dagger.Module;
import dagger.Provides;
import scooterdelta.challenge.bot.ChallengeApplication;
import scooterdelta.challenge.bot.process.ProcessEngine;

import java.io.File;

@Module(
        injects = ChallengeApplication.class
)
public class ProcessModule {

    private final File workingDirectory;
    private final String playerKey;

    public ProcessModule(final File workingDirectory, final String playerKey) {
        this.workingDirectory = workingDirectory;
        this.playerKey = playerKey;
    }

    @Provides
    ProcessEngine provideProcessEngine(final File workingDirectory, final String playerKey) {
        return new ProcessEngine(workingDirectory, playerKey);
    }

    @Provides
    File provideWorkingDirectory() {
        return workingDirectory;
    }

    @Provides
    String providePlayerKey() {
        return playerKey;
    }

}
