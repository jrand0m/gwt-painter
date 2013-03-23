package info.jrand0m.code.client.views;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import info.jrand0m.code.client.events.*;
import info.jrand0m.code.client.parser.Command;
import info.jrand0m.code.client.parser.ContextAdapter;

public class CanvasView implements IsWidget {
    private EventBus eventBus;
    private Canvas canvas;


    public CanvasView(EventBus eventBus) {
        this.eventBus = eventBus;
        canvas = Canvas.createIfSupported();
        if (canvas != null) {
            canvas.setWidth("200px");
            canvas.setHeight("200px");
            signUpForEvents();
        }

    }

    private void signUpForEvents() {
        eventBus.addHandler(RenderResultEvent.TYPE, new RenderResultHandler());
        eventBus.addHandler(MalformedSVGCommandEvent.TYPE, new MalformedSVGHandler());
    }

    public Widget asWidget() {
        if (canvas == null) {
            return new Label("Failed to create canvas");
        }
        return canvas;
    }

    private void clearCanvas() {
        canvas.getContext2d().clearRect(0, 0, getWidth(), getHeight());
    }

    private double getWidth() {
        return canvas.getCoordinateSpaceHeight();//for demo will do
    }

    private double getHeight() {
        return canvas.getCoordinateSpaceHeight();//for demo will do
    }

    class MalformedSVGHandler implements MalformedSVGCommandEventHandler {
        public void onMalformedSVGCommand(MalformedSVGCommandEvent event) {
            clearCanvas();
        }
    }

    class RenderResultHandler implements RenderResultEventHandler {
        public void onRenderResult(RenderResultEvent event) {

            ContextAdapter adapter = new ContextAdapter(canvas.getContext2d());
            adapter.getContext().setStrokeStyle("#FA0000");
            adapter.getContext().setLineWidth(2);
            adapter.getContext().beginPath();
            for (Command command : event.getCommandList()) {
                command.execute(adapter);
            }
            adapter.getContext().stroke();
        }
    }
}
