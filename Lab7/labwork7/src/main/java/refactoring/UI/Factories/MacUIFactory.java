package refactoring.UI.Factories;

import refactoring.UI.Canvas;
import refactoring.UI.Pen;
import refactoring.UI.Window;
import refactoring.UI.Mac.MacCanvas;
import refactoring.UI.Mac.MacPen;
import refactoring.UI.Mac.MacWindow;

public class MacUIFactory implements UIFactory {
    @Override
    public Window createWindow() {
        return new MacWindow();
    }

    @Override
    public Canvas createCanvas() {
        return new MacCanvas();
    }

    @Override
    public Pen createPen() {
        return new MacPen();
    }
}
