package info.jrand0m.code.client.views;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import info.jrand0m.code.client.events.InputReadyEvent;
import info.jrand0m.code.client.events.RenderResultEvent;
import info.jrand0m.code.client.events.RenderResultEventHandler;
import info.jrand0m.code.shared.parser.SVGPathParser;
import info.jrand0m.code.shared.Command;

import java.util.List;

public class UserInputComposite extends Composite {
    private EventBus bus;
    private final SimpleEventBus internalEventBus;
    private final SVGPathParser parser;
    private final String id;

    public UserInputComposite(EventBus bus, String id){
        this.id = id;
        this.bus = bus;
        this.internalEventBus = new SimpleEventBus();
        this.parser = new SVGPathParser(internalEventBus);
        createWidgets();
        signUpForEvents();
    }

    private void signUpForEvents() {
        internalEventBus.addHandler(RenderResultEvent.TYPE, new RenderResultEventHandler() {
            public void onRenderResult(RenderResultEvent event) {
                if (!event.getCommandList().isEmpty()){
                   bus.fireEvent(new InputReadyEvent(id,event.getRawString()));
                }
            }
        });
    }


    protected void createWidgets() {
        VerticalPanel widget = new VerticalPanel();
        initWidget(widget);
        widget.add(new Label("Input #"+id));
        widget.add(new TextInputView(internalEventBus));
        widget.add(new ErrorLabelView(internalEventBus));
        widget.add(new CanvasView(internalEventBus));
    }
}
