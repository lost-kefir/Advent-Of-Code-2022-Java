package adventcode.day06;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import reader.TestInputReader;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class TuningTrouble {
    @Getter
    private final String signal;

    public TuningTrouble(@NonNull String inputFileName) {
        signal = new TestInputReader(inputFileName).getInputAsString();
    }

    public Integer markerIn(String line, int markerLength) {
        char[] chars = line.toCharArray();
        
        for (int c = 0; c < chars.length; c++) {
            if (markerLength > chars.length)
                markerLength = (chars.length - c);

            var part = line.substring(c, c + markerLength);
            
            if (isUniqueMarker(part)) {
                return c + markerLength;
            }
        }
        return null;
    }

    private boolean isUniqueMarker(String string) {
        Set<Character> charSet = string.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.toSet());
        return charSet.size() == string.length();
    }
}
