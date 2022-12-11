package adventcode.day04;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import reader.TestInputReader;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2022/day/4">Day 4 puzzle</a>
 */

@Slf4j
public class CampCleanup {
    @Getter
    private final String assignments;
    private final static int LEFT = 0;
    private final static int RIGHT = 1;

    public CampCleanup(@NonNull String inputFileName) {
        assignments = new TestInputReader(inputFileName).getInputAsString();
    }

    public Pair<List<Integer>, List<Integer>> makeAssignmentPair(@NonNull String pairAssignment) {
        String[] assignments = pairAssignment.split(",");
        List<List<Integer>> fullAssignment = new LinkedList<>();
        for (String assignment: assignments) {
            int[] range = Arrays.stream(assignment.split("-"))
                    .mapToInt(Integer::valueOf).toArray();
            int from = range[LEFT];
            int to = range[RIGHT];
            if (from > to || from == 0 || to == 0)
                throw new IllegalArgumentException(
                        String.format("Range should be between lower and upper limits on a scale: %d - %d", from, to));
            fullAssignment.add(IntStream.rangeClosed(from, to)
                    .boxed().toList());
        }

        var assignmentsPair = Pair.of(fullAssignment.get(LEFT), fullAssignment.get(RIGHT));
        log.debug("Assignment Pair: {}", assignmentsPair);
        return assignmentsPair;
    }

    public boolean isAssignmentOverlapping(Pair<List<Integer>, List<Integer>> assignmentPair) {
        return assignmentPair.getLeft().stream()
                .anyMatch(assignmentPair.getRight()::contains);
    }

    public boolean isAssignmentsFullyOverlapping(Pair<List<Integer>, List<Integer>> assignmentPair) {
        return new HashSet<>(assignmentPair.getLeft()).containsAll(assignmentPair.getRight())
                || new HashSet<>(assignmentPair.getRight()).containsAll(assignmentPair.getLeft());
    }
}
