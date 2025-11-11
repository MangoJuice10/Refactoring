package refactoring;

import refactoring.UI.Factories.LinuxUIFactory;
import refactoring.UI.Factories.MacUIFactory;
import refactoring.UI.Factories.UIFactory;
import refactoring.UI.Factories.WindowsUIFactory;

public class Main {
    public static void main(String[] args) {
        // Создание нужной фабрики под целевую платформу
        UIFactory factory = getPlatformFactory();
        GameEngine engine = new GameEngine(factory);
        engine.run();
    }

    private static UIFactory getPlatformFactory() {
        String platformFullName = System.getProperty("os.name").toLowerCase();
        UIFactory platformFactory = null;
        if (platformFullName.contains("win"))
            platformFactory = new WindowsUIFactory();
        if (platformFullName.contains("linux"))
            platformFactory = new LinuxUIFactory();
        if (platformFullName.contains("mac"))
            platformFactory = new MacUIFactory();
        return platformFactory;
    }
}
