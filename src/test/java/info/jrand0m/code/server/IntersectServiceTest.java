package info.jrand0m.code.server;


import info.jrand0m.code.client.services.IntersectService;
import info.jrand0m.code.server.services.IntersectServiceImpl;
import info.jrand0m.code.shared.Command;
import info.jrand0m.code.shared.ContextAdapter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class IntersectServiceTest {
    @Test
    public void testIntegration() throws Exception {
        List<Command> square1 = new ArrayList<Command>();
        List<Command> square2 = new ArrayList<Command>();
        square1.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.moveTo(5, 5);
            }
        });
        square1.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.lineTo(5, 15);
            }
        });
        square1.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.lineTo(15, 15);
            }
        });
        square1.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.lineTo(15, 5);
            }
        });
        square1.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.lineTo(5, 5);
            }
        });
        square1.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.closePath();
            }
        });

        square2.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.moveTo(10, 5);
            }
        });
        square2.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.lineTo(10, 15);
            }
        });
        square2.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.lineTo(20, 15);
            }
        });
        square2.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.lineTo(20, 5);
            }
        });
        square2.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.lineTo(10, 5);
            }
        });
        square2.add(new Command() {
            public void execute(ContextAdapter context2d) {
                context2d.closePath();
            }
        });
        IntersectService service = new IntersectServiceImpl();
        List<Command> result = service.getIntersection(square1, square2);
        ContextAdapter ctx = mock(ContextAdapter.class);
        for (Command c : result) {
            c.execute(ctx);
        }

        verify(ctx).moveTo(10, 5);
        verify(ctx).lineTo(10, 15);
        verify(ctx).lineTo(15, 15);
        verify(ctx).lineTo(15, 5);
        verify(ctx).closePath();
        verifyNoMoreInteractions(ctx);
    }


}
