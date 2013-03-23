package info.jrand0m.code.client.parser;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import info.jrand0m.code.client.events.ParseSVGPathEvent;
import info.jrand0m.code.client.events.MalformedSVGCommandEvent;
import info.jrand0m.code.client.events.ParseSVGPathEventHandler;
import info.jrand0m.code.client.events.RenderResultEvent;

import java.util.*;

public class SVGPathParser {

    private static final Set<CommandParser> defaultCommandParsers = new HashSet<CommandParser>(){{
        add(new MoveToParser());
        add(new LineToParser());
        add(new ClosePathParser());
        add(new CubicBezierParser());
        add(new QuadraticBezierParser());
    }};

    private final Set<CommandParser> commandParserSet = new HashSet<CommandParser>();
    private final EventBus eventBus;


    public SVGPathParser(EventBus eventBus) {
        this(eventBus, defaultCommandParsers);

    }

    public SVGPathParser(EventBus eventBus, Collection<CommandParser> collection){
        this.eventBus = eventBus;
        for(CommandParser parser:collection){
            addCommandParser(parser);
        }
        signUpForEvents();
    }

    private void signUpForEvents() {
        eventBus.addHandler(ParseSVGPathEvent.TYPE, new ParseEventHandler());
    }

    public void addCommandParser(CommandParser commandParser) {
        if (!commandParserSet.contains(commandParser)) {
            commandParserSet.add(commandParser);
        }
    }

    public Set<CommandParser> getCommandParserSet() {
        return commandParserSet;
    }

    public List<Command> interpret(String string) {
        RegExp pattern = RegExp.compile(getCommandsRegex(), "g");
        List<Command> result = new ArrayList<Command>();
        MatchResult exec;
        while ((exec = pattern.exec(string)) != null) {
            String svgCommand = exec.getGroup(0);
            Command command = null;
            for (CommandParser commandParser : getCommandParserSet()) {
                command = commandParser.from(svgCommand);
                if (command != null) {
                    break;
                }
            }
            if (command == null) { // in case we cannot parse this command
                eventBus.fireEvent(new MalformedSVGCommandEvent(svgCommand));
                return new ArrayList<Command>(0);
            }
            result.add(command);
        }

        return result;
    }

    public String getCommandsRegex() {
        String commandsRegex = "";
        for (CommandParser parser : getCommandParserSet()) {
            commandsRegex += "(" + parser.getCommandRegEx() + ")|";
        }
        return commandsRegex.substring(0, commandsRegex.length() - 1);
    }

    class ParseEventHandler implements ParseSVGPathEventHandler {

        public void onParseSVGPathEvent(ParseSVGPathEvent event) {
            List<Command> commandList = interpret(event.getTextBoxInputValue());
            eventBus.fireEvent(new RenderResultEvent(commandList));
        }
    }
}
