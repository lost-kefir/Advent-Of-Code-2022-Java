package adventcode.day5;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reader.TestInputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class SupplyStacks {

    @Getter
    private List<Stack<Crate>> stacks;
    @Getter
    private final List<Procedure> procedures;
    private final DrawingParser parser;

    public SupplyStacks(@NonNull String inputFileName) {
        String drawing = new TestInputReader(inputFileName).getInputAsString();
        parser = new DrawingParser(drawing);
        this.stacks = initializeStacks();
        this.procedures = parser.getProcedures();
    }

    public void moveCratesByProcedure(@NonNull Crane craneType) {
        procedures.forEach(procedure -> this.stacks = craneType.moveCrates(stacks, procedure));
    }

    public String getTopCrates() {
        return stacks.stream().map(stack -> stack.lastElement().getValue()).collect(Collectors.joining(""));
    }

    private List<Stack<Crate>> initializeStacks() {
        List<Stack<Crate>> cratesStack = new ArrayList<>();
        List<String> stacks = parser.getStacks();

        int numberOfCrates = parser.getMaxNumberOfCrates();
        IntStream.range(0, numberOfCrates).forEach(value -> cratesStack.add(new Stack<>()));

        IntStream.range(0, stacks.size()).forEach(i -> {
            List<String> crates = parser.extractCratesFromStack(stacks, i);
            IntStream.range(0, numberOfCrates).forEach(c -> {
                String crate = crates.get(c).trim();
                if (StringUtils.isAlphanumeric(crate))
                    cratesStack.get(c).add(0, new Crate(crate));
            });
        });

        log.debug("STACKS {}", cratesStack);
        return cratesStack;
    }
}
