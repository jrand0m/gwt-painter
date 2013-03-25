package info.jrand0m.code.shared;

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
