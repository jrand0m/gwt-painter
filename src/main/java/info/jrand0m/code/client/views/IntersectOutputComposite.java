package info.jrand0m.code.client.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import info.jrand0m.code.client.events.InputReadyEvent;
import info.jrand0m.code.client.events.InputReadyEventHandler;
import info.jrand0m.code.client.events.ParseSVGPathEvent;
import info.jrand0m.code.client.services.IntersectService;
import info.jrand0m.code.client.services.IntersectServiceAsync;
import info.jrand0m.code.shared.parser.SVGPathParser;

import java.util.HashMap;

public class IntersectOutputComposite extends Composite {
    private final EventBus bus;
    private final SimpleEventBus internalEventBus;
    private final SVGPathParser parser;
    private final HashMap<String, String> results = new HashMap<String, String>();
    private final IntersectServiceAsync service = (IntersectServiceAsync) GWT.create(IntersectService.class);


    public IntersectOutputComposite(EventBus bus) {
        this.bus = bus;
        this.internalEventBus = new SimpleEventBus();
        this.parser = new SVGPathParser(internalEventBus);
        createWidgets();
        signUpForEvents();

    }

    private void signUpForEvents() {
        bus.addHandler(InputReadyEvent.TYPE, new InputReadyEventHandler() {
            public void onInputReady(InputReadyEvent event) {
                String commandString = event.getCommandString();
                if (!commandString.isEmpty()) {
                    results.put(event.getInputId(), commandString);
                }
            }
        });

    }

    protected void createWidgets() {
        VerticalPanel widget = new VerticalPanel();
        initWidget(widget);
        widget.add(new Label("Output"));
        widget.add(new IntersectButton());
        widget.add(new CanvasView(internalEventBus));

    }

    class IntersectButton extends Button {

        public IntersectButton() {
            setText("Intersect");
            addClickHandler(new ClickHandler() {

                public void onClick(ClickEvent event) {

                    if (results.size() == 2) {
                        Object[] keys = results.keySet().toArray();
                        assert keys.length == 2;
                        service.getIntersection(results.get(keys[0]), results.get(keys[1]),
                                new AsyncCallback<String>() {
                                    public void onFailure(Throwable caught) {
                                        caught.printStackTrace();
                                    }

                                    public void onSuccess(String result) {
                                        internalEventBus.fireEvent(new ParseSVGPathEvent(result));
                                    }
                                });
                        results.clear();
                    }
                }
            });
        }
    }
}
