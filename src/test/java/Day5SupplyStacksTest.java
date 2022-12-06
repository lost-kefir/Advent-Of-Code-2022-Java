import adventcode.day5.SupplyStacks;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <a href="https://adventofcode.com/2022/day/5">Day 5 puzzle</a>
 */

@Slf4j
public class Day5SupplyStacksTest {


    @Test //After the rearrangement procedure completes, what crate ends up on top of each stack?
    public void checkNumberOfPairsWithRangeFullyOverlaps() {
        final String inputFileName = "day5_test_input.txt";
        final SupplyStacks organizer = new SupplyStacks(inputFileName);

        var expectedResult = "CMZ";
        var result = organizer.moveCratesByProcedure();
//        var result = organizer.getTopCrates();
        log.debug("Crates that ends up on top of each stack are: {}",
                result);
        assertEquals(expectedResult, result);
    }

    @Test //After the rearrangement procedure completes, what crate ends up on top of each stack?
    public void craneV2() {
        final String inputFileName = "day5_test_input.txt";
        final SupplyStacks organizer = new SupplyStacks(inputFileName);

        var expectedResult = "MCD";

        //        organizer.printCurrentStacks();
        var result = organizer.moveCratesByProcedureV2();
//        var result = organizer.getTopCrates();
        log.debug("Crates that ends up on top of each stack are: {}",
                result);
        assertEquals(expectedResult, result);
    }
}
