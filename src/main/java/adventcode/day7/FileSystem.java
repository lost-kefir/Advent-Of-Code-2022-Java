package adventcode.day7;

import lombok.extern.slf4j.Slf4j;
import reader.TestInputReader;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Slf4j
public class FileSystem {
    private Directory root;
    int totalDiskSpace = 70000000;
    int unusedSpaceRequired = 30000000;

    public FileSystem(String inputFileName) {
        String input = new TestInputReader(inputFileName).getInputAsString();
        List<String> consoleLines = input.lines().toList();
        CommandParser parser = new CommandParser();
        Directory currentDir = null;
        
        for (String line: consoleLines) {
            if (parser.isRoot(line)) {
                currentDir = new Directory(line);
                root = currentDir;
                continue;
            }

            if (parser.isChangeDirCommand(line)) {
                Directory directory = new Directory(line);
                directory.setParent(currentDir);
                Objects.requireNonNull(currentDir).getDirectories().add(directory);
                currentDir = directory;
                continue;
            }

            if (parser.isLeaveDirCommand(line)) {
                currentDir = Objects.requireNonNull(currentDir).getParent();
                continue;
            }

            if(parser.isFile(line)) {
                log.debug("File - {}", line);
                Objects.requireNonNull(currentDir).getFiles().add(new File(line));
            }
        }
        log.debug("DIRS: {}", root.getDirectories());
        log.debug("Files in dir {}", root.getFiles());
        log.debug("Total Size {}", root.getTotalSize());
    }

    public int getRootTotalSize() {
        return  root.getTotalSize();
    }
    
    public int getUnusedSpace() {
        log.debug("Unused space: {}", totalDiskSpace - getRootTotalSize());
        return totalDiskSpace - getRootTotalSize();
    }

    public int getDirsSizeAtMost(int atMost) {
        Predicate<Directory> predicate = directory -> directory.getTotalSize() < atMost;
        return root.getMatchingDirSize(predicate);
    }

    public int getSpaceToDeleteAtLeast(int unusedSpace) {
        Predicate<Directory> predicate = directory -> directory.getTotalSize() >= unusedSpace;
        return root.getSmallerMatchingDirSize(predicate);
    }

    public int getMissingSpaceForUpdate() {
        return unusedSpaceRequired - getUnusedSpace();
    }
}
