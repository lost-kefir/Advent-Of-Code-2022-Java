package adventcode.day5;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Accessors(fluent = true)
@Getter(value = AccessLevel.PROTECTED)
class Procedure {
    private final int numberOfElements;
    private final int fromColumn;
    private final int toColumn;

    protected Procedure(String procedure) {
        Pattern digitRegex = Pattern.compile("\\d+");
        Matcher matcher = digitRegex.matcher(procedure);
        List<String> digits = new ArrayList<>();
        while (matcher.find()) {
            digits.add(matcher.group(0));
        }

        assert digits.size() == 3;
        this.numberOfElements = Integer.parseInt(String.valueOf(digits.get(0)));
        this.fromColumn = Integer.parseInt(String.valueOf(digits.get(1)));
        this.toColumn = Integer.parseInt(String.valueOf(digits.get(2)));
        log.debug("Procedure: {} elements, from column {} to column {}", numberOfElements, fromColumn, toColumn);
    }
}
