package scooterdelta.challenge.bot.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.File;

public class ProcessEngine implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessEngine.class);

    private final File workingDirectory;
    private final String playerKey;

    @Inject
    public ProcessEngine(final File workingDirectory,
                         final String playerKey) {
        this.workingDirectory = workingDirectory;
        this.playerKey = playerKey;
    }

    @Override
    public void run() {
        LOGGER.debug("Injection is working! Got these parameters: {} and {}", playerKey, workingDirectory);
    }
}
