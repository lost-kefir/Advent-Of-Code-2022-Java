package adventcode.day5;

import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class CrateMover9000 implements Crane {
    
    @Override
    public List<Stack<Crate>> moveCrates(List<Stack<Crate>> stacks, Procedure procedure) {
        int from = procedure.fromColumn() - 1;
        int to = procedure.toColumn() - 1;
        IntStream.range(0, procedure.numberOfElements()).forEach(value -> {
            Crate crate = stacks.get(from).pop();
            stacks.get(to).add(crate);
        });
        return stacks;
    }
}
