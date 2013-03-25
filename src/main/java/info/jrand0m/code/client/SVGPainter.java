package info.jrand0m.code.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import info.jrand0m.code.client.views.IntersectOutputComposite;
import info.jrand0m.code.client.views.UserInputComposite;
import info.jrand0m.code.shared.parser.SVGPathParser;

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

        HorizontalPanel hPanel = new HorizontalPanel();
        UserInputComposite input1 = new UserInputComposite(eventBus, "1");
        UserInputComposite input2 = new UserInputComposite(eventBus, "2");
        hPanel.add(input1);
        hPanel.add(input2);
        VerticalPanel vPanel = new VerticalPanel();
        IntersectOutputComposite output = new IntersectOutputComposite(eventBus);
        vPanel.add(hPanel);
        vPanel.add(output);
        RootPanel.get().add(vPanel);

    }
}
