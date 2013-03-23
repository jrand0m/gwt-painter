package info.jrand0m.code.client.events;

import com.google.gwt.event.shared.GwtEvent;


public class MalformedSVGCommandEvent extends GwtEvent<MalformedSVGCommandEventHandler> {
    public static Type<MalformedSVGCommandEventHandler> TYPE = new Type<MalformedSVGCommandEventHandler>();
    private final String malformedSVGCommand;

    public MalformedSVGCommandEvent(String malformedSVGCommand) {
        this.malformedSVGCommand = malformedSVGCommand;
    }

    public Type<MalformedSVGCommandEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(MalformedSVGCommandEventHandler handler) {
        handler.onMalformedSVGCommand(this);
    }

    public String getMalformedSVGCommand() {
        return malformedSVGCommand;
    }
}
