package scooterdelta.challenge.bot.process.module;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import scooterdelta.challenge.bot.process.ProcessEngine;

import java.io.File;

@Module(
        injects = ProcessEngine.class
)
public class ProcessModule {

    private final File workingDirectory;
    private final String playerKey;

    public ProcessModule(final File workingDirectory, final String playerKey) {
        this.workingDirectory = workingDirectory;
        this.playerKey = playerKey;
    }

    @Provides
    File provideWorkingDirectory() {
        return workingDirectory;
    }

    @Provides
    String providePlayerKey() {
        return playerKey;
    }

    @Provides
    Gson provideGson() {
        return new Gson();
    }

}
