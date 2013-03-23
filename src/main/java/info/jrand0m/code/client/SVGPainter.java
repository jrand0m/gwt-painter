package info.jrand0m.code.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.RootPanel;
import info.jrand0m.code.client.parser.SVGPathParser;
import info.jrand0m.code.client.views.CanvasView;
import info.jrand0m.code.client.views.ErrorLabelView;
import info.jrand0m.code.client.views.TextInputView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SVGPainter implements EntryPoint {
    SVGPathParser parser;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        //TODO: use GIN?
        SimpleEventBus eventBus = new SimpleEventBus();
        parser = new SVGPathParser(eventBus);
        RootPanel.get().add(new TextInputView(eventBus));
        RootPanel.get().add(new ErrorLabelView(eventBus));
        RootPanel.get().add(new CanvasView(eventBus));

    }
}
