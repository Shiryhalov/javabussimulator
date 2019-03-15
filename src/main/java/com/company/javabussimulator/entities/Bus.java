package com.company.javabussimulator.entities;

import com.company.javabussimulator.Simulation;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "bus")
public class Bus implements Runnable {
    private Long id;
    private Integer maxCapacity;
    private List<Person> passengers = Collections.synchronizedList(new ArrayList<>());
    private Stop currentStop = null;
    private Line currentLine = null;

    public Bus() {
    }

    public Bus(Integer maxCapacity, Line currentLine) {
        this.maxCapacity = maxCapacity;
        this.currentLine = currentLine;
    }

    public Bus(Long id, int maxCapacity, List<Person> passengers) {
        this.id = id;
        this.maxCapacity = maxCapacity;
        this.passengers = passengers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_id")
    public Long getId() {
        return id;
    }

    @Column(name = "bus_maxCapacity")
    public int getMaxCapacity() {
        return maxCapacity;
    }

    @OneToMany(targetEntity = Person.class, mappedBy = "bus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Person> getPassengers() {
        return passengers;
    }

    @ManyToOne
    @JoinColumn(name = "stop_id")
    public Stop getCurrentStop() {
        return currentStop;
    }

    @ManyToOne
    @JoinColumn(name = "line_id")
    public Line getCurrentLine() {
        return currentLine;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassengers(List<Person> passengers) {
        this.passengers = passengers;
    }

    public void setCurrentStop(Stop currentStop) {
        this.currentStop = currentStop;
    }

    public void setCurrentLine(Line currentLine) {
        this.currentLine = currentLine;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /*Поток высадки/посадки пассажиров на автобус*/
    @Override
    public void run() {
        Simulation s = new Simulation();
        Stop stop = getCurrentStop();
        Iterator<Person> personIterator = stop.getPassengers().iterator();
        Iterator<Person> passengerIterator = getPassengers().iterator();
        while (passengerIterator.hasNext()) {
            Person passenger = passengerIterator.next();
            if (passenger.getDestination().equals(stop)) {
                /*this.passengers.remove(passenger);*/
                System.out.printf("The Passenger id %d got off the Bus id %d\n", passenger.getId(), this.getId());
                passengerIterator.remove();
                sleep(Long.valueOf(s.getRandomIntInRangeEnclosed(s.passengerDurationMin, s.passengerDurationMin)));
            }
        }
        while (personIterator.hasNext()) {
            Person person = personIterator.next();
            if (person.checkLine(getCurrentLine()) && getPassengers().size() < getMaxCapacity()) {
                this.passengers.add(person);
                System.out.printf("The Passenger id %d get on the Bus id %d\n", person.getId(), this.getId());
                /*personIterator.remove();*/
                sleep(Long.valueOf(s.getRandomIntInRangeEnclosed(s.passengerDurationMin, s.passengerDurationMin)));
            }
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getPassengersGetOn() {
        Stop stop = getCurrentStop();
        Iterator<Person> personIterator = stop.getPassengers().iterator();
        while (personIterator.hasNext()) {
            Person person = personIterator.next();
            if (person.checkLine(getCurrentLine())) {
                this.passengers.add(person);
                System.out.printf("Passenger with id %d get on Bus with id %d", person.getId(), this.getId());
                personIterator.remove();
            }
        }
    }

    public void removePassngers() {

    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", maxCapacity=" + maxCapacity +
                ", passengers=" + passengers +
                ", currentStop=" + currentStop +
                ", currentLine=" + currentLine +
                '}';
    }
}
