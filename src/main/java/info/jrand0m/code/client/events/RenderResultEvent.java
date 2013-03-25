package info.jrand0m.code.client.events;

import com.google.gwt.event.shared.GwtEvent;
import info.jrand0m.code.shared.Command;

import java.util.List;


public class RenderResultEvent extends GwtEvent<RenderResultEventHandler> {
    public static Type<RenderResultEventHandler> TYPE = new Type<RenderResultEventHandler>();
    private final List<Command> commandList;
    private final String rawString;

    public static Type<RenderResultEventHandler> getTYPE() {
        return TYPE;
    }

    public String getRawString() {
        return rawString;
    }

    public RenderResultEvent(List<Command> commandList, String rawString) {
        this.commandList = commandList;
        this.rawString = rawString;

    }

    public Type<RenderResultEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(RenderResultEventHandler handler) {
        handler.onRenderResult(this);
    }

    public List<Command> getCommandList() {
        return commandList;
    }
}
