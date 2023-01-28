package main.model;

import java.util.UUID;

public class Wagon {
    protected final UUID id;
    protected final WagonType wagonType;

    public Wagon(WagonType wagonType) {
        this.id = UUID.randomUUID();
        this.wagonType = wagonType;
    }

    public Wagon(Wagon wagon) {
        this.id = wagon.getWagonId();
        this.wagonType = wagon.getWagonType();
    }

    public Wagon(UUID id, WagonType type) {
        this.id = id;
        this.wagonType = type;
    }

    public UUID getWagonId() {
        return id;
    }

    public WagonType getWagonType() {
        return wagonType;
    }

    public String toFile() {
        return id + " " + wagonType.toFile();
    }
}
