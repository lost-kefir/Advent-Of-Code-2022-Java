package adventcode.day5;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import reader.TestInputReader;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class SupplyStacks {

    @Getter
    private final List<Stack<Crate>> stacks = new ArrayList<>();
    @Getter
    private final List<Procedure> procedures;
    private final static Pattern drawingDivide = Pattern.compile("(?m)^\\s*$"); // will divide drawing to crates positions and procedures


    public SupplyStacks(@NonNull String inputFileName) {
        String drawing = new TestInputReader(inputFileName).getInputAsString();
        initializeStacks(drawing);
        this.procedures = getProcedureFromDrawing(drawing);
    }

    private void initializeStacks(String string) {
        String stackDrawing = new Drawing(string).getStacks();
        List<String> stacks = stackDrawing.lines().toList();
        int maxColumns = 0;
        for (int i = 0; i < stacks.size(); i++) {
            List<String> crates = Arrays.stream(stacks.get(i).replaceAll("[\\[\\]]", "").split(StringUtils.SPACE)).toList();
            if (i == 0) {
                maxColumns = crates.size();
                IntStream.range(0, maxColumns).forEach(value -> this.stacks.add(new Stack<>()));
            }
            IntStream.range(0, maxColumns).forEach(value -> {
                String s = crates.get(value).trim();
                char c = s.charAt(0);
                if (Character.isAlphabetic(c))
                    this.stacks.get(value).add(0, new Crate(String.valueOf(c)));
            });
        }

        log.debug("STACK SIZE {}", this.stacks.size());
        log.debug("STACKS {}", this.stacks);
    }

    private List<Procedure> getProcedureFromDrawing(String drawing) {
        String[] sections = drawing.split(drawingDivide.pattern());
        log.debug("procedures input: {}", sections[1].trim());

        List<Procedure> procedures = new LinkedList<>();
        sections[1].trim().lines().forEach(line -> {
            log.debug("Line: {}", line);
            Procedure procedure = new Procedure(line);
            procedures.add(procedure);
        });

        return procedures;
    }

    public void printCurrentStacks() {

    }

    public String moveCratesByProcedure() {
        // do all moves
        procedures.stream().forEach(this::moveV1);
        return stacks.stream().map(stack -> stack.lastElement().getValue()).collect(Collectors.joining(""));
    }
    
    private void moveV1(Procedure procedure) {
        for (int i = 0; i < procedure.getNumberOfElements(); i++) {
            this.stacks.get(procedure.getTo() - 1).add(this.stacks.get(procedure.getFrom() - 1).pop());
        }
    }

    private void moveV2(Procedure procedure) {
        Stack<Crate> tempStack = new Stack<>();

        for (int i = 0; i < procedure.getNumberOfElements(); i++) {
            tempStack.add(this.stacks.get(procedure.getFrom() - 1).pop());
        }

        int initialSize = tempStack.size();
        for (int i = 0; i < initialSize; i++)
            this.stacks.get(procedure.getTo() - 1).add(tempStack.pop());
    }

    public String getTopCrates() {
        return "";
    }

    public String moveCratesByProcedureV2() {
        // do all moves
        procedures.stream().forEach(this::moveV2);
        return stacks.stream().map(stack -> stack.lastElement().getValue()).collect(Collectors.joining(""));

    }
}
