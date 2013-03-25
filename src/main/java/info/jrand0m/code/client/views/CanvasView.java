package info.jrand0m.code.client.views;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import info.jrand0m.code.client.events.MalformedSVGCommandEvent;
import info.jrand0m.code.client.events.MalformedSVGCommandEventHandler;
import info.jrand0m.code.client.events.RenderResultEvent;
import info.jrand0m.code.client.events.RenderResultEventHandler;
import info.jrand0m.code.shared.Command;
import info.jrand0m.code.shared.ContextAdapter;
import info.jrand0m.code.shared.parser.GWTContext2DAdapter;

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
            clearCanvas();
            Context2d context2d = canvas.getContext2d();
            ContextAdapter adapter = new GWTContext2DAdapter(context2d);
            context2d.setStrokeStyle("#FA0000");
            context2d.setFillStyle("#00FA00");
            context2d.setLineWidth(2);
            context2d.beginPath();
            for (Command command : event.getCommandList()) {
                command.execute(adapter);
            }
            context2d.stroke();
            context2d.fill();
        }
    }
}
