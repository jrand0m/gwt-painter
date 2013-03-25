package info.jrand0m.code.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

public interface ContextAdapter extends IsSerializable, Serializable {
    void moveTo(double x, double y);

    void lineTo(double x, double y);

    void bezierCurveTo(double cpx1, double cpy1, double cpx2, double cpy2, double x, double y);

    void quadraticCurveTo(double cpx1, double cpy1, double x, double y);

    void closePath();

    double getLastX();

    double getLastY();

    void setLastPoint(double x, double y);
}
