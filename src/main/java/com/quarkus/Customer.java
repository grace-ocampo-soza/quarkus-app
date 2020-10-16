package com.quarkus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
    private Integer id;
    private String name;
    private String surname;

    @Override
    public String toString() { return name + " " + surname; }
}
