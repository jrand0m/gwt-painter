package info.jrand0m.code.server;

import info.jrand0m.code.shared.ContextAdapter;

import java.awt.geom.Path2D;

class AWTPath2DContextAdapter implements ContextAdapter {
    private final Path2D path;
    private double x, y;

    public AWTPath2DContextAdapter(Path2D path) {
        this.path = path;
    }

    public void moveTo(double x, double y) {
        path.moveTo(x, y);
    }

    public void lineTo(double x, double y) {
        path.lineTo(x, y);
    }

    public void bezierCurveTo(double cpx1, double cpy1, double cpx2, double cpy2, double x, double y) {
        path.curveTo(cpx1, cpy1, cpx2, cpy2, x, y);
    }

    public void quadraticCurveTo(double cpx1, double cpy1, double x, double y) {
        path.quadTo(cpx1, cpy1, x, y);
    }

    public void closePath() {
        path.closePath();
    }

    public double getLastX() {
        return x;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public double getLastY() {
        return y;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setLastPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
