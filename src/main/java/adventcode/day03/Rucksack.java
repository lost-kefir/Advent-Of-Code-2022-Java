package adventcode.day03;

import com.google.common.primitives.Chars;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class Rucksack {
    private final String input;

    protected List<String> getCompartments() {
        int size = input.length();
        int compartmentSize = size / 2;
        String[] compartments = input.split("(?<=\\G.{" + compartmentSize + "})");
        log.debug("Compartments {}", Arrays.toString(compartments));
        return Arrays.asList(compartments);
    }

    protected List<Character> getDuplicatedItemsIn(List<String> backpack) {
        var characterSet = backpack.stream()
                .map(s -> new HashSet<>(Chars.asList(s.toCharArray())))
                .toList();

        IntStream.range(1, backpack.size())
                .forEach(index -> {
                    characterSet.get(0).retainAll(characterSet.get(index));
                });

        log.debug("Duplicated Items is rucksack are: {}", characterSet.get(0));
        return characterSet.get(0).stream().distinct().collect(Collectors.toList());
    }
}
