package info.jrand0m.code.client.parser;

import com.google.gwt.canvas.dom.client.Context2d;
import info.jrand0m.code.shared.ContextAdapter;


public class GWTContext2DAdapter implements ContextAdapter {
    private Context2d context;
    private double lastX = 0;
    private double lastY = 0;

    public GWTContext2DAdapter(Context2d context) {
        this.context = context;
    }

    public void moveTo(double x, double y) {
        context.moveTo(x, y);
    }

    public void lineTo(double x, double y) {
        context.lineTo(x, y);
    }

    public void bezierCurveTo(double cpx1, double cpy1, double cpx2, double cpy2, double x, double y) {
        context.bezierCurveTo(cpx1, cpy1, cpx2, cpy2, x, y);
    }

    public void quadraticCurveTo(double cpx1, double cpy1, double x, double y) {
        context.quadraticCurveTo(cpx1, cpy1, x, y);
    }

    public void closePath() {
        context.closePath();
    }

    public double getLastX() {
        return lastX;
    }

    public double getLastY() {
        return lastY;
    }

    public void setLastPoint(final double x, final double y) {
        this.lastX = x;
        this.lastY = y;
    }
}
