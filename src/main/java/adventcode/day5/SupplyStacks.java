package adventcode.day5;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reader.TestInputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class SupplyStacks {

    @Getter
    private final List<Stack<Crate>> stacks;
    @Getter
    private final List<Procedure> procedures;
    private final DrawingParser parser;

    public SupplyStacks(@NonNull String inputFileName) {
        String drawing = new TestInputReader(inputFileName).getInputAsString();
        parser = new DrawingParser(drawing);
        this.stacks = initializeStacks();
        this.procedures = parser.getProcedures();
    }

    public void moveCratesByProcedure() {
        procedures.forEach(this::moveByCraneV1);
    }

    public void moveCratesByProcedureV2() {
        procedures.forEach(this::moveByCraneV2);
    }

    public String getTopCrates() {
        return stacks.stream().map(stack -> stack.lastElement().getValue()).collect(Collectors.joining(""));
    }

    private List<Stack<Crate>> initializeStacks() {
        List<Stack<Crate>> cratesStack = new ArrayList<>();
        List<String> stacks = parser.getStacks().lines().toList();

        int cratesSize = extractCratesFromStack(stacks, 0).size();
        IntStream.range(0, cratesSize).forEach(value -> cratesStack.add(new Stack<>()));

        IntStream.range(0, stacks.size()).forEach(s -> {
            List<String> crates = extractCratesFromStack(stacks, s);
            IntStream.range(0, cratesSize).forEach(c -> {
                String crate = crates.get(c).trim();
                if (StringUtils.isAlphanumeric(crate))
                    cratesStack.get(c).add(0, new Crate(crate));
            });
        });

        log.debug("STACKS {}", cratesStack);
        return cratesStack;
    }

    private List<String> extractCratesFromStack(List<String> stacks, int stackNumber) {
        return Arrays.stream(stacks.get(stackNumber).replaceAll("[\\[\\]]", "").split(StringUtils.SPACE))
                .toList();
    }

    private void moveByCraneV1(Procedure procedure) {
        int from = procedure.fromColumn() - 1;
        int to = procedure.toColumn() - 1;
        IntStream.range(0, procedure.numberOfElements()).forEach(value -> {
            Crate crate = stacks.get(from).pop();
            stacks.get(to).add(crate);
        });
    }

    private void moveByCraneV2(Procedure procedure) {
        int from = procedure.fromColumn() - 1;
        int to = procedure.toColumn() - 1;
        Stack<Crate> temp = new Stack<>();

        IntStream.range(0, procedure.numberOfElements()).forEach(value -> {
            Crate crate = stacks.get(from).pop();
            temp.add(crate);
        });
        
        IntStream.range(0, temp.size()).forEach(value -> this.stacks.get(to).add(temp.pop()));
    }
}
