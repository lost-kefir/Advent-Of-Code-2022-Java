package console;

import asg.cliche.Command;
import asg.cliche.Param;

public class ConsoleHandler {

    @Command(name = "help",
            description = "This is advent of code solutions",
            header = "input ?list for available command, or check ?help for help")
    public String hello() {
        return "Hello, World!";
    }

    @Command(name = "add",
            abbrev = "a",
            description = "Use this to add two numbers",
            header = "This is header")
    public int add(@Param(name = "First number", description = "positive number") int a,
                   @Param(name = "second number", description = "positive number") int b) {
        return a + b;
    }
}
