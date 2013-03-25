package info.jrand0m.code.shared;

public interface ContextAdapter {
    void moveTo(double x, double y);

    void lineTo(double x, double y);

    void bezierCurveTo(double cpx1, double cpy1, double cpx2, double cpy2, double x, double y);

    void quadraticCurveTo(double cpx1, double cpy1, double x, double y);

    void closePath();

    double getLastX();

    double getLastY();

    void setLastPoint(double x, double y);
}
