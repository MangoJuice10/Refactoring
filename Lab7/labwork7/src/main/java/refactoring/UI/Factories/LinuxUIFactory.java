package refactoring.UI.Factories;

import refactoring.UI.Canvas;
import refactoring.UI.Pen;
import refactoring.UI.Window;
import refactoring.UI.Linux.LinuxCanvas;
import refactoring.UI.Linux.LinuxPen;
import refactoring.UI.Linux.LinuxWindow;

public class LinuxUIFactory implements UIFactory {
    @Override
    public Window createWindow() {
        return new LinuxWindow();
    }

    @Override
    public Canvas createCanvas() {
        return new LinuxCanvas();
    }

    @Override
    public Pen createPen() {
        return new LinuxPen();
    }
}
