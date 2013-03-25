package info.jrand0m.code.shared.parser;

import info.jrand0m.code.shared.ContextAdapter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GWTContext2DAdapterTest {
    @Test
    public void testThatContextIsInitializedWithXYSetTo0() throws Exception {
        ContextAdapter c = new GWTContext2DAdapter(null
        );
        assertThat(c.getLastX(), equalTo(0d));
        assertThat(c.getLastY(), equalTo(0d));
    }

    @Test
    public void testThatContextAdapterLastPositionIsSettable() throws Exception {
        ContextAdapter c = new GWTContext2DAdapter(null);
        double x = 5, y = 6;
        c.setLastPoint(x, y);
        assertThat(c.getLastX(), equalTo(5d));
        assertThat(c.getLastY(), equalTo(6d));
    }


}
