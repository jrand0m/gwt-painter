package info.jrand0m.code.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import info.jrand0m.code.shared.parser.SVGPathParser;
import info.jrand0m.code.client.views.*;

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

        HorizontalPanel panel = new HorizontalPanel();
        UserInputComposite input1 = new UserInputComposite(eventBus,"1");
        UserInputComposite input2 = new UserInputComposite(eventBus,"2");
        panel.add(input1);
        panel.add(input2);
        VerticalPanel vp = new VerticalPanel();
        vp.add(panel);
        IntersectOutputComposite output = new IntersectOutputComposite(eventBus);
        vp.add(output);
        RootPanel.get().add(vp);

    }
}
