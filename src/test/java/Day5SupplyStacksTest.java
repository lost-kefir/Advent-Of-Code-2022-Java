import adventcode.day5.SupplyStacks;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <a href="https://adventofcode.com/2022/day/5">Day 5 puzzle</a>
 */

@Slf4j
public class Day5SupplyStacksTest {
    final String inputFileName = "day5_test_input.txt";
    final SupplyStacks organizer = new SupplyStacks(inputFileName);


    @Test //After the rearrangement procedure completes, what crate ends up on top of each stack?
    public void checkNumberOfPairsWithRangeFullyOverlaps() {
        var expectedResult = "CMZ";
        organizer.moveCratesByProcedure();
        var result = organizer.getTopCrates();

        log.debug("Crates that ends up on top of each stack are: {}",
                result);
        assertEquals(expectedResult, result);
    }

    @Test //After the rearrangement procedure completes, what crate ends up on top of each stack?
    public void craneV2() {
        var expectedResult = "MCD";
        organizer.moveCratesByProcedureV2();
        var result = organizer.getTopCrates();
        log.debug("Crates that ends up on top of each stack are: {}",
                result);
        assertEquals(expectedResult, result);
    }
}
