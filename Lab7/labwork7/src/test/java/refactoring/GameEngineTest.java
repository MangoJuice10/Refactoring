package refactoring;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameEngineTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void runsOnWindows() {
        GameEngine engine = new GameEngine("windows");
        engine.run();
    }

    @Test
    void runsOnLinux() {
        GameEngine engine = new GameEngine("linux");
        engine.run();
    }

    @Test
    void runsOnMac() {
        GameEngine engine = new GameEngine("mac");
        engine.run();
    }

    @Test
    void windowsOutputContainsExpectedTokens() {
        new GameEngine("windows").run();

        String log = out.toString();
        assertTrue(log.contains("WindowsWindow"));
        assertTrue(log.contains("WindowsCanvas"));
        assertTrue(log.contains("WindowsPen"));
    }

    @Test
    void linuxOutputContainsExpectedTokens() {
        new GameEngine("linux").run();

        String log = out.toString();
        assertTrue(log.contains("LinuxWindow"));
        assertTrue(log.contains("LinuxCanvas"));
        assertTrue(log.contains("LinuxPen"));
    }

    @Test
    void macOutputContainsExpectedTokens() {
        new GameEngine("mac").run();

        String log = out.toString();
        assertTrue(log.contains("MacWindow"));
        assertTrue(log.contains("MacCanvas"));
        assertTrue(log.contains("MacPen"));
    }
}