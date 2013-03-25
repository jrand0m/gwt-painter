package info.jrand0m.code.client.parser;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import info.jrand0m.code.shared.Command;
import info.jrand0m.code.shared.ContextAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSVGCommandParser implements CommandParser {

    private static final String VALID_SVG_ARG = "-?\\d+(?:\\.\\d+)?[\\s,$]?";

    private final String sVGCommandDelimiter;
    private final int numberOfParameters;
    private final boolean hasRelativeVersion;

    public AbstractSVGCommandParser(String sVGCommandDelimiter, int numberOfParameters, boolean hasRelativeVersion) {
        this.sVGCommandDelimiter = sVGCommandDelimiter;
        this.numberOfParameters = numberOfParameters;
        this.hasRelativeVersion = hasRelativeVersion;
    }

    public String getCommandRegEx() {

        return "[" + getsVGCommandDelimiter() + getSVGRelativeCommandDelimiter() + "]{1}"
                + (getNumberOfParameters() > 0 ? "(?:" + VALID_SVG_ARG + "){" + getNumberOfParameters() + ",}" : "");
    }

    private String getSVGRelativeCommandDelimiter() {
        return isHasRelativeVersion() ? getsVGCommandDelimiter().toLowerCase() : "";
    }

    public abstract Delegate getDelegate();

    public Command from(String string) {

        if (!string.matches(getCommandRegEx())) {
            return null;
        }
        final boolean isRelative = string.contains(getsVGCommandDelimiter().toLowerCase());
        if (getNumberOfParameters() == 0) {
            return new Command() {
                public void execute(ContextAdapter context) {
                    getDelegate().apply(context, new ArrayList<Double>(0), isRelative);
                }
            };
        }

        final List<Command> commandList = new ArrayList<Command>();
        final ArrayList<Double> args = getArguments(string);
        if (getNumberOfParameters() > 0 && args.size() % getNumberOfParameters() != 0) {
            return null;
        }
        for (int i = 0; i < args.size(); i = i + getNumberOfParameters()) {
            final ArrayList<Double> functionArgs = new ArrayList<Double>(getNumberOfParameters());
            for (int j = 0; j < getNumberOfParameters(); j++) { // generate args for function
                functionArgs.add(args.get(i + j));
            }
            commandList.add(
                    new Command() {
                        public void execute(ContextAdapter context) {
                            getDelegate().apply(context, functionArgs, isRelative);
                        }
                    });
        }

        return new Command() {
            public void execute(ContextAdapter context) {
                for (Command c : commandList) {
                    c.execute(context);
                }
            }
        };
    }

    public ArrayList<Double> getArguments(String string) {
        string = string.replace(',', ' ');
        RegExp argsRegexp = RegExp.compile("(" + VALID_SVG_ARG + ")", "g");
        ArrayList<Double> argList = new ArrayList<Double>();
        MatchResult results;
        while ((results = argsRegexp.exec(string)) != null) {
            argList.add(Double.parseDouble(results.getGroup(0)));
        }
        return argList;
    }

    public int getNumberOfParameters() {
        return numberOfParameters;
    }

    public boolean isHasRelativeVersion() {
        return hasRelativeVersion;
    }

    public String getsVGCommandDelimiter() {
        return sVGCommandDelimiter;
    }

    static interface Delegate {
        public void apply(ContextAdapter context2d, List<Double> args, boolean isRelative);
    }
}
