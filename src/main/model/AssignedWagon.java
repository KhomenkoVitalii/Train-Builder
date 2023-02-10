package main.model;

import java.util.UUID;

public class AssignedWagon extends Wagon {
    private final UUID assignedId;
    private final UUID trainId;
    private final int number;

    public AssignedWagon(Wagon wagon, UUID trainId, int number) {
        super(wagon);
        this.assignedId = UUID.randomUUID();
        this.trainId = trainId;
        this.number = number;
    }

    public AssignedWagon(AssignedWagon assignedWagon) {
        super(assignedWagon);
        this.assignedId = assignedWagon.assignedId;
        this.trainId = assignedWagon.trainId;
        this.number = assignedWagon.number;
    }

    public AssignedWagon(UUID wagonId, WagonType type, UUID id, UUID trainId, int number) {
        super(wagonId, type);
        this.assignedId = id;
        this.trainId = trainId;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Wagon type: " + wagonType + ", wagons number " + number + "\n\t" +
                "id: Wagon id: " + id;
    }

    public String toSql() {
        return "('" + id + "', '" + wagonType.getId() + "', " + number + ")";
    }

    public String toFile() {
        return id + " " + wagonType.toFile() + " " + assignedId + " " + trainId + " " + number + "\n";
    }

    public UUID getAssignedId() {
        return assignedId;
    }

    public UUID getTrainId() {
        return trainId;
    }

    public int getNumber() {
        return number;
    }
}
