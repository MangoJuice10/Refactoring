package refactoring.UI.Windows;

import refactoring.UI.Pen;

public class WindowsPen implements Pen {
    public void drawLine(int x1, int y1, int x2, int y2) {
        System.out.printf("WindowsPen drawline (%d, %d) -> (%d, %d)%n", x1, y1, x2, y2);
    }
}
