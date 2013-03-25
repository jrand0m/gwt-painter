package info.jrand0m.code.server;


import info.jrand0m.code.shared.Command;
import info.jrand0m.code.shared.ContextAdapter;
import org.junit.Test;

import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CommandUtilsTest {
    @Test
    public void testCanConvertMoveToFromCommandToArea() throws Exception {
        Command moveToCommand = new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.moveTo(10, 10);
            }
        };
        Command lineToCommand = new
                Command() {
                    public void execute(ContextAdapter context2d) {
                        context2d.lineTo(20, 20);
                    }
                };
        Command lineToCommand2 = new
                Command() {
                    public void execute(ContextAdapter context2d) {
                        context2d.lineTo(10, 20);
                    }
                };
        Command closePathCommand = new
                Command() {
                    public void execute(ContextAdapter context2d) {
                        context2d.closePath();
                    }
                };
        List<Command> list = new ArrayList<Command>(4);
        list.add(moveToCommand);
        list.add(lineToCommand);
        list.add(lineToCommand2);
        list.add(closePathCommand);

        Area a = CommandUtils.areaFromCommandList(list);

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

        List<Command> commands = CommandUtils.commandsListFromArea(a);
        assertThat(commands.size(), not(equalTo(0)));
        ContextAdapter ctx = mock(ContextAdapter.class);

        for (Command c : commands) {
            c.execute(ctx);
        }

        verify(ctx, times(1)).moveTo(80, 20);
        verify(ctx, times(1)).quadraticCurveTo(90.0, 40.0, 80.0, 60.0);
        verify(ctx, times(1)).quadraticCurveTo(50.0, 120.0, 80.0, 180.0);
        verify(ctx, times(1)).lineTo(120.0, 180.0);
        verify(ctx, times(1)).quadraticCurveTo(150.0, 120.0, 120.0, 60.0);
        verify(ctx, times(1)).quadraticCurveTo(110.0, 40.0, 120.0, 20.0);
        verify(ctx, times(1)).closePath();
        verifyNoMoreInteractions(ctx);

        ///////////////////////////////////////////

        gp = new GeneralPath();
        gp.moveTo(10, 105);
        gp.curveTo(50, 50, 130, 50, 80, 105);
        gp.closePath();

        a = new Area(gp);

        commands = CommandUtils.commandsListFromArea(a);
        assertThat(commands.size(), not(equalTo(0)));
        ctx = mock(ContextAdapter.class);

        for (Command c : commands) {
            c.execute(ctx);
        }
        // java.awt.geom.Area does some optimizations...
        verify(ctx, times(1)).moveTo(78.75, 63.75);
        verify(ctx, times(1)).bezierCurveTo(60, 63.75, 30, 77.5, 10, 105);
        verify(ctx, times(1)).lineTo(80, 105);
        verify(ctx, times(1)).bezierCurveTo(105, 77.5, 97.5, 63.75, 78.75, 63.75);
        verify(ctx, times(1)).closePath();
        verifyNoMoreInteractions(ctx);


    }
}
