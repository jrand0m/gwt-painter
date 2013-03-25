package info.jrand0m.code.client.views;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.TextBox;
import info.jrand0m.code.client.events.ParseSVGPathEvent;

public class TextInputView extends TextBox {
    private final EventBus eventBus;

    public TextInputView(EventBus eventBus) {
        this.eventBus = eventBus;
        signUpForEvents();
    }

    private void signUpForEvents() {
        addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {

                    eventBus.fireEvent(new ParseSVGPathEvent(getValue()));
                }
            }
        });
    }

}
