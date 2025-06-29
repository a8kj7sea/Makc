package me.a8kj.makc.command.impl;

import java.util.List;

import me.a8kj.makc.Interpreter;
import me.a8kj.makc.command.Command;
import me.a8kj.makc.token.Token;

public class PrintCommand implements Command {
    @Override
    public int execute(Interpreter interpreter, List<Token> tokens, int index) {
        Token next = tokens.get(index + 1);
        if (!"STRING".equals(next.type)) {
            throw new RuntimeException("Expected string after print.");
        }
   
        String raw = next.value.substring(1, next.value.length() - 1);
        
        String processed = raw
            .replace("\\n", "\n")
            .replace("\\t", "\t")
            .replace("\\\"", "\"")
            .replace("\\\\", "\\");
        System.out.println(processed);
        return index + 1;
    }
}
