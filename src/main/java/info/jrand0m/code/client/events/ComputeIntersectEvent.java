package info.jrand0m.code.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class ComputeIntersectEvent extends GwtEvent<ComputeIntersectEventHandler> {
    public static Type<ComputeIntersectEventHandler> TYPE = new Type<ComputeIntersectEventHandler>();

    public Type<ComputeIntersectEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(ComputeIntersectEventHandler handler) {
        handler.onComputeIntersect(this);
    }
}
