package adventcode.day7;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class CommandParser {
    private final String input;
    private final static String commandMarker = "$";

    public CommandParser(String input) {
        this.input = input;
    }

    public boolean isFile(String line) {
        String[] elements = line.split(StringUtils.SPACE);
        return StringUtils.isNumeric(elements[0]);
    }
}
