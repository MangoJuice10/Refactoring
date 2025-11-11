package refactoring;

public class Main {
    public static void main(String[] args) {
        // Определение целевой платформы
        String platform = detectPlatform();

        GameEngine engine = new GameEngine(platform);
        engine.run();
    }

    private static String detectPlatform() {
        String platformFullName = System.getProperty("os.name").toLowerCase();
        String platformShortName = null;
        if (platformFullName.contains("win"))
            platformShortName = "windows";
        if (platformFullName.contains("linux"))
            platformShortName = "linux";
        if (platformFullName.contains("mac"))
            platformShortName = "mac";
        return platformShortName;
    }
}
