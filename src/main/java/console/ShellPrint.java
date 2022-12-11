package console;

import asg.cliche.ShellManageable;
import lombok.SneakyThrows;

public class ShellPrint implements ShellManageable {
    private final ASCIIArtGenerator artGen = new ASCIIArtGenerator();
    @SneakyThrows
    @Override
    public void cliEnterLoop() {
        artGen.printTextArt("AoC 2022", ASCIIArtGenerator.ART_SIZE_MEDIUM);
    }

    @SneakyThrows
    @Override
    public void cliLeaveLoop() {
        artGen.printTextArt("Thanks!", ASCIIArtGenerator.ART_SIZE_MEDIUM);
    }
}
