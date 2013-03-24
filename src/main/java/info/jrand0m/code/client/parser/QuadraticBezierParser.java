package info.jrand0m.code.client.parser;

import java.util.List;

public class QuadraticBezierParser extends AbstractSVGCommandParser {

    public QuadraticBezierParser() {
        super("Q", 4, true);
    }

    @Override
    public Delegate getDelegate() {
        return new Delegate() {
            public void apply(ContextAdapter context, List<Double> args, boolean isRelative) {
                assert args.size() == 4;
                double x=args.get(2),y=args.get(3),
                        x1=args.get(0),y1=args.get(1);

                if(isRelative){
                    x1+=context.getLastX();
                    y1+=context.getLastY();
                    x+=context.getLastX();
                    y+=context.getLastY();
                }
                context.quadraticCurveTo(x1, y1, x, y);
                context.setLastPoint(x,y);
            }
        };
    }
}
