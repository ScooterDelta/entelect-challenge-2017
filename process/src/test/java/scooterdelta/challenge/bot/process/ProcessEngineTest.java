package scooterdelta.challenge.bot.process;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import scooterdelta.challenge.bot.common.local.FileState;
import scooterdelta.challenge.bot.process.converter.GameStateDeserializer;

import java.io.File;

import static com.google.common.io.Resources.getResource;

public class ProcessEngineTest {

    private static final String PLAYER_KEY = "B";

    private ProcessEngine processEngine;

    @Before
    public void setUp() throws Exception {
        final File workingDir = new File(getResource("data/").toURI());
        processEngine = new ProcessEngine(new FileState(workingDir, PLAYER_KEY), new GameStateDeserializer(new Gson()));
    }

    @Test
    public void runProcessEngine() throws Exception {
        processEngine.run();
    }

}
