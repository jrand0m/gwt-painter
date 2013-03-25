package info.jrand0m.code.client.parser;


import info.jrand0m.code.shared.Command;

public interface CommandParser {
    /**
     * Create command object from SVG command string
     *
     * @return object if can create/parse it , otherwise - null
     */
    Command from(String string);

    /**
     * RegExp must not contain group parenthesis, start or end symbol.
     *
     * @returns regexp to match one command.
     */
    String getCommandRegEx();
}
