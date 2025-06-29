package me.a8kj.makc.debug;

import me.a8kj.makc.Interpreter;

public class MakcDemo {
    

    public static void main(String[] args) throws Exception {
        Interpreter interpreter = new Interpreter();
        interpreter.importFile("main.makc");
    }

}
