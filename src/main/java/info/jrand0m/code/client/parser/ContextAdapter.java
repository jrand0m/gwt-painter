package info.jrand0m.code.client.parser;

import com.google.gwt.canvas.dom.client.Context;
import com.google.gwt.canvas.dom.client.Context2d;


public class ContextAdapter {
    private Context2d context;
    private double lastX = 0;
    private double lastY = 0;

    public ContextAdapter(Context2d context) {
        this.context = context;
    }

    public Context2d getContext() {
        return context;
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
