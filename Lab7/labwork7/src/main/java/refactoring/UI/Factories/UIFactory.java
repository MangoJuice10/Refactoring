package refactoring.UI.Factories;

import refactoring.UI.Canvas;
import refactoring.UI.Pen;
import refactoring.UI.Window;

public interface UIFactory {
    public Window createWindow();
    public Canvas createCanvas();
    public Pen createPen();
}
