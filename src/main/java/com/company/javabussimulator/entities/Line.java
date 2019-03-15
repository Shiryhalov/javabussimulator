package com.company.javabussimulator.entities;

import com.company.javabussimulator.Simulation;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "line")
public class Line implements Runnable {
    private Long id;
    private String number;
    private List<Stop> stops = Collections.synchronizedList(new ArrayList<>());
    private Double interval;
    private List<Bus> buses = Collections.synchronizedList(new ArrayList<>());
    private Double cost;

    public Line(Long id, String number, List<Stop> stops, double interval) {
        this.id = id;
        this.number = number;
        this.stops = stops;
        this.interval = interval;
    }

    public Line() {
    }

    public void addBus(Bus bus) {
        buses.add(bus);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "line_number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @OneToMany(targetEntity = Stop.class, mappedBy = "line", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    @OneToMany(targetEntity = Bus.class, mappedBy = "currentLine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    @Column(name = "line_interval")
    public double getInterval() {
        return interval;
    }

    public void setInterval(Double interval) {
        this.interval = interval;
    }

    @Column(name = "line_cost")
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    /*public boolean isOnSim() {
        return onSim;
    }

    public void setOnSim(boolean onSim) {
        this.onSim = onSim;
    }*/

    /*@Override
    public void run() {
        Simulation s = new Simulation();
        int stopCounter = 0;
        int busCounter = 0;
        while (isOnSim()) {
            Iterator<Person> personIterator = getStops().get(stopCounter).getPassengers().iterator();
            while (personIterator.hasNext()) {
                Person i = personIterator.next();
                System.out.printf("Автобус %d прибыл на остановку %s\n", buses.get(busCounter).getId(),
                        stops.get(stopCounter).getName());
                buses.get(busCounter).setCurrentLine(this);
                if (i.checkLine(getBuses().get(busCounter).getCurrentLine())) {
                    buses.get(busCounter).addPassengers(i);
                    System.out.printf("Пассажир %d совершил посадку на автобус %d\n", i.getId(),
                            buses.get(busCounter).getId());
                    personIterator.remove();
                    stopCounter++;
                    sleep(Long.valueOf(s.getRandomIntInRangeEnclosed(s.durationMin, s.durationMax)));
                }
            }
        }
    }*/


    @Override
    public void run() {
        Simulation s = new Simulation();
        while (true) {
            Iterator<Stop> stopIterator = getStops().iterator();
            Iterator<Bus> busIterator = getBuses().iterator();
            while (busIterator.hasNext()) {
                Bus bus = busIterator.next();
                bus.setCurrentLine(this);
                while (stopIterator.hasNext()) {
                    Stop stop = stopIterator.next();
                    bus.setCurrentStop(stop);
                    System.out.printf("The Bus id %d is arrived on Stop named %s\n", bus.getId(), stop.getName());
                    Thread thread = new Thread(bus);
                    thread.start();
                    sleep(Long.valueOf(s.getRandomIntInRangeEnclosed(s.passengerDurationMin, s.passengerDurationMax)));
                }
            }
        }
    }


    private void sleep(long ms) {
        try {
            Thread.sleep(ms + 4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", stops=" + stops +
                ", interval=" + interval +
                ", buses=" + buses +
                ", cost=" + cost +
                '}';
    }
}
