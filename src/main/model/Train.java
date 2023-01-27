package main.model;

import java.util.UUID;

public class Train {
    private final UUID id;
    private final String name;
    private final String code;

    public Train(String name, String code) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.code = code;
    }

    public Train(Train train) {
        this.id = train.getId();
        this.name = train.getName();
        this.code = train.getCode();
    }

    @Override
    public String toString() {
        return "name: " + name + ", code: " + code;
    }

    public String toSQL() {
        return "('" + id + "', '" + name + "', '" + code + "')";
    }

    public String toFile() {
        return id + " " + name + " " + code;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
