package adventcode.day03;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import reader.TestInputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <a href="https://adventofcode.com/2022/day/3">Day 3 puzzle</a>
 */
@Slf4j
public class RucksackReorganization {
    private final String backpacks;

    @Getter
    private final Map<Character, Integer> prioritiesMap;

    public RucksackReorganization(@NonNull String inputFileName) {
        backpacks = new TestInputReader(inputFileName).getInputAsString();
        prioritiesMap = generatePrioritiesMap();
    }

    public int calculatePrioritiesByCompartment() {
        AtomicInteger result = new AtomicInteger();
        backpacks.lines().forEach(backpack -> {
            Rucksack rucksack = new Rucksack(backpack);
            List<String> compartments = rucksack.getCompartments();
            List<Character> duplicatedItems = rucksack.getDuplicatedItemsIn(compartments);
            int prioritiesSum = sumOfPriorities(duplicatedItems);
            log.debug("Sum of the priorities of duplicated item types is {}", prioritiesSum);
            result.getAndAdd(prioritiesSum);
        });
        return result.get();
    }

    public int calculatePrioritiesByGroups(int groupSize) {
        AtomicInteger result = new AtomicInteger();
        Rucksack rucksack = new Rucksack(backpacks);
        List<List<String>> groups =
                groupBackpacks(backpacks, groupSize);
        groups.forEach(group -> {
            List<Character> duplicatedItems = rucksack.getDuplicatedItemsIn(group);
            int prioritiesSum = sumOfPriorities(duplicatedItems);
            log.debug("Sum of the priorities of duplicated item types is {}", prioritiesSum);
            result.getAndAdd(prioritiesSum);
        });
        return result.get();
    }

    private List<List<String>> groupBackpacks(String backpacks, int numberOfBackpacksInGroup) {
        var index = new AtomicInteger();
        var groupedMap = backpacks.lines()
                .collect(Collectors.groupingBy(line -> index.getAndIncrement() / numberOfBackpacksInGroup));
        ArrayList<List<String>> backpackGroups = new ArrayList<>(groupedMap.values());
        log.debug("Grouped backpacks {}", backpackGroups);
        return backpackGroups;
    }

    private int sumOfPriorities(List<Character> duplicatedItems) {
        AtomicInteger points = new AtomicInteger();
        duplicatedItems.forEach(item -> points.addAndGet(prioritiesMap.get(item)));
        return points.get();
    }

    private Map<Character, Integer> generatePrioritiesMap() {
        List<Character> items = englishAlphabet();
        Map<Integer, Character> tempMap = IntStream.rangeClosed(1, 52).boxed()
                .collect(Collectors.toMap(Function.identity(), i -> items.get(i - 1)));
        Map<Character, Integer> priorities = tempMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

        log.debug("Map generated: {}", priorities);
        return priorities;
    }

    private List<Character> englishAlphabet() {
        return new CharacterSequence()
                .rangeClosed('a', 'z')
                .rangeClosed('A', 'Z')
                .appendAll();
    }
}
