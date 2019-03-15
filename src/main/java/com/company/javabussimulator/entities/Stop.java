package com.company.javabussimulator.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "stop")
public class Stop {
    private Long id;
    private String name;
    private Set<Person> passengers;
    private Line line;

    public Stop(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Stop(String name) {
        this.name = name;
    }

    public Stop() {
    }

    public void addPassenger(Person person) {
        passengers.add(person);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stop_id")
    public Long getId() {
        return id;
    }

    @Column(name = "stop_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Person.class, mappedBy = "onStop", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Person> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Person> passengers) {
        this.passengers = passengers;
    }

    @ManyToOne
    @JoinColumn(name = "line_id")
    public Line getLine() {
        return line;
    }

    public void deletePassenger(Person passenger) {
        if (getPassengers().contains(passenger)) {
            passengers.remove(passenger);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stop stop = (Stop) o;
        return id == stop.id &&
                Objects.equals(name, stop.name) &&
                Objects.equals(passengers, stop.passengers);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, passengers);
    }


}
