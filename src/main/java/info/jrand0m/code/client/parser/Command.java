package info.jrand0m.code.client.parser;

import com.google.gwt.canvas.dom.client.Context2d;

/**
 * Represents Abstract SVG Command
 */
public interface Command {
    /**
     * Execute command in given context
     */
    void execute(ContextAdapter context2d);
}
