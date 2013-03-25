package info.jrand0m.code.client.parser;

import info.jrand0m.code.shared.Command;
import info.jrand0m.code.shared.ContextAdapter;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SVGCommandParserTest {

    @Test
    public void abstractSVGCommandParsesArgumentsCorrectly() {
        AbstractSVGCommandParser parser = new AbstractSVGCommandParser("M", 2, false) {
            @Override
            public Delegate getDelegate() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        ArrayList<Double> result = parser.getArguments("M100.1,200");
        ArrayList<Double> expected = new ArrayList<Double>(2) {{
            add(100.1d);
            add(200d);
        }};
        assertThat("argument parser handles coma separated arguments", result, equalTo(expected));
        result = parser.getArguments("M100.1 200");
        assertThat("argument parser handles space separated arguments", result, equalTo(expected));
        result = parser.getArguments("M100.1,200 300,400 500,600.1");
        expected.add(300d);
        expected.add(400d);
        expected.add(500d);
        expected.add(600.1d);
        assertThat("argument parser handles more that one set of arguments", result, equalTo(expected));

    }

    @Test
    public void abstractSVGCommandHandlesConversionFromStringCorrectly() {
        final AbstractSVGCommandParser.Delegate f = mock(AbstractSVGCommandParser.Delegate.class);
        AbstractSVGCommandParser parser = new AbstractSVGCommandParser("M", 2, false) {
            @Override
            public Delegate getDelegate() {
                return f;
            }
        };
        Command c = parser.from("M100.1 200");
        ContextAdapter mockAdapter = mock(GWTContext2DAdapter.class);
        c.execute(mockAdapter);
        ArrayList<Double> data = new ArrayList<Double>(2) {{
            add(100.1d);
            add(200d);
        }};
        verify(f, times(1)).apply(eq(mockAdapter), eq(data), eq(false));
    }

    @Test
    public void abstractSVGCommandHandlesShortHandFormCorrectly() {
        final AbstractSVGCommandParser.Delegate f = mock(AbstractSVGCommandParser.Delegate.class);
        AbstractSVGCommandParser parser = new AbstractSVGCommandParser("M", 2, false) {
            @Override
            public Delegate getDelegate() {
                return f;
            }
        };
        Command c = parser.from("M100.1 200 300 400");// multiple args are actually split into multiple commands
        ContextAdapter mockAdapter = mock(GWTContext2DAdapter.class);
        c.execute(mockAdapter);
        ArrayList<Double> firstRun = new ArrayList<Double>(2) {{
            add(100.1d);
            add(200d);
        }};
        ArrayList<Double> secondRun = new ArrayList<Double>(2) {{
            add(300d);
            add(400d);
        }};
        verify(f, times(1)).apply(any(GWTContext2DAdapter.class), eq(firstRun), eq(false));
        verify(f, times(1)).apply(any(GWTContext2DAdapter.class), eq(secondRun), eq(false));
    }

    @Test
    public void abstractSVGCommandHandlesAbsoluteFormCorrectly() {
        final AbstractSVGCommandParser.Delegate f = mock(AbstractSVGCommandParser.Delegate.class);
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ContextAdapter c = (ContextAdapter) invocation.getArguments()[0];
                return null;
            }
        }).when(f).apply(any(GWTContext2DAdapter.class), anyListOf(Double.class), eq(false));
        AbstractSVGCommandParser parser = new AbstractSVGCommandParser("M", 2, true) {
            @Override
            public Delegate getDelegate() {
                return f;
            }
        };
        Command c = parser.from("M100.1 200 300 400");
        ContextAdapter mockAdapter = mock(GWTContext2DAdapter.class);
        c.execute(mockAdapter);
        ArrayList<Double> firstRun = new ArrayList<Double>(2) {{
            add(100.1d);
            add(200d);
        }};
        ArrayList<Double> secondRun = new ArrayList<Double>(2) {{
            add(300d);
            add(400d);
        }};
        verify(f, times(1)).apply(any(GWTContext2DAdapter.class), eq(firstRun), eq(false));
        verify(f, times(1)).apply(any(GWTContext2DAdapter.class), eq(secondRun), eq(false));

    }

    @Test
    public void abstractSVGCommandHandlesRelativeFormCorrectly() {
        final AbstractSVGCommandParser.Delegate f = mock(AbstractSVGCommandParser.Delegate.class);
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ContextAdapter c = (ContextAdapter) invocation.getArguments()[0];
                return null;
            }
        }).when(f).apply(any(GWTContext2DAdapter.class), anyListOf(Double.class), eq(false));
        AbstractSVGCommandParser parser = new AbstractSVGCommandParser("M", 2, true) {
            @Override
            public Delegate getDelegate() {
                return f;
            }
        };
        Command c = parser.from("m100.1 200 300 400");
        ContextAdapter mockAdapter = mock(GWTContext2DAdapter.class);
        c.execute(mockAdapter);
        ArrayList<Double> firstRun = new ArrayList<Double>(2) {{
            add(100.1d);
            add(200d);
        }};
        ArrayList<Double> secondRun = new ArrayList<Double>(2) {{
            add(300d);
            add(400d);
        }};
        verify(f, times(1)).apply(any(GWTContext2DAdapter.class), eq(firstRun), eq(true));
        verify(f, times(1)).apply(any(GWTContext2DAdapter.class), eq(secondRun), eq(true));
    }

    @Test
    public void abstractSVGCommandArgumentsParserReturnsNullIfMalformedString() {
        AbstractSVGCommandParser parser = new AbstractSVGCommandParser("M", 2, false) {
            @Override
            public Delegate getDelegate() {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        Command command = parser.from("M100.1 200 300");
        assertThat("if parser cannot parse or validate it returns null", command, nullValue());
        command = parser.from("MMMMMMMM");
        assertThat("if parser cannot parse or validate it returns null", command, nullValue());
        command = parser.from("100 200");
        assertThat("if parser cannot parse or validate it returns null", command, nullValue());
        command = parser.from(" M");
        assertThat("if parser cannot parse or validate it returns null", command, nullValue());
        command = parser.from("M");
        assertThat("if parser cannot parse or validate it returns null", command, nullValue());
        command = parser.from("M ");
        assertThat("if parser cannot parse or validate it returns null", command, nullValue());
        command = parser.from("M 100 200");
        assertThat("if parser cannot parse or validate it returns null", command, nullValue());
    }
}
