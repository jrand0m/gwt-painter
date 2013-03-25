package info.jrand0m.code.shared.parser;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import info.jrand0m.code.client.events.MalformedSVGCommandEvent;
import info.jrand0m.code.client.events.ParseSVGPathEvent;
import info.jrand0m.code.client.events.RenderResultEvent;
import org.junit.Test;
import org.mockito.internal.matchers.Contains;
import org.mockito.internal.matchers.Find;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class SVGParserTest {

    @Test
    @SuppressWarnings("unchecked")
    public void checkIfInterpretFiresEventOnSuccess() {
        EventBus bus = spy(new SimpleEventBus());
        new SVGPathParser(bus);
        bus.fireEvent(new ParseSVGPathEvent("M0,0 L1,1, Z"));
        verify(bus, atLeastOnce()).fireEvent(new RenderResultEvent(anyList(), "M0,0 L1,1, Z"));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void checkIfInterpretFiresEventOnFailure() {
        EventBus bus = mock(EventBus.class);
        SVGPathParser parser = spy(new SVGPathParser(bus));
        final CommandParser parserStub = mock(CommandParser.class);
        when(parserStub.from(anyString())).thenReturn(null);
        when(parser.getCommandsRegex()).thenReturn("(\\.*)");
        when(parser.getCommandParserSet()).thenReturn(new HashSet<CommandParser>() {{
            add(parserStub);
        }});
        parser.interpret("M0,0 L1,1 1 z");
        verify(parser, times(1)).getCommandParserSet();
        verify(bus, times(1)).fireEvent(new MalformedSVGCommandEvent(anyString()));
        verify(bus, times(1)).fireEvent(new RenderResultEvent(anyList(), "M0,0 L1,1, Z"));

    }

    @Test
    public void checkIfInterpretGeneratesRightRegExForCommands() {
        EventBus bus = mock(EventBus.class);
        SVGPathParser svgPathParser = spy(new SVGPathParser(bus));
        svgPathParser.getCommandParserSet().clear();
        CommandParser parser = mock(CommandParser.class);
        when(parser.getCommandRegEx()).thenReturn("test1");
        svgPathParser.addCommandParser(parser);
        assertThat("trailing OR symbol is removed", svgPathParser.getCommandsRegex(), equalTo("(test1)"));
        CommandParser parser2 = mock(CommandParser.class);
        when(parser2.getCommandRegEx()).thenReturn("test2");
        svgPathParser.addCommandParser(parser2);
        assertThat("regexes from CommandParsers are concatenated with OR", svgPathParser.getCommandsRegex(), allOf(new Contains("(test1)"), new Contains("(test2)"), new Find("\\(\\w+\\d\\)\\|\\(\\w+\\d\\)")));
    }

}
