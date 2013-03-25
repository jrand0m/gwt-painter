package info.jrand0m.code.client.events;

import com.google.gwt.event.shared.GwtEvent;
import info.jrand0m.code.shared.Command;

import java.util.List;


public class InputReadyEvent extends GwtEvent<InputReadyEventHandler> {
    public static Type<InputReadyEventHandler> TYPE = new Type<InputReadyEventHandler>();
    private String inputId;
    private List<Command> commandList;

    public InputReadyEvent(String inputId, List<Command> commandList) {
        this.inputId = inputId;
        this.commandList = commandList;
    }

    public List<Command> getCommandList() {
        return commandList;
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
