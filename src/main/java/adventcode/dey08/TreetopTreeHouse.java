package adventcode.dey08;

import lombok.extern.slf4j.Slf4j;
import reader.TestInputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class TreetopTreeHouse {
    private final int[][] treeMap;
    private final int width;
    private final int length;

    public TreetopTreeHouse(String inputFileName) {
        String inputMap = new TestInputReader(inputFileName).getInputAsString();
        List<String> lines = inputMap.lines().toList();
        this.width = lines.size();
        this.length = splitTrees(lines.get(0)).length;
        this.treeMap = Arrays.stream(inputMap.split("\n"))
                .map(this::splitTrees).toArray(int[][]::new);
    }

    public int getNumberOfVisibleTries() {
        int numberOfTreesInEdges = countTreesOnEdges();
        int numberOfInnerTrees = countInnerTrees();

        log.debug("Trees on edges: {}", numberOfTreesInEdges);
        log.debug("Inner trees visible: {}", numberOfInnerTrees);
        log.debug("Number of all visible trees: {}", numberOfTreesInEdges + numberOfInnerTrees);
        return numberOfTreesInEdges + numberOfInnerTrees;
    }

    public int calculateScenicScore() {
        List<Integer> distances = new ArrayList<>();
        for (int row = 1; row <= width - 2; row++) {
            for (int column = 1; column <= length - 2; column++) {
                int tree = treeMap[row][column];
                List<Integer> scenicView = new LinkedList<>();
                
                scenicView.add(iterateTopTrees(tree, row, column));
                scenicView.add(iterateLeftTrees(tree, row, column));
                scenicView.add(iterateRightTrees(tree, row, column));
                scenicView.add(iterateBottomTrees(tree, row, column));
                
                int distance = scenicView.stream()
                        .mapToInt(v -> v)
                        .reduce(Math::multiplyExact)
                        .orElseThrow();
                
                distances.add(distance);
            }
        }
        return distances.stream().mapToInt(v -> v)
                .max().orElseThrow();
    }

    private boolean isTreeVisibleFromEdge(int tree, int r, int c) {
        // check if tries in top and bottom are lower
        List<Boolean> isInAnySideHigherTree = new LinkedList<>();

        List<Integer> topTrees = new ArrayList<>();
        for (int i = r - 1; i >= 0; i--) {
            topTrees.add(treeMap[i][c]);
        }
        isInAnySideHigherTree.add(topTrees.stream().anyMatch(t -> t >= tree));

        List<Integer> leftTrees = new ArrayList<>();
        for (int i = c - 1; i >= 0; i--) {
            leftTrees.add(treeMap[r][i]);
        }
        isInAnySideHigherTree.add(leftTrees.stream().anyMatch(t -> t >= tree));

        List<Integer> bottomTrees = new ArrayList<>();
        for (int i = r + 1; i < width; i++) {
            bottomTrees.add(treeMap[i][c]);
        }
        isInAnySideHigherTree.add(bottomTrees.stream().anyMatch(t -> t >= tree));

        List<Integer> rightTrees = new ArrayList<>();
        for (int i = c + 1; i < length; i++) {
            rightTrees.add(treeMap[r][i]);
        }
        isInAnySideHigherTree.add(rightTrees.stream().anyMatch(t -> t >= tree));

        return isInAnySideHigherTree.stream().anyMatch(bool -> bool.equals(false));
    }

    private Integer iterateRightTrees(int tree, int row, int column) {
        int rightTrees = 0;
        for (int i = column + 1; i < length; i++) {
            rightTrees++;
            if (treeMap[row][i] >= tree)
                break;
        }
        return rightTrees;
    }

    private Integer iterateLeftTrees(int tree, int row, int column) {
        int leftTrees = 0;
        for (int i = column - 1; i >= 0; i--) {
            leftTrees++;
            if (treeMap[row][i] >= tree)
                break;
        }
        return leftTrees;
    }

    private Integer iterateBottomTrees(int tree, int row, int column) {
        int bottomTrees = 0;
        for (int i = row + 1; i < width; i++) {
            bottomTrees++;
            if (treeMap[i][column] >= tree)
                break;
        }
        return bottomTrees;
    }

    private Integer iterateTopTrees(int tree, int row, int column) {
        int topTrees = 0;
        for (int i = row - 1; i >= 0; i--) {
            topTrees++;
            if (treeMap[i][column] >= tree)
                break;
        }
        return topTrees;
    }

    private int[] splitTrees(String line) {
        return Stream.of(line.trim().split(""))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private int countTreesOnEdges() {
        int edges = 4;
        return (2 * width) + (2 * length) - edges;
    }

    private int countInnerTrees() {
        int counter = 0;
        for (int row = 1; row <= width - 2; row++) {
            for (int column = 1; column <= length - 2; column++) {
                int tree = treeMap[row][column];
                if (isTreeVisibleFromEdge(tree, row, column)) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
