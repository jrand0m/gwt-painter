package info.jrand0m.code.client.parser;


import com.google.gwt.canvas.dom.client.Context2d;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.*;

@Ignore(/*TODO:*/"cannot stub context2d")
public class ParsersIntegrationTests {
    @Test
    public void moveToTest() throws Exception {
        MoveToParser m = spy(new MoveToParser());
        Command c = m.from("M100,200 300,400");
        ContextAdapter ctxAdapter = mock(ContextAdapter.class);
        Context2d ctx = mock(Context2d.class);
        when(ctxAdapter.getContext()).thenReturn(ctx);
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).setLastPoint(eq(100d), eq(200d));
        verify(ctxAdapter, times(1)).setLastPoint(eq(300d), eq(400d));
        verify(ctx, times(1)).moveTo(eq(100d), eq(200d));
        verify(ctx, times(1)).moveTo(eq(300d), eq(400d));
        c = m.from("m100,200 300,400");
        ctxAdapter = mock(ContextAdapter.class);
        ctx = mock(Context2d.class);
        when(ctxAdapter.getContext()).thenReturn(ctx);
        when(ctxAdapter.getLastX()).thenReturn(10d);
        when(ctxAdapter.getLastY()).thenReturn(20d);
        c.execute(ctxAdapter);
        verify(ctx, times(1)).moveTo(eq(110d), eq(220d));
        verify(ctx, times(1)).moveTo(eq(420d), eq(640d));
    }

    @Test
    public void lineToTest() throws Exception {
        LineToParser m = spy(new LineToParser());
        Command c = m.from("L100,200 300,400");
        ContextAdapter ctxAdapter = mock(ContextAdapter.class);
        Context2d ctx = mock(Context2d.class);
        when(ctxAdapter.getContext()).thenReturn(ctx);
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).setLastPoint(eq(100d), eq(200d));
        verify(ctxAdapter, times(1)).setLastPoint(eq(300d), eq(400d));
        verify(ctx, times(1)).lineTo(eq(100d), eq(200d));
        verify(ctx, times(1)).lineTo(eq(300d), eq(400d));
        c = m.from("l100,200 300,400");
        ctxAdapter = mock(ContextAdapter.class);
        ctx = mock(Context2d.class);
        when(ctxAdapter.getContext()).thenReturn(ctx);
        when(ctxAdapter.getLastX()).thenReturn(10d);
        when(ctxAdapter.getLastY()).thenReturn(20d);
        c.execute(ctxAdapter);
        verify(ctx, times(1)).lineTo(eq(110d), eq(220d));
        verify(ctx, times(1)).lineTo(eq(420d), eq(640d));
    }

    @Test
    public void closePathTest() throws Exception {
        ClosePathParser m = spy(new ClosePathParser());
        Command c = m.from("Z");
        ContextAdapter ctxAdapter = mock(ContextAdapter.class);
        Context2d ctx = mock(Context2d.class);
        when(ctxAdapter.getContext()).thenReturn(ctx);
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).setLastPoint(eq(0d), eq(0d));
        verify(ctx, times(1)).closePath();
    }

    @Test
    public void cubicBezierTest() throws Exception {
        CubicBezierParser m = spy(new CubicBezierParser());
        Command c = m.from("C100,200 300,400 500,600 100,200 300,400 550,660");
        ContextAdapter ctxAdapter = mock(ContextAdapter.class);
        Context2d ctx = mock(Context2d.class);
        when(ctxAdapter.getContext()).thenReturn(ctx);
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).setLastPoint(eq(500d), eq(600d));
        verify(ctxAdapter, times(1)).setLastPoint(eq(550d), eq(660d));
        verify(ctx, times(1)).bezierCurveTo(eq(100d), eq(200d), eq(300d), eq(400d), eq(500d), eq(600d));
        verify(ctx, times(1)).bezierCurveTo(eq(100d), eq(200d), eq(300d), eq(400d), eq(550d), eq(660d));
        c = m.from("c100,200 300,400 500,600 100,200 300,400 550,660");
        ctxAdapter = mock(ContextAdapter.class);
        ctx = mock(Context2d.class);
        when(ctxAdapter.getContext()).thenReturn(ctx);
        when(ctxAdapter.getLastX()).thenReturn(10d);
        when(ctxAdapter.getLastY()).thenReturn(20d);
        c.execute(ctxAdapter);
        verify(ctx, times(1)).bezierCurveTo(eq(110d), eq(220d), eq(310d), eq(420d), eq(560d), eq(680d));
        verify(ctx, times(1)).bezierCurveTo(eq(210d), eq(420d), eq(610d), eq(820d), eq(1110d), eq(1340d));

    }

    @Test
    public void quadraticBezierTest() throws Exception {
        CubicBezierParser m = spy(new CubicBezierParser());
        Command c = m.from("Q100,200 500,600 100,200 550,660");
        ContextAdapter ctxAdapter = mock(ContextAdapter.class);
        Context2d ctx = mock(Context2d.class);
        when(ctxAdapter.getContext()).thenReturn(ctx);
        c.execute(ctxAdapter);
        verify(ctxAdapter, times(1)).setLastPoint(eq(500d), eq(600d));
        verify(ctxAdapter, times(1)).setLastPoint(eq(550d), eq(660d));
        verify(ctx, times(1)).quadraticCurveTo(eq(100d), eq(200d), eq(500d), eq(600d));
        verify(ctx, times(1)).quadraticCurveTo(eq(100d), eq(200d), eq(550d), eq(660d));
        c = m.from("q100,200 500,600 100,200 550,660");
        ctxAdapter = mock(ContextAdapter.class);
        ctx = mock(Context2d.class);
        when(ctxAdapter.getContext()).thenReturn(ctx);
        when(ctxAdapter.getLastX()).thenReturn(10d);
        when(ctxAdapter.getLastY()).thenReturn(20d);
        c.execute(ctxAdapter);
        verify(ctx, times(1)).quadraticCurveTo(eq(110d), eq(220d), eq(560d), eq(680d));
        verify(ctx, times(1)).quadraticCurveTo(eq(210d), eq(420d), eq(1110d), eq(1340d));
    }
}
