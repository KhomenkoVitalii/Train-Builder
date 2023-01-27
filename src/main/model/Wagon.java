package main.model;

import java.util.UUID;

public class Wagon {
    private final UUID id;
    private final WagonType wagonType;
    private final int number;

    public Wagon(WagonType wagonType, int number) {
        this.id = UUID.randomUUID();
        this.wagonType = wagonType;
        this.number = number;
    }

    public Wagon(Wagon wagon) {
        this.id = wagon.getId();
        this.wagonType = wagon.getWagonType();
        this.number = wagon.getNumber();
    }

    @Override
    public String toString() {
        return "Wagon id: '" + id + "', wagonType: " + wagonType.getComfortTypeName() +
                ", number " + number;
    }

    public String toSql() {
        return "('" + id + "', '" + wagonType.getId() + "', " + number + ")";
    }

    public String toFile() {
        return id + " " + wagonType.getId() + " " + number;
    }

    public UUID getId() {
        return id;
    }

    public WagonType getWagonType() {
        return wagonType;
    }

    public int getNumber() {
        return number;
    }
}
