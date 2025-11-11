package refactoring;
import refactoring.UI.Windows.*;
import refactoring.UI.Linux.*;
import refactoring.UI.Mac.*;

public class GameEngine {
    private final String platform;

    public GameEngine(String platform) {
        this.platform = platform;
    }

    public void run() {
        // Конкретная платформа и, соответственно, необходимые для создания объекты
        // определяются большим условным оператором, тем самым создавая посылки для
        // использования паттерна Abstract Factory
        if ("windows".equals(platform)) {
            WindowsWindow window = new WindowsWindow();
            WindowsCanvas canvas = new WindowsCanvas();
            WindowsPen pen = new WindowsPen();
            window.show();
            canvas.render();
            pen.drawLine(0, 0, 100, 100);
        } else if ("linux".equals(platform)) {
            LinuxWindow window = new LinuxWindow();
            LinuxCanvas canvas = new LinuxCanvas();
            LinuxPen pen = new LinuxPen();
            window.show();
            canvas.render();
            pen.drawLine(0, 0, 100, 100);
        } else if ("mac".equals(platform)) {
            MacWindow window = new MacWindow();
            MacCanvas canvas = new MacCanvas();
            MacPen pen = new MacPen();
            window.show();
            canvas.render();
            pen.drawLine(0, 0, 100, 100);
    } else {
            throw new IllegalArgumentException("Unknown platform");
        }
    }
}
