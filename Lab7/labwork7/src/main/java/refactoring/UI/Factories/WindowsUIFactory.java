package refactoring.UI.Factories;

import refactoring.UI.Canvas;
import refactoring.UI.Pen;
import refactoring.UI.Window;
import refactoring.UI.Windows.WindowsCanvas;
import refactoring.UI.Windows.WindowsPen;
import refactoring.UI.Windows.WindowsWindow;

public class WindowsUIFactory implements UIFactory {
    @Override
    public Window createWindow() {
        return new WindowsWindow();
    }

    @Override
    public Canvas createCanvas() {
        return new WindowsCanvas();
    }

    @Override
    public Pen createPen() {
        return new WindowsPen();
    }
}
