import adventcode.day7.FileSystem;
import adventcode.dey8.TreetopTreeHouse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Day8TreetopTreeHouseTest {

    private final String inputFileName = "day8_test_input.txt";
    private final TreetopTreeHouse treeHouse = new TreetopTreeHouse(inputFileName);

    @Test //What is the sum of the total sizes of those directories?
    public void checkDirectoriesTotalSize() {
        int expectedSizeSum = 21;
        int actualSum = treeHouse.getNumberOfVisibleTries();
//
        assertEquals(expectedSizeSum, actualSum);
    }

    @Test //What is the sum of the total sizes of those directories?
    public void checkSizeOfDirectoriesBiggerThen() {
        int expectedSizeSum = 8;
        int actualSum = treeHouse.calculateScenicScore();

        assertEquals(expectedSizeSum, actualSum);
    }

//    @Test //What is the total size of that directory?
//    public void checkSizeOfDirectoriesToDelete() {
//        int expectedSizeToDelete = 24933642;
//        int spaceRequired = fileSystem.getMissingSpaceForUpdate();
//        int actualSum = fileSystem.getSpaceToDeleteAtLeast(spaceRequired);
//
//        assertEquals(expectedSizeToDelete, actualSum);
//    }
}
