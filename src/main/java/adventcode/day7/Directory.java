package adventcode.day7;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Objects.nonNull;

@Slf4j
@Getter
public class Directory {
    private final String name;
    
    @Setter
    private Directory parent;
    private final Set<File> files = new HashSet<>();
    private final Set<Directory> directories = new HashSet<>();

    public Directory(String string) {
        String[] elements = string.split(StringUtils.SPACE);
        this.name = elements[2];
    }

    public int getDirectoriesBySize(int atMost) {
        List<Directory> matchingDir =  directories.stream()
                .flatMap(this::flatMapRecursive)
                .peek(directory -> log.debug("Dirs: {}", directory.getName()))
                .filter(directory -> directory.getTotalSize() < atMost)
                .peek(directory -> log.debug("Dirs limited: {}", directory.getName()))
                .toList();
        
        return matchingDir.stream()
                .flatMap(this::flatMapRecursive)
                .peek(directory -> log.debug("Matching Dirs: {}", directory.getName()))
                .flatMap(directory -> directory.getFiles().stream())
                .mapToInt(File::getSize)
                .sum();
    }

    public int getDirectoriesBySize2(int atLeast) {
        Directory matchingDir =  directories.stream()
                .flatMap(this::flatMapRecursive)
                .filter(directory -> directory.getTotalSize() >= atLeast)
                .peek(directory -> log.debug("Dirs limited: {}", directory.getName()))
                .min(Comparator.comparing(Directory::getTotalSize))
                .orElseThrow(NoSuchElementException::new);

        return matchingDir.getTotalSize();
    }
    
    public Integer getTotalSize() {
        Integer filesSize = files.stream().mapToInt(File::getSize).sum();
        Integer childDirsSize = directories.stream()
                .flatMap(this::flatMapRecursive)
                .flatMap(directory -> directory.getFiles().stream())
                .mapToInt(File::getSize).sum();
        return filesSize + childDirsSize;
    }

    @Override
    public String toString() {
        List<String> dirs = directories.stream()
                .map(Directory::getName)
                .toList();
        List<String> filesNames = files.stream()
                .map(File::getName)
                .toList();
        var parent = nonNull(this.parent) ? this.parent.getName() : "";
        return "Directory{" +
                "name='" + name + '\'' +
                ", parent=" + parent +
                ", files=" + filesNames +
                ", child dirs=" + dirs.toString() +
                '}';
    }

    private Stream<Directory> flatMapRecursive(Directory directory) {
        return Stream.concat(Stream.of(directory), directory.getDirectories().stream()
                .flatMap(this::flatMapRecursive));
    }
}
