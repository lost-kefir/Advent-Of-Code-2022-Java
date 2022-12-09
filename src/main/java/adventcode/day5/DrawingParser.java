package adventcode.day5;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Getter
class DrawingParser {
    private final List<Procedure> procedures;
    private final List<String> stacks;
    private final int maxNumberOfCrates;
    private final static Pattern drawingDivide = Pattern.compile("(?m)^\\s*$"); // will divide drawing to crates stack and procedures
    private final static Pattern crateMarker = Pattern.compile("\\[[^\\[]*]");

    protected DrawingParser(String drawing) {
        String[] sections = drawing.split(drawingDivide.pattern());
        String stackMatrix = getStackMatrix(sections[0]);
        this.stacks = stackMatrix.lines().toList();
        this.maxNumberOfCrates = extractCratesFromStack(stacks, 0).size();
        this.procedures = getProcedureFromDrawing(drawing);
        log.debug("Formatted Stacks:\n{}", stacks);
        log.debug("Procedures:\n{}", procedures);
    }

    protected List<String> extractCratesFromStack(List<String> stacks, int stackNumber) {
        return Arrays.stream(stacks.get(stackNumber).replaceAll("[\\[\\]]", "").split(StringUtils.SPACE))
                .toList();
    }

    private String getStackMatrix(String stacksDrawing) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] lines = stacksDrawing.split(System.lineSeparator());
        Arrays.stream(lines).forEach(line -> {
            var formattedLine = replaceEmptyPlaces(line); //replace empty places with marker [*]
            if (!formattedLine.startsWith(" 1")) //Remove last line with column numbers
                stringBuilder.append(formattedLine).append("\n");
        });
        return stringBuilder.toString().trim();
    }

    private List<Procedure> getProcedureFromDrawing(String drawing) {
        String[] sections = drawing.split(drawingDivide.pattern());
        log.debug("procedures input: {}", sections[1].trim());

        List<Procedure> procedures = new LinkedList<>();
        sections[1].trim().lines().forEach(line -> {
            log.debug("Line: {}", line);
            Procedure procedure = new Procedure(line);
            procedures.add(procedure);
        });

        return procedures;
    }

    private String replaceEmptyPlaces(String s) {
        var marker = "[*]";
        var numberOfSigns = 3;

        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i += numberOfSigns) {
            int end = i + numberOfSigns;
            String element = s.substring(i, end);
            Matcher matcher = crateMarker.matcher(element);
            if (!matcher.find())
                sb.replace(i, end,marker);
            i++;
        }
        return sb.toString();
    }
}
