package refactoring;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import refactoring.UI.Factories.LinuxUIFactory;
import refactoring.UI.Factories.MacUIFactory;
import refactoring.UI.Factories.WindowsUIFactory;

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
        GameEngine engine = new GameEngine(new WindowsUIFactory());
        engine.run();
    }

    @Test
    void runsOnLinux() {
        GameEngine engine = new GameEngine(new LinuxUIFactory());
        engine.run();
    }

    @Test
    void runsOnMac() {
        GameEngine engine = new GameEngine(new MacUIFactory());
        engine.run();
    }

    @Test
    void windowsOutputContainsExpectedTokens() {
        new GameEngine(new WindowsUIFactory()).run();

        String log = out.toString();
        assertTrue(log.contains("WindowsWindow"));
        assertTrue(log.contains("WindowsCanvas"));
        assertTrue(log.contains("WindowsPen"));
    }

    @Test
    void linuxOutputContainsExpectedTokens() {
        new GameEngine(new LinuxUIFactory()).run();

        String log = out.toString();
        assertTrue(log.contains("LinuxWindow"));
        assertTrue(log.contains("LinuxCanvas"));
        assertTrue(log.contains("LinuxPen"));
    }

    @Test
    void macOutputContainsExpectedTokens() {
        new GameEngine(new MacUIFactory()).run();

        String log = out.toString();
        assertTrue(log.contains("MacWindow"));
        assertTrue(log.contains("MacCanvas"));
        assertTrue(log.contains("MacPen"));
    }
}