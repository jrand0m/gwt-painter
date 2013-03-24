package info.jrand0m.code.client.parser;

import com.google.gwt.canvas.dom.client.Context2d;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class ContextAdapterTest {
    @Test
    public void testThatContextIsInitializedWithXYSetTo0() throws Exception {
        ContextAdapter c = new ContextAdapter(null
        );
        assertThat(c.getLastX(), equalTo(0d));
        assertThat(c.getLastY(), equalTo(0d));
    }

    @Test
    public void testThatContextAdapterLastPositionIsSettable() throws Exception {
        ContextAdapter c = new ContextAdapter(null);
        double x = 5, y = 6;
        c.setLastPoint(x, y);
        assertThat(c.getLastX(), equalTo(5d));
        assertThat(c.getLastY(), equalTo(6d));
    }


}
