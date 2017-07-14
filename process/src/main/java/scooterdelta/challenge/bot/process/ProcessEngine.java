package scooterdelta.challenge.bot.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scooterdelta.challenge.bot.common.local.FileState;
import scooterdelta.challenge.bot.common.state.GameState;
import scooterdelta.challenge.bot.process.converter.GameStateDeserializer;

import javax.inject.Inject;
import java.io.IOException;

public class ProcessEngine implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessEngine.class);

    private final FileState fileState;
    private final GameStateDeserializer deserializer;

    @Inject
    public ProcessEngine(final FileState fileState,
                         final GameStateDeserializer deserializer) {
        this.fileState = fileState;
        this.deserializer = deserializer;
    }

    @Override
    public void run() {
        LOGGER.debug("Injection is working! Got these parameters: {} and {}", fileState.getPlayerKey(), fileState.getWorkingDirectory());

        try {
            final GameState gameState = deserializer.deserialize(fileState.getWorkingDirectory());
            LOGGER.info("Deserialized game state: {}", gameState);
        } catch (final IOException ex) {
            LOGGER.error("Error extracting state object: {}", ex);
        }
    }
}
