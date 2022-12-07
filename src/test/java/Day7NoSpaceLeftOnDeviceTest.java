import adventcode.day7.fileSystem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class Day7NoSpaceLeftOnDeviceTest {

    private final String inputFileName = "day7_test_input.txt";
    private final fileSystem fileSystem = new fileSystem(inputFileName);

    @Test //How many characters need to be processed before the first start-of-packet marker is detected?
    public void checkForUniqueSignalMarkers() {
        int expectedSizeSum = 48381165;
        fileSystem.runAllCommands();
        int actualSum = fileSystem.getRootTotalSize();

        assertEquals(expectedSizeSum, actualSum);
    }

    @Test //How many characters need to be processed before the first start-of-packet marker is detected?
    public void two() {
        int expectedSizeSum = 95437;
        fileSystem.runAllCommands();
        int actualSum = fileSystem.allDirsWithSizeAtMost(100000);

        assertEquals(expectedSizeSum, actualSum);
    }

    @Test //How many characters need to be processed before the first start-of-packet marker is detected?
    public void three() {
        int expectedSizeToDelete = 24933642;
        fileSystem.runAllCommands();
        int unusedSpace = fileSystem.getUnusedSpace();
        int spaceRequired = fileSystem.getMissingSpaceForUpdate();
        int actualSum = fileSystem.getSpaceToDeleteAtLeast(spaceRequired);

        assertEquals(expectedSizeToDelete, actualSum);
    }
}
