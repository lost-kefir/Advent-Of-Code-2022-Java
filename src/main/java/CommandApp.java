import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import console.ShellPrint;
import lombok.SneakyThrows;
import console.ConsoleHandler;

public class CommandApp {
    @SneakyThrows
    public static void main(String[] args) {
        setUpHeadlessMode();
        Shell shell = ShellFactory.createConsoleShell("aoc2022",
                "AdventOfCode2022 solutions app \n" +
                        " Enter ?l to list available commands.\n" +
                        " enter ?h <command> to display command help.\n\n" +
                        " enter ? to display this message.\n" +
                        " or enter ?help for help.\n" +
                        " To exit, enter exit"
                , new ConsoleHandler(), new ShellPrint());
        shell.setDisplayTime(true);
        shell.commandLoop();
    }

    private static void setUpHeadlessMode() {
        System.setProperty("java.awt.headless", "true");
    }
}