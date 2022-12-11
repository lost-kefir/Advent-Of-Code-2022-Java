package adventcode.day03;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CharacterSequence {
    private final List<List<Character>> sequences;
    protected CharacterSequence() {
        this.sequences = new ArrayList<>();
    }

    protected CharacterSequence rangeClosed(char from, char to) {
        boolean isLowerCase = Character.isLowerCase(from);
        char[] chars = IntStream.rangeClosed(from, to)
                .mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
        List<Character> sequence = Arrays.stream(ArrayUtils.toObject(chars))
                .map(character -> isLowerCase ? Character.toLowerCase(character) : character)
                .collect(Collectors.toList());
        sequences.add(sequence);
        return this;
    }

    protected List<Character> appendAll() {
        return sequences.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
