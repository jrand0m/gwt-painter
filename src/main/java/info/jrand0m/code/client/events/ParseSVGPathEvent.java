package info.jrand0m.code.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * event is fired when user presses 'Enter' key on input box
 */
public class ParseSVGPathEvent extends GwtEvent<ParseSVGPathEventHandler> {
    public static Type<ParseSVGPathEventHandler> TYPE = new Type<ParseSVGPathEventHandler>();

    private String textBoxInputValue;

    public ParseSVGPathEvent(String textBoxInputValue) {
        this.textBoxInputValue = textBoxInputValue;
    }

    public Type<ParseSVGPathEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(ParseSVGPathEventHandler handler) {
        handler.onParseSVGPathEvent(this);
    }

    public String getTextBoxInputValue() {
        return textBoxInputValue;
    }
}
