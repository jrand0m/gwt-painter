package info.jrand0m.code.client.parser;

import java.util.List;

public class MoveToParser extends AbstractSVGCommandParser {

    public MoveToParser() {
        super("M", 2, true);
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
                context.getContext().moveTo(x, y);
                context.setLastPoint(x,y);
            }
        };
    }
}
