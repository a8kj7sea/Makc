package me.a8kj.makc.command.impl;

import me.a8kj.makc.Interpreter;
import me.a8kj.makc.command.Command;
import me.a8kj.makc.token.Token;

import java.util.List;

public class CallCommand implements Command {
    @Override
    public int execute(Interpreter interpreter, List<Token> tokens, int currentIndex) throws Exception {
        int i = currentIndex + 1;
        Token funcNameToken = tokens.get(i);
        if (!"IDENTIFIER".equals(funcNameToken.type)) {
            throw new RuntimeException("Expected function name after call");
        }
        String funcName = funcNameToken.value;
        List<Token> funcTokens = interpreter.getFunctionRegistry().get(funcName);
        if (funcTokens == null) {
            throw new RuntimeException("Function not defined: " + funcName);
        }
        interpreter.executeTokens(funcTokens);
        return i;
    }
}
