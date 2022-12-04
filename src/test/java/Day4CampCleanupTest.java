import adventcode.day4.CampCleanup;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * <a href="https://adventofcode.com/2022/day/4">Day 4 puzzle</a>
 */

@Slf4j
public class Day4CampCleanupTest {

    private final String inputFileName = "day4_test_input.txt";
    private final CampCleanup organizer = new CampCleanup(inputFileName);

    @Test //In how many assignment pairs does one range fully contain the other?
    public void checkNumberOfPairsWithRangeFullyOverlaps() {
        var expectedResult = 2;
        var numberOfOverlappingAssignments = new AtomicInteger();
        var assignments = organizer.getAssignments();
        assignments.lines().forEach(assignment -> {
            var assignmentPair = organizer.makeAssignmentPair(assignment);
            if (organizer.isAssignmentsFullyOverlapping(assignmentPair))
                numberOfOverlappingAssignments.incrementAndGet();
        });

        log.debug("Number of pairs that one range fully contain the other: {}",
                numberOfOverlappingAssignments.get());
        assertEquals(expectedResult, numberOfOverlappingAssignments.get());
    }

    @Test //In how many assignment pairs do the ranges overlap?
    public void checkNumberOfPairsWithRangePartiallyOverlaps() {
        var expectedResult = 4;
        var numberOfOverlappingAssignments = new AtomicInteger();
        var assignments = organizer.getAssignments();

        assignments.lines().forEach(assignment -> {
            var assignmentPair = organizer.makeAssignmentPair(assignment);
            if (organizer.isAssignmentOverlapping(assignmentPair))
                numberOfOverlappingAssignments.incrementAndGet();
        });

        log.debug("Number of pairs with range overlap: {}",
                numberOfOverlappingAssignments.get());
        assertEquals(expectedResult, numberOfOverlappingAssignments.get());
    }

    @Test
    public void checkAssignmentPairMapping() {
        var range = "1-9,10-100";
        var expectedSmallerRange = IntStream.rangeClosed(1,9).boxed().toList();
        var expectedLongRange = IntStream.rangeClosed(10,100).boxed().toList();
        var assignmentPair = organizer.makeAssignmentPair(range);

        assertEquals(expectedSmallerRange, assignmentPair.getLeft());
        assertEquals(expectedLongRange, assignmentPair.getRight());
    }

    @Test
    public void checkAssignmentRangeValidation() {
        var range = "9-1,1-9";
        var msg = String
                .format("Range should be between lower and upper limits on a scale: %d - %d", 9, 1);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> organizer.makeAssignmentPair(range));
        assertEquals(msg, exception.getMessage());
    }

    @Test
    public void checkAssignmentRangeZeroValidation() {
        var range = "0-1,1-0";
        var msg = String
                .format("Range should be between lower and upper limits on a scale: %d - %d", 0, 1);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> organizer.makeAssignmentPair(range));
        assertEquals(msg, exception.getMessage());
    }
}
