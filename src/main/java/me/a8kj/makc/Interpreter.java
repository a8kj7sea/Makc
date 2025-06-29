package me.a8kj.makc;

import me.a8kj.makc.command.Command;
import me.a8kj.makc.command.impl.*;
import me.a8kj.makc.registry.FunctionRegistry;
import me.a8kj.makc.token.Lexer;
import me.a8kj.makc.token.Token;

import java.util.*;

public class Interpreter {
    private final Set<String> importedFiles = new HashSet<>();
    private final FunctionRegistry functionRegistry = new FunctionRegistry();
    private final Map<String, Command> commands = new HashMap<>();

    public Interpreter() {
        commands.put("print", new PrintCommand());
        commands.put("import", new ImportCommand());
        commands.put("func", new FuncCommand());
        commands.put("call", new CallCommand());
    }

    public FunctionRegistry getFunctionRegistry() {
        return functionRegistry;
    }

    public void importFile(String resource) throws Exception {
        if (importedFiles.contains(resource)) return;
        importedFiles.add(resource);

        try (var is = getClass().getClassLoader().getResourceAsStream(resource)) {
            if (is == null) throw new RuntimeException("Resource not found: " + resource);
            String code = new String(is.readAllBytes());
            List<Token> tokens = Lexer.tokenize(code);
            executeTokens(tokens);
        }
    }

    public void executeTokens(List<Token> tokens) throws Exception {
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if ("KEYWORD".equals(token.type)) {
                Command cmd = commands.get(token.value);
                if (cmd == null) throw new RuntimeException("Unknown command: " + token.value);
                i = cmd.execute(this, tokens, i);
            }
        }
    }
}
