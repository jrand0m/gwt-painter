package info.jrand0m.code.client.events;

import com.google.gwt.event.shared.GwtEvent;


public class InputReadyEvent extends GwtEvent<InputReadyEventHandler> {
    public static Type<InputReadyEventHandler> TYPE = new Type<InputReadyEventHandler>();
    private String inputId;
    private String commandString;

    public InputReadyEvent(String inputId, String commandString) {
        this.inputId = inputId;
        this.commandString = commandString;
    }

    public String getCommandString() {
        return commandString;
    }

    public String getInputId() {
        return inputId;
    }

    public Type<InputReadyEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(InputReadyEventHandler handler) {
        handler.onInputReady(this);
    }
}
