package info.jrand0m.code.client.parser;


import com.google.gwt.canvas.dom.client.Context2d;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ParsersIntegrationTests {
    static class LastPointSetMock{
        double x,y;
        LastPointSetMock(double x, double y){
            this.x = x; this.y= y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }
    }


    @Test
    public void moveToTest() throws Exception {
        MoveToParser m = spy(new MoveToParser());
        Command c = m.from("M100,200 300,400");
        ContextAdapter ctxAdapter = mock(ContextAdapter.class);
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).setLastPoint(eq(100d), eq(200d));
        verify(ctxAdapter, times(1)).setLastPoint(eq(300d), eq(400d));
        verify(ctxAdapter, times(1)).moveTo(eq(100d), eq(200d));
        verify(ctxAdapter, times(1)).moveTo(eq(300d), eq(400d));
        c = m.from("m100,200 300,400");
        ctxAdapter = mock(ContextAdapter.class);
        final LastPointSetMock point = new LastPointSetMock(10,20);
        when(ctxAdapter.getLastX()).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return point.getX();
            }
        });
        when(ctxAdapter.getLastY()).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return point.getY();
            }
        });
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                point.setX((Double) invocationOnMock.getArguments()[0]);
                point.setY((Double) invocationOnMock.getArguments()[1]);
                return null;
            }
        }).when(ctxAdapter).setLastPoint(anyDouble(),anyDouble());
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).moveTo(eq(110d), eq(220d));
        verify(ctxAdapter, times(1)).setLastPoint(eq(110d), eq(220d));
        verify(ctxAdapter, times(1)).moveTo(eq(410d), eq(620d));
        verify(ctxAdapter, times(1)).setLastPoint(eq(110d), eq(220d));
    }

    @Test
    public void lineToTest() throws Exception {
        LineToParser m = spy(new LineToParser());
        Command c = m.from("L100,200 300,400");
        ContextAdapter ctxAdapter = mock(ContextAdapter.class);
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).setLastPoint(eq(100d), eq(200d));
        verify(ctxAdapter, times(1)).setLastPoint(eq(300d), eq(400d));
        verify(ctxAdapter, times(1)).lineTo(eq(100d), eq(200d));
        verify(ctxAdapter, times(1)).lineTo(eq(300d), eq(400d));
        c = m.from("l100,200 300,400");
        ctxAdapter = mock(ContextAdapter.class);
        final LastPointSetMock point = new LastPointSetMock(10,20);
        when(ctxAdapter.getLastX()).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return point.getX();
            }
        });
        when(ctxAdapter.getLastY()).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return point.getY();
            }
        });
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                point.setX((Double) invocationOnMock.getArguments()[0]);
                point.setY((Double) invocationOnMock.getArguments()[1]);
                return null;
            }
        }).when(ctxAdapter).setLastPoint(anyDouble(),anyDouble());
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).lineTo(eq(110d), eq(220d));
        verify(ctxAdapter, times(1)).lineTo(eq(410d), eq(620d));
    }

    @Test
    public void closePathTest() throws Exception {
        ClosePathParser m = spy(new ClosePathParser());
        Command c = m.from("Z");
        ContextAdapter ctxAdapter = mock(ContextAdapter.class);
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).closePath();
    }

    @Test
    public void cubicBezierTest() throws Exception {
        CubicBezierParser m = spy(new CubicBezierParser());
        Command c = m.from("C100,200 300,400 500,600 100,200 300,400 550,660");
        ContextAdapter ctxAdapter = mock(ContextAdapter.class);
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).setLastPoint(eq(500d), eq(600d));
        verify(ctxAdapter, times(1)).setLastPoint(eq(550d), eq(660d));
        verify(ctxAdapter, times(1)).bezierCurveTo(eq(100d), eq(200d), eq(300d), eq(400d), eq(500d), eq(600d));
        verify(ctxAdapter, times(1)).bezierCurveTo(eq(100d), eq(200d), eq(300d), eq(400d), eq(550d), eq(660d));
        c = m.from("c100,200 300,400 500,600 100,200 300,400 550,660");
        ctxAdapter = mock(ContextAdapter.class);
        final LastPointSetMock point = new LastPointSetMock(10,20);
        when(ctxAdapter.getLastX()).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return point.getX();
            }
        });
        when(ctxAdapter.getLastY()).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return point.getY();
            }
        });
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                point.setX((Double) invocationOnMock.getArguments()[0]);
                point.setY((Double) invocationOnMock.getArguments()[1]);
                return null;
            }
        }).when(ctxAdapter).setLastPoint(anyDouble(),anyDouble());
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).bezierCurveTo(eq(110d), eq(220d), eq(310d), eq(420d), eq(510d), eq(620d));
        verify(ctxAdapter, times(1)).bezierCurveTo(eq(610d), eq(820d), eq(810d), eq(1020d), eq(1060d), eq(1280d));

    }

    @Test
    public void quadraticBezierTest() throws Exception {
        QuadraticBezierParser m = spy(new QuadraticBezierParser());
        Command c = m.from("Q100,200 500,600 100,200 550,660");
        ContextAdapter ctxAdapter = mock(ContextAdapter.class);
        assertThat(c,notNullValue());
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).setLastPoint(eq(500d), eq(600d));
        verify(ctxAdapter, times(1)).setLastPoint(eq(550d), eq(660d));
        verify(ctxAdapter, times(1)).quadraticCurveTo(eq(100d), eq(200d), eq(500d), eq(600d));
        verify(ctxAdapter, times(1)).quadraticCurveTo(eq(100d), eq(200d), eq(550d), eq(660d));
        c = m.from("q100,200 500,600 100,200 550,660");
        ctxAdapter = mock(ContextAdapter.class);
        final LastPointSetMock point = new LastPointSetMock(10,20);
        when(ctxAdapter.getLastX()).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return point.getX();
            }
        });
        when(ctxAdapter.getLastY()).thenAnswer(new Answer<Object>() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                return point.getY();
            }
        });
        doAnswer(new Answer() {
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                point.setX((Double) invocationOnMock.getArguments()[0]);
                point.setY((Double) invocationOnMock.getArguments()[1]);
                return null;
            }
        }).when(ctxAdapter).setLastPoint(anyDouble(),anyDouble());
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).quadraticCurveTo(eq(110d), eq(220d), eq(510d), eq(620d));
        verify(ctxAdapter, times(1)).quadraticCurveTo(eq(610d), eq(820d), eq(1060d), eq(1280d));
    }
}
