package me.a8kj.makc.command.impl;

import me.a8kj.makc.Interpreter;
import me.a8kj.makc.command.Command;
import me.a8kj.makc.token.Token;

import java.util.ArrayList;
import java.util.List;

public class FuncCommand implements Command {
    @Override
    public int execute(Interpreter interpreter, List<Token> tokens, int currentIndex) throws Exception {
        int i = currentIndex + 1;
        Token funcNameToken = tokens.get(i);
        if (!"IDENTIFIER".equals(funcNameToken.type)) {
            throw new RuntimeException("Expected function name after func");
        }
        String funcName = funcNameToken.value;

        i++;
        Token braceOpen = tokens.get(i);
        if (!"BRACE".equals(braceOpen.type) || !"{".equals(braceOpen.value)) {
            throw new RuntimeException("Expected '{' after function name");
        }

        List<Token> funcTokens = new ArrayList<>();
        int braceCount = 1;
        i++;
        while (i < tokens.size() && braceCount > 0) {
            Token t = tokens.get(i);
            if ("BRACE".equals(t.type)) {
                if ("{".equals(t.value)) braceCount++;
                else if ("}".equals(t.value)) braceCount--;
            }
            if (braceCount > 0) {
                funcTokens.add(t);
            }
            i++;
        }

        interpreter.getFunctionRegistry().register(funcName, funcTokens);
        return i - 1;
    }
}
