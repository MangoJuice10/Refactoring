package refactoring;

import refactoring.UI.Canvas;
import refactoring.UI.Pen;
import refactoring.UI.Window;
import refactoring.UI.Factories.UIFactory;

public class GameEngine {
    private final UIFactory factory;

    public GameEngine(UIFactory factory) {
        this.factory = factory;
    }

    public void run() {
        Window window = factory.createWindow();
        Canvas canvas = factory.createCanvas();
        Pen pen = factory.createPen();
        window.show();
        canvas.render();
        pen.drawLine(0, 0, 100, 100);
    }
}
