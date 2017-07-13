package scooterdelta.challenge.bot;

import dagger.ObjectGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scooterdelta.challenge.bot.module.ChallengeModule;
import scooterdelta.challenge.bot.process.ProcessEngine;
import scooterdelta.challenge.bot.process.module.ProcessModule;

import javax.inject.Inject;
import java.io.File;

import static dagger.ObjectGraph.create;

public class ChallengeApplication implements Runnable {

    private final ProcessEngine processEngine;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChallengeApplication.class);

    @Inject
    public ChallengeApplication(final ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @Override
    public void run() {
        processEngine.run();
    }

    public static void main(final String[] args) {

        final long startTime = System.nanoTime();
        runBot(args);
        final long endTime = System.nanoTime();

        LOGGER.debug(String.format("Bot finished in %d ms.", (endTime - startTime) / 1000000));
    }

    private static void runBot(final String... args) {

        if (args.length != 2) {
            printUsage();
            System.exit(1);
        }

        final String key = args[0];
        final File workingDir = new File(args[1]);

        if (!workingDir.exists()) {
            printUsage();
            LOGGER.debug(String.format("Error: Working directory \" %s \" does not exist.", args[1]));
            System.exit(1);
        }

        final ObjectGraph objectGraph = create(
                new ChallengeModule(),
                new ProcessModule(workingDir, key)
        );
        final ChallengeApplication challengeApplication = objectGraph.get(ChallengeApplication.class);

        challengeApplication.run();
    }

    private static void printUsage() {

        LOGGER.debug("Java SampleBot usage: sample-bot.jar <PlayerKey> <WorkingDirectoryFilename>");
        LOGGER.debug("\tPlayerKey\tThe key assigned to your bot.");
        LOGGER.debug("\tWorkingDirectoryFilename\tThe working directory folder where the match runner will output map and state files and look for the move file.");
    }
}
