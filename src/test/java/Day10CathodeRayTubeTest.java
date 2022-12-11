import adventcode.day10.CathodeRayTube;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Day10CathodeRayTubeTest {
    private final String inputFileName = "day10_test_input.txt";
    private final CathodeRayTube program = new CathodeRayTube(inputFileName);

    @Test //What is the sum of these six signal strengths
    public void checkSignalStrengths() {
        int expectedSignalStrength = 13140;
        int actualSignalStrength = program.calculateSignalStrengths();

        assertEquals(expectedSignalStrength, actualSignalStrength);
    }

    @Test //What eight capital letters appear on your CRT?
    public void checkProgramExecution() {
        String expectedImage =
                "##..##..##..##..##..##..##..##..##..##..\n" +
                "###...###...###...###...###...###...###.\n" +
                "####....####....####....####....####....\n" +
                "#####.....#####.....#####.....#####.....\n" +
                "######......######......######......####\n" +
                "#######.......#######.......#######.....";
        String image = program.renderImage();
        assertEquals(expectedImage, image);
    }
}
