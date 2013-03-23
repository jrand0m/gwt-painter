package info.jrand0m.code.client.views;


import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Label;
import info.jrand0m.code.client.events.MalformedSVGCommandEvent;
import info.jrand0m.code.client.events.MalformedSVGCommandEventHandler;

public class ErrorLabelView extends Label {

    private final EventBus eventBus;

    public ErrorLabelView(EventBus eventBus) {
        super();
        this.eventBus = eventBus;
        this.setWidth("300px");
        this.setHeight("20px");
        signUpForEvents();
    }

    private void signUpForEvents() {
        eventBus.addHandler(MalformedSVGCommandEvent.TYPE, new MalformedSVGCommandHandler());

    }

    class MalformedSVGCommandHandler implements MalformedSVGCommandEventHandler {

        public void onMalformedSVGCommand(MalformedSVGCommandEvent event) {
            setText("Cannot parse '" + event.getMalformedSVGCommand() + "'. Check input and try again.");
        }
    }
}
