import adventcode.day2.RockPaperScissors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reader.TestInputReader;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * https://adventofcode.com/2022/day/2
 */
@Slf4j
public class Day2RockPaperScissorsTest {

    private final String inputFileName = "day2_input.txt";
    private final RockPaperScissors rockPaperScissors = new RockPaperScissors();

    @Test //What would your total score be if everything goes exactly according to your strategy guide?
    public void checkGameTotalScoreBaseOnOpponentMove() {
        int correctAnswer = 13446;
        String strategy = new TestInputReader(inputFileName).getInputAsString();
        int totalScore = rockPaperScissors.calculateScoreBaseOnOpponentMove(strategy);
        Assertions.assertEquals(correctAnswer, totalScore);
    }

    @Test //what would your total score be if everything goes exactly according to your strategy guide?
    public void checkGameTotalScoreBaseOnIndicatedResult() {
        int correctAnswer = 13509;
        String strategy = new TestInputReader(inputFileName).getInputAsString();
        int totalScore = rockPaperScissors.calculateScoreBaseOnIndicateResult(strategy);
        Assertions.assertEquals(correctAnswer, totalScore);
    }

    @Test
    public void checkScoreBaseOnOpponentMoveExceptionEmptyInput() {
        String input = "";

        Exception exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> rockPaperScissors.calculateScoreBaseOnOpponentMove(input));
        assertEquals("Parameter should not be null or empty", exceptionEmpty.getMessage());
    }

    @Test
    public void checkScoreBaseOnOpponentMoveExceptionNullInput() {
        Exception exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> rockPaperScissors.calculateScoreBaseOnOpponentMove(null));
        assertEquals("Parameter should not be null or empty", exceptionEmpty.getMessage());
    }

    @Test
    public void checkScoreBaseOnIndicatedResultExceptionEmptyInput() {
        String input = "";

        Exception exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> rockPaperScissors.calculateScoreBaseOnIndicateResult(input));
        assertEquals("Parameter should not be null or empty", exceptionEmpty.getMessage());
    }

    @Test
    public void checkScoreBaseOnIndicatedResultExceptionNullInput() {
        Exception exceptionEmpty = assertThrows(IllegalArgumentException.class, () -> rockPaperScissors.calculateScoreBaseOnIndicateResult(null));
        assertEquals("Parameter should not be null or empty", exceptionEmpty.getMessage());
    }
}
