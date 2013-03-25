package info.jrand0m.code.server;


import info.jrand0m.code.shared.Command;
import info.jrand0m.code.shared.ContextAdapter;

import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.List;

public class CommandUtils {
    public static Area areaFromCommandList(List<Command> commandList) {
        GeneralPath path = new GeneralPath();
        ContextAdapter ca = new AWTPath2DContextAdapter(path);
        for (Command command : commandList) {
            command.execute(ca);
        }
        return new Area(path);
    }

    public static List<Command> commandsListFromArea(Area area) {
        List<Command> result = new ArrayList<Command>();
        PathIterator i = area.getPathIterator(null);
        while (!i.isDone()) {

            final double[] args = new double[6];

            switch (i.currentSegment(args)) {

                case PathIterator.SEG_MOVETO:
                    result.add(new Command() {
                        public void execute(ContextAdapter context2d) {
                            context2d.moveTo(args[0], args[1]);
                        }
                    });
                    break;

                case PathIterator.SEG_LINETO:

                    result.add(new Command() {
                        public void execute(ContextAdapter context2d) {
                            context2d.lineTo(args[0], args[1]);
                        }
                    });
                    break;

                case PathIterator.SEG_CLOSE:
                    result.add(new Command() {
                        public void execute(ContextAdapter context2d) {
                            context2d.closePath();
                        }
                    });
                    break;

                case PathIterator.SEG_QUADTO:
                    result.add(new Command() {
                        public void execute(ContextAdapter context2d) {
                            context2d.quadraticCurveTo(args[0], args[1], args[2], args[3]);
                        }
                    });
                    break;

                case PathIterator.SEG_CUBICTO:
                    result.add(new Command() {
                        public void execute(ContextAdapter context2d) {
                            context2d.bezierCurveTo(args[0], args[1], args[2], args[3], args[4], args[5]);
                        }
                    });
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
            i.next();
        }
        return result;
    }

}
