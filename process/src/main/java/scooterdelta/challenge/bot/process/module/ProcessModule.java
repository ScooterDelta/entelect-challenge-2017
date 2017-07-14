package scooterdelta.challenge.bot.process.module;

import com.google.gson.Gson;
import dagger.Module;
import dagger.Provides;
import scooterdelta.challenge.bot.common.local.FileState;
import scooterdelta.challenge.bot.process.converter.GameStateDeserializer;

import java.io.File;

@Module
public class ProcessModule {

    private final File workingDirectory;
    private final String playerKey;

    public ProcessModule(final File workingDirectory, final String playerKey) {
        this.workingDirectory = workingDirectory;
        this.playerKey = playerKey;
    }

    @Provides
    FileState provideFileState() {
        return new FileState(workingDirectory, playerKey);
    }

    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    GameStateDeserializer provideDeserializer(final Gson gson) {
        return new GameStateDeserializer(gson);
    }

}
