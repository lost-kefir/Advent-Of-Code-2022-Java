package adventcode.day10;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reader.TestInputReader;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CathodeRayTube {
    private final List<String> commands;
    private final List<Integer> signalStrengths = new ArrayList<>();
    private final List<char[]> display = new ArrayList<>();
    private final int signalFrequency = 40;
    private int cycle = 0;
    private int x = 1;
    private int signalCheckFrequency = 20;

    private char[] displayRow = new char[signalFrequency];
    private int pixelCounter = 0;

    public CathodeRayTube(String inputFileName) {
        String inputMap = new TestInputReader(inputFileName).getInputAsString();
        this.commands = inputMap.lines().toList();
    }

    public int calculateSignalStrengths() {
        commands.forEach(this::updateValueByProgram);

        log.debug("Number of cycles: {}", cycle);
        log.debug("Value X: {}", x);
        log.debug("Signal Strengths: {}", signalStrengths);
        return sumOfStrengths();
    }

    public String renderImage() {
        commands.forEach(this::updateValueByProgram);
        printRows();
        List<String> displayRows = display.stream().map(String::valueOf).toList();
        return String.join("\n", displayRows);
    }

    private void updateValueByProgram(String command) {
        if (command.startsWith("noop"))
            updateCycle();

        if(command.startsWith("addx")) {
            String[] parts = command.split(StringUtils.SPACE);
            int value = Integer.parseInt(parts[1]);
            for (int i = 0; i <= 1; i++) {
                updateCycle();
                if (i == 1)
                    x += value;
            }
        }
    }

    private int sumOfStrengths() {
        return signalStrengths.stream().mapToInt(x -> x).sum();
    }

    private void updateCycle() {
        drawPixel();
        cycle++;
        if (cycle % signalCheckFrequency == 0) {
            int signalStrength = cycle * x;
            signalStrengths.add(signalStrength);
            signalCheckFrequency += signalFrequency;
        }
    }

    private void drawPixel() {
        char litPixel = '#';
        char darkPixel = '.';
        log.debug("cycle: {}  x:{} counter: {}", cycle, x, pixelCounter);

        displayRow[pixelCounter] = isCpuInSprite() ? litPixel : darkPixel;
        pixelCounter++;

        clearRow();
    }

    private void clearRow() {
        if ((cycle + 1) % signalFrequency == 0) {
            display.add(displayRow);
            displayRow = new char[signalFrequency]; //clear row
            pixelCounter = 0;
        }
    }

    private boolean isCpuInSprite() {
        return pixelCounter >= x -1 && pixelCounter <= x + 1;
    }

    private void printRows() {
        List<String> displayRows = display.stream().map(String::valueOf).toList();
        log.debug("\n{}", String.join("\n", displayRows));
    }
}
