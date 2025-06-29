package me.a8kj.makc.command.impl;

import java.util.List;

import me.a8kj.makc.Interpreter;
import me.a8kj.makc.command.Command;
import me.a8kj.makc.token.Token;

public class ImportCommand implements Command {
    @Override
    public int execute(Interpreter interpreter, List<Token> tokens, int index) throws Exception {
        Token next = tokens.get(index + 1);
        if (!"STRING".equals(next.type)) {
            throw new RuntimeException("Expected string after import.");
        }
        String path = next.value.substring(1, next.value.length() - 1);
        interpreter.importFile(path);
        return index + 1;
    }
}
