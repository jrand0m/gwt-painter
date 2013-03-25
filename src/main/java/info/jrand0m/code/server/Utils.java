package info.jrand0m.code.server;


import info.jrand0m.code.shared.Command;
import info.jrand0m.code.shared.ContextAdapter;
import info.jrand0m.code.shared.parser.SVGPathParser;

import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;

public class Utils {
    public static Area areaFromCommandString(String commandList) {
        GeneralPath path = new GeneralPath();
        ContextAdapter ca = new AWTPath2DContextAdapter(path);
        for (Command command : new SVGPathParser().interpret(commandList)) {
            command.execute(ca);
        }
        return new Area(path);
    }

    public static String commandStringFromArea(Area area) {
        StringBuilder result = new StringBuilder();

        PathIterator i = area.getPathIterator(null);
        while (!i.isDone()) {

            final double[] args = new double[6];

            switch (i.currentSegment(args)) {

                case PathIterator.SEG_MOVETO:
                    result.append(String.format("M%s,%s ", args[0], args[1]));
                    break;

                case PathIterator.SEG_LINETO:
                    result.append(String.format("L%s,%s ", args[0], args[1]));
                    break;

                case PathIterator.SEG_CLOSE:
                    result.append(String.format("Z "));
                    break;

                case PathIterator.SEG_QUADTO:
                    result.append(String.format("Q%s,%s %s,%s ", args[0], args[1], args[2], args[3]));
                    break;

                case PathIterator.SEG_CUBICTO:
                    result.append(String.format("C%s,%s %s,%s %s,%s ", args[0], args[1], args[2], args[3], args[4], args[5]));
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
            i.next();
        }
        return result.toString().trim();
    }

}
