package scooterdelta.challenge.bot.process;

import com.google.common.io.Resources;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scooterdelta.challenge.bot.common.lookup.StateLookup;
import scooterdelta.challenge.bot.common.state.GameState;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class ProcessEngine implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessEngine.class);

    private final File workingDirectory;
    private final String playerKey;
    private final Gson gson;

    @Inject
    public ProcessEngine(final File workingDirectory,
                         final String playerKey,
                         final Gson gson) {
        this.workingDirectory = workingDirectory;
        this.playerKey = playerKey;
        this.gson = gson;
    }

    @Override
    public void run() {
        LOGGER.debug("Injection is working! Got these parameters: {} and {}", playerKey, workingDirectory);

        try {
            final GameState gameState = gson.fromJson(extractJson(workingDirectory, StateLookup.STATE.getLocation()), GameState.class);
            LOGGER.info("Deserialized game state: {}", gameState);
        } catch (final IOException ex) {
            LOGGER.error("Error extracting state object: {}", ex);
        }
    }

    private String extractJson(final File workingDirectory, final String fileName) throws IOException {
        final File workingFile = new File(workingDirectory, fileName);
        return Resources.toString(workingFile.toURI().toURL(), Charset.defaultCharset());
    }
}
