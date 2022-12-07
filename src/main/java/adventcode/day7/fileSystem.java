package adventcode.day7;

import lombok.extern.slf4j.Slf4j;
import reader.TestInputReader;

@Slf4j
public class fileSystem {
    private final String input;
    private Directory root;
    CommandParser parser;
    int totalDiskSpace = 70000000;
    int unusedSpaceRequired = 30000000;

    public fileSystem(String inputFileName) {
        input = new TestInputReader(inputFileName).getInputAsString();
        parser = new CommandParser(input);
    }

    public void runAllCommands() {
        Directory currentDir = null;
        for (String line: input.lines().toList()) {
            if (line.startsWith("$ cd /")) {
                currentDir = new Directory(line);
                root = currentDir;
                log.debug("Root Dir {}", currentDir);
                continue;
            }
            
            if (line.startsWith("$ cd ") && !line.startsWith("$ cd ..")) {
                log.debug("change directory - {}", line);
                Directory directory = new Directory(line);
                directory.setParent(currentDir);
                currentDir.getDirectories().add(directory);
                currentDir = directory;
                log.debug("Current Dir {}", currentDir);
                continue;
            }
            
            if (line.startsWith("$ cd ..")) {
                log.debug("Leave directory - {}", line);
                currentDir = currentDir.getParent();
                log.debug("Current Dir {}", currentDir);
                continue;
            }
            
            if(parser.isFile(line)) {
                log.debug("File - {}", line);
                currentDir.getFiles().add(new File(line));
                log.debug("Current Dir {}", currentDir);
            }
        }
        log.debug("DIRS: {}", root.getDirectories());
        log.debug("Files {}", root.getFiles());
        log.debug("Total Size {}", root.getTotalSize());
//        root.calculateTotalSize();
    }

    public int getRootTotalSize() {
        return  root.getTotalSize();
    }
    
    public int getUnusedSpace() {
        log.debug("Unused space: {}", totalDiskSpace - getRootTotalSize());
        return totalDiskSpace - getRootTotalSize();
    }

    public int allDirsWithSizeAtMost(int atMost) {
        return root.getDirectoriesBySize(atMost);
    }

    public int getSpaceToDeleteAtLeast(int unusedSpace) {
        return root.getDirectoriesBySize2(unusedSpace);
    }

    public int getMissingSpaceForUpdate() {
        return unusedSpaceRequired - getUnusedSpace();
    }
}
