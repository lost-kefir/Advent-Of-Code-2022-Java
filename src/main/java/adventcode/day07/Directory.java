package adventcode.day07;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

@Slf4j
@Getter
class Directory {
    private final String name;
    
    @Setter
    private Directory parent;
    private final Set<File> files = new HashSet<>();
    private final Set<Directory> directories = new HashSet<>();

    Directory(String string) {
        String[] elements = string.split(StringUtils.SPACE);
        this.name = elements[2];
    }

    int getMatchingDirSize(Predicate<Directory> predicate) {
        // filter directories that match size
        List<Directory> matchingDir =  directories.stream()
                .flatMap(this::flatMapRecursive)
//                .peek(directory -> log.debug("Dirs: {}", directory.getName()))
                .filter(predicate)
                .peek(directory -> log.debug("Dirs limited: {}", directory.getName()))
                .toList();
        
        // get size of filtered directories
        return matchingDir.stream()
                .flatMap(this::flatMapRecursive)
                .flatMap(directory -> directory.getFiles().stream())
                .mapToInt(File::getSize)
                .sum();
    }

    int getSmallerMatchingDirSize(Predicate<Directory> predicate) {
        Directory matchingDir =  directories.stream()
                .flatMap(this::flatMapRecursive)
                .filter(predicate)
                .peek(directory -> log.debug("Dirs limited: {}", directory.getName()))
                .min(Comparator.comparing(Directory::getTotalSize)) // return dir with smaller size
                .orElseThrow(NoSuchElementException::new);

        return matchingDir.getTotalSize();
    }
    
    Integer getTotalSize() {
        Integer filesSize = files.stream().mapToInt(File::getSize).sum();
        Integer childDirsSize = directories.stream()
                .flatMap(this::flatMapRecursive)
                .flatMap(directory -> directory.getFiles().stream())
                .mapToInt(File::getSize).sum();
        return filesSize + childDirsSize;
    }

    @Override
    public String toString() {
        var parent = nonNull(this.parent) ? this.parent.getName() : "";
        return "Directory{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                ", files=" + files.stream().map(File::getName).toList() +
                ", child dirs=" + directories.stream().map(Directory::getName).toList() +
                '}';
    }

    /**
     * To get all nested directories recursive is needed.
     * @param directory object that could have nested directories inside.
     * @return stream of directories
     */
    private Stream<Directory> flatMapRecursive(Directory directory) {
        return Stream.concat(Stream.of(directory), directory.getDirectories().stream()
                .flatMap(this::flatMapRecursive));
    }
}
