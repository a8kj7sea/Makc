package me.a8kj.makc.registry;

import java.util.*;

import me.a8kj.makc.token.Token;

public class FunctionRegistry {
    private final Map<String, List<Token>> functions = new HashMap<>();

    public void register(String name, List<Token> tokens) {
        functions.put(name, tokens);
    }

    public List<Token> get(String name) {
        return functions.get(name);
    }
}
