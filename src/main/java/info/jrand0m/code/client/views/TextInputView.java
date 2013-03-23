package info.jrand0m.code.client.views;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
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
        addKeyPressHandler(new KeyPressHandler() {
            public void onKeyPress(KeyPressEvent event) {
                char charCode = event.getCharCode();

                if (charCode == '\n' || charCode == '\r') {

                    eventBus.fireEvent(new ParseSVGPathEvent(getValue()));
                }
            }
        });
    }

}
