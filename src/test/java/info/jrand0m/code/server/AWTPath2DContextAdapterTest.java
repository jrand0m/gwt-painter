package info.jrand0m.code.server;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.awt.geom.Path2D;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Path2D.class})
public class AWTPath2DContextAdapterTest {

    @Test
    public void testContextPassesCallsDirectlyToPath() throws Exception {
        Path2D mock = PowerMockito.mock(Path2D.class);
//        PowerMockito.doNothing().when(mock,"closePath");//todo for some reason doesn't work, but should
        AWTPath2DContextAdapter adapter = new AWTPath2DContextAdapter(mock);
        adapter.moveTo(30.0, 40.0);
        verify(mock, times(1)).moveTo(eq(30.0), eq(40.0));
        adapter.lineTo(10.0, 20.0);
        verify(mock, times(1)).lineTo(eq(10.0), eq(20.0));
        adapter.quadraticCurveTo(10.0, 20.0, 30.0, 40.0);
        verify(mock, times(1)).quadTo(eq(10.0), eq(20.0), eq(30.0), eq(40.0));
        adapter.bezierCurveTo(10.0, 20.0, 30.0, 40.0, 50.0, 60.0);
        verify(mock, times(1)).curveTo(eq(10.0), eq(20.0), eq(30.0), eq(40.0), eq(50.0), eq(60.0));
//        adapter.closePath();
//        verify(mock, times(1)).closePath();
        verifyNoMoreInteractions(mock);
    }

    @Test
    public void testThatGeneralPathContextIsInitializedWithXYSetTo0() throws Exception {
        AWTPath2DContextAdapter c = new AWTPath2DContextAdapter(null);
        assertThat(c.getLastX(), equalTo(0d));
        assertThat(c.getLastY(), equalTo(0d));
    }

    @Test
    public void testThatContextAdapterLastPositionIsSettable() throws Exception {
        AWTPath2DContextAdapter c = new AWTPath2DContextAdapter(null);
        double x = 5, y = 6;
        c.setLastPoint(x, y);
        assertThat(c.getLastX(), equalTo(5d));
        assertThat(c.getLastY(), equalTo(6d));
    }

}
