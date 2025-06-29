package me.a8kj.makc.token;

import java.util.*;
import java.util.regex.*;

public class Lexer {
    private static final Pattern tokenPatterns = Pattern.compile(
        "(import|print|func|call)|(\"[^\"]*\")|(\\{|\\})|(\\S+)"
    );

    public static List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        Matcher matcher = tokenPatterns.matcher(input);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                tokens.add(new Token("KEYWORD", matcher.group(1)));
            } else if (matcher.group(2) != null) {
                tokens.add(new Token("STRING", matcher.group(2)));
            } else if (matcher.group(3) != null) {
                tokens.add(new Token("BRACE", matcher.group(3)));
            } else if (matcher.group(4) != null) {
                tokens.add(new Token("IDENTIFIER", matcher.group(4)));
            }
        }
        return tokens;
    }
}
