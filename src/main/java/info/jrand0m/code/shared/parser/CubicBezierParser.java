package info.jrand0m.code.shared.parser;

import info.jrand0m.code.shared.ContextAdapter;

import java.util.List;

public class CubicBezierParser extends AbstractSVGCommandParser {


    public CubicBezierParser() {
        super("C", 6, true);
    }

    @Override
    public Delegate getDelegate() {
        return new Delegate() {
            public void apply(ContextAdapter context, List<Double> args, boolean isRelative) {
                assert args.size() == 6;
                double x = args.get(4), y = args.get(5),
                        x1 = args.get(0), y1 = args.get(1),
                        x2 = args.get(2), y2 = args.get(3);
                if (isRelative) {
                    x1 += context.getLastX();
                    y1 += context.getLastY();
                    x2 += context.getLastX();
                    y2 += context.getLastY();
                    x += context.getLastX();
                    y += context.getLastY();
                }
                context.bezierCurveTo(x1, y1, x2, y2, x, y);
                context.setLastPoint(x, y);
            }
        };
    }
}
