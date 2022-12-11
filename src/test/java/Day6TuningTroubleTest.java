import adventcode.day06.TuningTrouble;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Day6TuningTroubleTest {

    private final String inputFileName = "day6_test_input.txt";
    private final TuningTrouble resolver = new TuningTrouble(inputFileName);

    @Test //How many characters need to be processed before the first start-of-packet marker is detected?
    public void checkForUniqueSignalMarkers() {
        int markerLength = 4;
        List<Integer> expectedResult = List.of(7, 5, 6, 10, 11);
        List<Integer> actualResult = new ArrayList<>();
        
        resolver.getSignal().lines().forEach(line -> actualResult.add(resolver.markerIn(line, markerLength)));

        log.debug("Marker appear after characters: {}",
                actualResult);
        assertEquals(expectedResult, actualResult);
    }

    @Test //How many characters need to be processed before the first start-of-message marker is detected?
    public void checkForUniqueMessageMarkers() {
        int messageMarkerLength = 14;
        List<Integer> expectedResult = List.of(19, 23, 23, 29, 26);
        List<Integer> actualResult = new ArrayList<>();

        resolver.getSignal().lines().forEach(line -> actualResult.add(resolver.markerIn(line, messageMarkerLength)));

        log.debug("Marker appear after characters: {}",
                actualResult);
        assertEquals(expectedResult, actualResult);
    }
}
