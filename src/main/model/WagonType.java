package main.model;

import java.util.UUID;

public class WagonType {
    private final UUID id;
    private final ComfortTypes comfortType;
    private final int seatsNumber;
    private final float maxThingsWeightPerPerson;

    public WagonType(ComfortTypes comfortType, int seatsNumber, float maxThingsWeightPerPerson) {
        this.id = UUID.randomUUID();
        this.comfortType = comfortType;
        this.seatsNumber = seatsNumber;
        this.maxThingsWeightPerPerson = maxThingsWeightPerPerson;
    }

    public WagonType(WagonType wagonType) {
        this.id = wagonType.getId();
        this.comfortType = wagonType.comfortType;
        this.seatsNumber = wagonType.getSeatsNumber();
        this.maxThingsWeightPerPerson = wagonType.getMaxThingsWeightPerPerson();
    }

    public WagonType(UUID id, ComfortTypes comfortType, int seatsNumber, float maxThingsWeightPerPerson) {
        this.id = id;
        this.comfortType = comfortType;
        this.seatsNumber = seatsNumber;
        this.maxThingsWeightPerPerson = maxThingsWeightPerPerson;
    }

    @Override
    public String toString() {
        return "Comfort type: " + comfortType.name() + ", seats number: " + seatsNumber +
            ", max weight of things per person";
    }

    public String toSql() {
        return "('" + id + "', '" + comfortType.name() + "', " + seatsNumber + ", " + maxThingsWeightPerPerson + ")";
    }

    public String toFileType() {
        return id + " " + comfortType.name() + " " + seatsNumber + " " + maxThingsWeightPerPerson;
    }

    public UUID getId() {
        return id;
    }

    public ComfortTypes getComfortType() {return comfortType;}

    public String getComfortTypeName() {
        return comfortType.name();
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public float getMaxThingsWeightPerPerson() {
        return maxThingsWeightPerPerson;
    }
}
