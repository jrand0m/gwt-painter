package info.jrand0m.code.client.parser;


import java.util.List;
/*
* Represents LineTo
* */
public class LineToParser extends AbstractSVGCommandParser {
    public LineToParser() {
        super("L", 2, true);
    }


    @Override
    public Delegate getDelegate() {
        return new Delegate() {
            public void apply(ContextAdapter context, List<Double> args, boolean isRelative) {
                assert args.size() == 2;
                double x=args.get(0),y=args.get(1);
                if(isRelative){
                    x+=context.getLastX();
                    y+=context.getLastY();
                }
                context.getContext().lineTo(x, y);
                context.setLastPoint(x,y);

            }
        };
    }
}
