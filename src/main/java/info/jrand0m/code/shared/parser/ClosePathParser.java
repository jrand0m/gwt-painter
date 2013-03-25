package info.jrand0m.code.shared.parser;

import info.jrand0m.code.shared.ContextAdapter;

import java.util.List;

public class ClosePathParser extends AbstractSVGCommandParser {
    public ClosePathParser() {
        super("Z", 0, true);
    }

    @Override
    public Delegate getDelegate() {
        return new Delegate() {
            public void apply(ContextAdapter context, List<Double> args, boolean isRelative) {
                assert args.size() == 0;
                context.closePath();
            }
        };
    }
}
