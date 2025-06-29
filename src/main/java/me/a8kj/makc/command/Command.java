package me.a8kj.makc.command;

import me.a8kj.makc.Interpreter;
import me.a8kj.makc.token.Token;

import java.util.List;

public interface Command {
    int execute(Interpreter interpreter, List<Token> tokens, int currentIndex) throws Exception;
}
