package adventcode.day2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;

@AllArgsConstructor
public enum Result {
    WIN(6, "Z"),
    DRAW(3, "Y"),
    LOST(0, "X");

    @Getter
    private final int score;

    @Getter
    private final String indicatedResult;

    public static Result fromValue(String value) {
        return Arrays.stream(Result.values())
                .filter(result -> result.getIndicatedResult().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("There is no result with value: " + value));
    }

}
