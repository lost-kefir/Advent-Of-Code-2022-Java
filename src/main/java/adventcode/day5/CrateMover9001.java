package adventcode.day5;

import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class CrateMover9001 implements Crane {
   
    @Override
    public List<Stack<Crate>> moveCrates(List<Stack<Crate>> stacks, Procedure procedure) {
        int from = procedure.fromColumn() - 1;
        int to = procedure.toColumn() - 1;
        Stack<Crate> temp = new Stack<>();

        IntStream.range(0, procedure.numberOfElements()).forEach(value -> {
            Crate crate = stacks.get(from).pop();
            temp.add(crate);
        });

        IntStream.range(0, temp.size()).forEach(value -> stacks.get(to).add(temp.pop()));
        return stacks;
    }
}
