package info.jrand0m.code.server;


import org.junit.Test;

import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class UtilsTest {
    @Test
    public void testCanConvertMoveToFromCommandToArea() throws Exception {
        String triangle = "M10,10 L20,20 10,20 Z";

        Area a = Utils.areaFromCommandString(triangle);

        assertThat(a, notNullValue());
        PathIterator it = a.getPathIterator(null);
        double[] args = new double[2];
        assertThat(it.currentSegment(args), equalTo(PathIterator.SEG_MOVETO));
        assertThat(args, equalTo(new double[]{10.0, 10.0}));
        it.next();
        assertThat(it.currentSegment(args), equalTo(PathIterator.SEG_LINETO));
        assertThat(args, equalTo(new double[]{10.0, 20.0}));
        it.next();
        assertThat(it.currentSegment(args), equalTo(PathIterator.SEG_LINETO));
        assertThat(args, equalTo(new double[]{20.0, 20.0}));
        it.next();
        assertThat(it.currentSegment(args), equalTo(PathIterator.SEG_LINETO));
        assertThat(args, equalTo(new double[]{10.0, 10.0}));
        it.next();
        assertThat(it.currentSegment(args), equalTo(PathIterator.SEG_CLOSE));
        assertThat(args, equalTo(new double[]{10.0, 10.0}));
    }

    @Test
    public void testCanConvertFromAreaToCommand() throws Exception {
        GeneralPath gp = new GeneralPath();
        gp.moveTo(80, 20);
        gp.quadTo(90.0, 40.0, 80.0, 60.0);
        gp.quadTo(50.0, 120.0, 80.0, 180.0);
        gp.lineTo(120.0, 180.0);
        gp.quadTo(150.0, 120.0, 120.0, 60.0);
        gp.quadTo(110.0, 40.0, 120.0, 20.0);
        gp.closePath();

        Area a = new Area(gp);

        String result = Utils.commandStringFromArea(a);
        assertThat(result.length(), not(equalTo(0)));
        assertThat(result, equalTo("M80.0,20.0 Q90.0,40.0 80.0,60.0 Q50.0,120.0 80.0,180.0 L120.0,180.0 Q150.0,120.0 120.0,60.0 Q110.0,40.0 120.0,20.0 Z"));
        ///////////////////////////////////////////

        gp = new GeneralPath();
        gp.moveTo(10, 105);
        gp.curveTo(50, 50, 130, 50, 80, 105);
        gp.closePath();

        a = new Area(gp);

        result = Utils.commandStringFromArea(a);
        assertThat(result.length(), not(equalTo(0)));
        // java.awt.geom.Area does some optimizations...
        assertThat(result, equalTo("M78.75,63.75 C60.0,63.75 30.0,77.5 10.0,105.0 L80,105 C105,77.5 97.5,63.75 78.75,63.75 Z"));

    }
}
