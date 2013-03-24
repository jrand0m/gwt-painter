package info.jrand0m.code.client.parser;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.rebind.rpc.SerializableTypeOracle;

import java.io.Serializable;

/**
 * Represents Abstract SVG Command
 */
public interface Command extends Serializable {
    /**
     * Execute command in given context
     */
    void execute(ContextAdapter context2d);
}
