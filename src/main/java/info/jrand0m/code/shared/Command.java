package info.jrand0m.code.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;

/**
 * Represents Abstract SVG Command
 */
public interface Command extends Serializable,IsSerializable {
    /**
     * Execute command in given context
     */
    void execute(ContextAdapter context2d);
}
