package adventcode.day02;

import adventcode.day02.model.Move;
import adventcode.day02.model.Result;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@NoArgsConstructor
public class RockPaperScissors {
    private final int OPPONENT_MOVE = 0;
    private final int PLAYER_MOVE = 1;
    private final int INDICATED_RESULT = 1;

    public int calculateScoreBaseOnOpponentMove(String strategy) {
        if (StringUtils.isEmpty(strategy)) {
            log.error("Parameter is null or empty {}", strategy);
            throw new IllegalArgumentException("Parameter should not be null or empty");
        }

        AtomicInteger totalScore = new AtomicInteger();
        strategy.lines().forEach(line -> {
            String[] moves = line.split(StringUtils.SPACE);
            Move opponentMove = Move.fromValue(moves[OPPONENT_MOVE]);
            Move playerMove = Move.fromValue(moves[PLAYER_MOVE]);

            Result result = gameResult(playerMove, opponentMove);

            totalScore.addAndGet(gameScore(result, playerMove));
        });

        log.debug("Total game score is: {}", totalScore.get());
        return totalScore.get();
    }

    public int calculateScoreBaseOnIndicateResult(String strategy) {
        if (StringUtils.isEmpty(strategy)) {
            log.error("Parameter is null or empty {}", strategy);
            throw new IllegalArgumentException("Parameter should not be null or empty");
        }

        AtomicInteger totalScore = new AtomicInteger();
        strategy.lines().forEach(line -> {
            String[] gameStrategy = line.split(StringUtils.SPACE);
            Result indicatedResult = Result.fromValue(gameStrategy[INDICATED_RESULT]);
            Move opponentMove = Move.fromValue(gameStrategy[OPPONENT_MOVE]);
            Move playerMove = getMoveBaseOnIndicatedResult(opponentMove, indicatedResult);

            Result result = gameResult(playerMove, opponentMove);

            totalScore.addAndGet(gameScore(result, playerMove));
        });

        log.debug("Total game score is: {}", totalScore.get());
        return totalScore.get();
    }

    private Move getMoveBaseOnIndicatedResult(Move opponentMove, Result indicatedResult) {
        Move playerMove;
        switch (indicatedResult) {
            case WIN -> playerMove = Move.getWinningMove(opponentMove);
            case DRAW -> playerMove = opponentMove;
            case LOST -> playerMove = Move.getFailingMove(opponentMove);
            default -> throw new IllegalStateException("Unexpected value: " + indicatedResult);
        }
        return playerMove;
    }

    private Result gameResult(Move playerMove, Move opponentMove) {
        Result result;
        if (playerMove.equals(opponentMove)) {
            result = Result.DRAW;
        } else if (isPlayerWin(playerMove, opponentMove)) {
            result = Result.WIN;
        } else {
            result = Result.LOST;
        }

        return result;
    }

    private int gameScore(Result result, Move move) {
        return result.getScore() + move.getScore();
    }

    private boolean isPlayerWin(Move playerMove, Move opponentMove) {
        return playerMove.equals(Move.ROCK) && opponentMove.equals(Move.SCISSORS)
                || (playerMove.equals(Move.SCISSORS) && opponentMove.equals(Move.PAPER))
                || (playerMove.equals(Move.PAPER) && opponentMove.equals(Move.ROCK));
    }
}
