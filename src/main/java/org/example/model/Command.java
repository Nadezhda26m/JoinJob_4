package org.example.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Command implements Comparable<Command> {

    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Command o) {
        return this.name.compareTo(o.name);
    }
}
