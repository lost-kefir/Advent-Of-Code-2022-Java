package adventcode.day5;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
@Getter
public class Drawing {
    private final String procedures;
    private final String stacks;
    private final String stackDrawing;
    private final static Pattern drawingDivide = Pattern.compile("(?m)^\\s*$"); // will divide drawing to crates positions and procedures

    public Drawing(String drawing) {
        String[] sections = drawing.split(drawingDivide.pattern());
        String stacksDrawing = sections[0];
        String proceduresDrawing = sections[1].trim();
        this.stackDrawing = sections[0];
        this.stacks = formatStack(stacksDrawing);
        this.procedures = proceduresDrawing;
        log.debug("Formatted Stacks:\n{}", stacks);
        log.debug("Procedures:\n{}", procedures);
    }

    private String formatStack(String stacksDrawing) {
        log.debug("INPUT: \n{}", stacksDrawing);
        StringBuilder stringBuilder = new StringBuilder();
        String[] lines = stacksDrawing.split(System.lineSeparator());
        for (int i = 0; i < lines.length -1 ; i++) {
            String string = lines[i];
            log.debug("LINE|{}", string);
            string = replaceEmptyCrates(string);
            stringBuilder.append(string).append("\n");
        }
        return stringBuilder.toString().trim();
    }

    private String replaceEmptyCrates(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i += 3) {
            int end = i + 3;
            String element = s.substring(i, end);
            if (element.equals("   ")) {
                sb.replace(i, end,"[*]");
            }
            i++;
        }
        return sb.toString();
    }
}
