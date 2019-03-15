package com.company.javabussimulator.entities;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    private Long id;
    private Stop destination;
    private Stop onStop;
    private Bus bus = null;

    public Person(Stop destination, Stop onStop) {
        this.destination = destination;
        this.onStop = onStop;
    }

    public Person() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "stop_id")
    public Stop getDestination() {
        return destination;
    }

    public void setDestination(Stop destination) {
        this.destination = destination;
    }

    @ManyToOne
    @JoinColumn(name = "bus_id")
    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    @ManyToOne
    public Stop getOnStop() {
        return onStop;
    }

    public void setOnStop(Stop onStop) {
        this.onStop = onStop;
    }

    public boolean checkLine(Line line) {
        if (line.getStops().contains(destination)) {
            return true;
        }
        return false;
    }


}
