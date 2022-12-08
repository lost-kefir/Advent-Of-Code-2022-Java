import adventcode.day7.FileSystem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Day7NoSpaceLeftOnDeviceTest {

    private final String inputFileName = "day7_test_input.txt";
    private final FileSystem fileSystem = new FileSystem(inputFileName);

    @Test //What is the sum of the total sizes of those directories?
    public void checkDirectoriesTotalSize() {
        int expectedSizeSum = 48381165;
        int actualSum = fileSystem.getRootTotalSize();

        assertEquals(expectedSizeSum, actualSum);
    }

    @Test //What is the sum of the total sizes of those directories?
    public void checkSizeOfDirectoriesBiggerThen() {
        int directorySizeAtMost = 100000;
        int expectedSizeSum = 95437;
        int actualSum = fileSystem.getDirsSizeAtMost(directorySizeAtMost);

        assertEquals(expectedSizeSum, actualSum);
    }

    @Test //What is the total size of that directory?
    public void checkSizeOfDirectoriesToDelete() {
        int expectedSizeToDelete = 24933642;
        int spaceRequired = fileSystem.getMissingSpaceForUpdate();
        int actualSum = fileSystem.getSpaceToDeleteAtLeast(spaceRequired);

        assertEquals(expectedSizeToDelete, actualSum);
    }
}
