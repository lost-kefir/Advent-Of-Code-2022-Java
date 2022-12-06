package adventcode.day5;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Getter
public class Procedure {
    private final int numberOfElements;
    private final int from;
    private final int to;

    public Procedure(String procedure) {
        Pattern digitRegex = Pattern.compile("\\d+");
        Matcher matcher = digitRegex.matcher(procedure);
        List<String> digits = new ArrayList<>();
        while (matcher.find()) {
            digits.add(matcher.group(0));
        }

        assert digits.size() == 3;
        this.numberOfElements = Integer.parseInt(String.valueOf(digits.get(0)));
        this.from = Integer.parseInt(String.valueOf(digits.get(1)));
        this.to = Integer.parseInt(String.valueOf(digits.get(2)));
        log.debug("Procedure: {} elements, from column {} to column {}", numberOfElements, from, to);
    }
}
