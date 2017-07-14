package scooterdelta.challenge.bot.process.converter;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import scooterdelta.challenge.bot.common.lookup.StateLookup;
import scooterdelta.challenge.bot.common.state.GameState;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class GameStateDeserializer {

    private final Gson gson;

    @Inject
    public GameStateDeserializer(Gson gson) {
        this.gson = gson;
    }

    public GameState deserialize(final File workingDirectory) throws IOException {
        return gson.fromJson(extractJson(workingDirectory, StateLookup.STATE.getLocation()), GameState.class);
    }

    private String extractJson(final File workingDirectory, final String fileName) throws IOException {
        final File workingFile = new File(workingDirectory, fileName);
        return Resources.toString(workingFile.toURI().toURL(), Charset.defaultCharset());
    }
}
