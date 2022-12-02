package adventcode.day2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Predicate;

@AllArgsConstructor
public enum Move {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3);

    @Getter
    private final String opponentMove;
    @Getter
    private final String playerMove;
    @Getter
    private final int score;

    public static Move fromValue(String value) {
        Predicate<Move> isOpponentMove = move -> move.getOpponentMove().equalsIgnoreCase(value);
        Predicate<Move> isPlayerMove = move -> move.getPlayerMove().equalsIgnoreCase(value);
        return Arrays.stream(Move.values())
                .filter(isOpponentMove.or(isPlayerMove))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Unexpected value: " + value));
    }

    public static Move getFailingMove(Move opponentMove) {
        Move failingMove;
        switch (opponentMove) {
            case ROCK -> failingMove = Move.SCISSORS;
            case PAPER -> failingMove = Move.ROCK;
            case SCISSORS -> failingMove = Move.PAPER;
            default -> throw new IllegalStateException("Unexpected value: " + opponentMove);
        }

        return failingMove;
    }

    public static Move getWinningMove(Move opponentMove) {
        Move winMove;
        switch (opponentMove) {
            case ROCK -> winMove = Move.PAPER;
            case PAPER -> winMove = Move.SCISSORS;
            case SCISSORS -> winMove = Move.ROCK;
            default -> throw new IllegalStateException("Unexpected value: " + opponentMove);
        }

        return winMove;
    }


}

