package adventcode.day1;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Slf4j
@NoArgsConstructor
public class CalorieCounting {

    public List<Integer> countCaloriesForEachElf(List<String> inventory) {
        if (CollectionUtils.isEmpty(inventory)) {
            log.error("List parameter is null or empty: {}", inventory);
            throw new IllegalArgumentException("List should not be null or empty");
        }

        List<Integer> elfBag = new ArrayList<>();
        List<Integer> items = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            if (isNextElfInLine(inventory, i)) {
                sumBagCalories(elfBag, items);
                continue;
            }
            items.add(Integer.valueOf(inventory.get(i)));
        }

        assert CollectionUtils.isNotEmpty(elfBag);
        log.debug("Calories for each elf counted: {}", elfBag);
        return elfBag;
    }

    public Integer getMostCalories(List<Integer> caloriesInEachElfBag) {
        if (CollectionUtils.isEmpty(caloriesInEachElfBag)) {
            throw new IllegalArgumentException("List should not be null or empty");
        }

        OptionalInt mostCalories = caloriesInEachElfBag.stream()
                .mapToInt(Integer::intValue)
                .max();
        log.debug("Most calories carried: {}", mostCalories);
        return mostCalories.orElseThrow(() -> new NoSuchElementException("Unable to calculate max value from provided list"));
    }

    public Integer getTopOfMostCalories(List<Integer> caloriesInEachElfBag, int top) {
        int topOFMostCalories;
        if (CollectionUtils.isEmpty(caloriesInEachElfBag)) {
            throw new IllegalArgumentException("List should not be null or empty");
        }
        if (top <= 0 || top > caloriesInEachElfBag.size()) {
            throw new IllegalArgumentException("Top parameter should be larger then zero and lower then list parameter size");
        }

        topOFMostCalories = caloriesInEachElfBag.stream()
                .sorted(Comparator.reverseOrder())
                .limit(top)
                .mapToInt(Integer::intValue)
                .sum();

        log.debug("Top {} most calories carried: {}", top, topOFMostCalories);
        return topOFMostCalories;
    }

    private boolean isNextElfInLine(List<String> inventory, int i) {
        return StringUtils.isEmpty(inventory.get(i)) || inventory.size() == (i + 1);
    }

    private void sumBagCalories(List<Integer> elfBag, List<Integer> items) {
        elfBag.add(items.stream().mapToInt(Integer::intValue).sum());
        items.clear();
    }
}
