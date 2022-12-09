package adventcode.day5;

import adventcode.day5.Crate;

import java.util.List;
import java.util.Stack;

public interface Crane {
    List<Stack<Crate>> moveCrates(List<Stack<Crate>> stacks, Procedure procedure);
}
