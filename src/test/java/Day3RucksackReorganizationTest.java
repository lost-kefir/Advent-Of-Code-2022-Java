import adventcode.day3.RucksackReorganization;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reader.TestInputReader;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <a href="https://adventofcode.com/2022/day/3">Day 3 puzzle</a>
 */
@Slf4j
public class Day3RucksackReorganizationTest {
    private final String inputFileName = "day3_input.txt";
    private final RucksackReorganization organizer = new RucksackReorganization(inputFileName);

    @Test //What is the sum of the priorities of those item types?
    public void checkPrioritiesSumOfDuplicatedItems() {
        int expectedResult = 8185;

        int sumOfPriorities = organizer.calculatePrioritiesByCompartment();

        assertEquals(expectedResult, sumOfPriorities);
    }

    @Test //What is the sum of the priorities of those item types?
    public void checkPrioritiesSumOfGroupsDuplicatedItems() {
        int expectedResult = 2817;
        int numberOfBackpacksInGroup = 3;
        int sumOfPriorities = organizer.calculatePrioritiesByGroups(numberOfBackpacksInGroup);

        assertEquals(expectedResult, sumOfPriorities);
    }

    @Test
    public void checkPrioritiesGeneratedMap() {
        List<Character> letters = List.of('a', 'z', 'A', 'Z', 'q', 'V');
        List<Integer> expectedPriorities = List.of(1, 26, 27, 52, 17, 48);
        Map<Character, Integer> prioritiesMap = organizer.getPrioritiesMap();

        IntStream.range(0, letters.size())
                .forEach(index -> {
                    int value = prioritiesMap.get(letters.get(index));
                    assertEquals(expectedPriorities.get(index), value);
                });
    }
}
