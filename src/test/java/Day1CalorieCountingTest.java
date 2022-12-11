import adventcode.day01.CalorieCounting;
import org.junit.jupiter.api.Test;
import reader.TestInputReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * https://adventofcode.com/2022/day/1
 */
public class Day1CalorieCountingTest {

    private final String inputFileName = "day1_input.txt";
    private final CalorieCounting calorieCounting = new CalorieCounting();

    @Test //How many total Calories is Elf carrying?
    public void checkMostCaloriesCarries() {
        int correctAnswer = 71023;

        String input = new TestInputReader(inputFileName).getInputAsString();
        List<String> caloriesInventory = Arrays.stream(input.split(System.lineSeparator())).toList();

        List<Integer> caloriesInEachElfBag = calorieCounting.countCaloriesForEachElf(caloriesInventory);
        int mostCalories = calorieCounting.getMostCalories(caloriesInEachElfBag);

        assertEquals(correctAnswer, mostCalories);
    }

    @Test //How many Calories are top 3 Elves carrying in total?
    public void checkTopThreeOfMostCaloriesCarries() {
        int correctAnswer = 206289;

        String input = new TestInputReader(inputFileName).getInputAsString();
        List<String> caloriesInventory = Arrays.stream(input.split(System.lineSeparator())).toList();

        List<Integer> caloriesInEachElfBag = calorieCounting.countCaloriesForEachElf(caloriesInventory);
        int topThreeMostCalories = calorieCounting.getTopOfMostCalories(caloriesInEachElfBag, 3);

        assertEquals(correctAnswer, topThreeMostCalories);
    }

    @Test
    public void countCaloriesExceptionCheck() {
        List<String> input = Collections.emptyList();

        Exception exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> calorieCounting.countCaloriesForEachElf(input));
        assertEquals("List should not be null or empty", exceptionEmpty.getMessage());

        Exception exceptionNull = assertThrows(IllegalArgumentException.class, () -> calorieCounting.countCaloriesForEachElf(null));
        assertEquals("List should not be null or empty", exceptionNull.getMessage());
    }

    @Test
    public void mostCaloriesExceptionCheck() {
        List<Integer> input = Collections.emptyList();

        Exception exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> calorieCounting.getMostCalories(input));
        assertEquals("List should not be null or empty", exceptionEmpty.getMessage());

        Exception exceptionNull = assertThrows(IllegalArgumentException.class, () -> calorieCounting.getMostCalories(null));
        assertEquals("List should not be null or empty", exceptionNull.getMessage());
    }

    @Test
    public void topMostCaloriesListExceptionCheck() {
        int top = 5;
        List<Integer> input = Collections.emptyList();

        Exception exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> calorieCounting.getTopOfMostCalories(input, top));
        assertEquals("List should not be null or empty", exceptionEmpty.getMessage());

        Exception exceptionNull = assertThrows(IllegalArgumentException.class, () -> calorieCounting.getTopOfMostCalories(null, top));
        assertEquals("List should not be null or empty", exceptionNull.getMessage());
    }

    @Test
    public void topMostCaloriesWithNegative() {
        int negativeTop = -1;
        List<Integer> input = IntStream.rangeClosed(0, 10).boxed().toList();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> calorieCounting.getTopOfMostCalories(input, negativeTop));
        assertEquals("Top parameter should be larger then zero and lower then list parameter size", exception.getMessage());
    }

    @Test
    public void topMostCaloriesWithZero() {
        int zeroTop = 0;
        List<Integer> input = IntStream.rangeClosed(0, 10).boxed().toList();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> calorieCounting.getTopOfMostCalories(input, zeroTop));
        assertEquals("Top parameter should be larger then zero and lower then list parameter size", exception.getMessage());
    }

    @Test
    public void topMostCaloriesOversize() {
        int oversizeTop = 101;
        List<Integer> input = IntStream.rangeClosed(0, 99).boxed().toList();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> calorieCounting.getTopOfMostCalories(input, oversizeTop));
        assertEquals("Top parameter should be larger then zero and lower then list parameter size", exception.getMessage());
    }
}
