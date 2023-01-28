package main.commands.assign.wagon;

import main.app.TrainBuilder;
import main.commands.Command;
import main.extensions.Extensions;

import java.util.UUID;

public class RemoveAssignWagons extends Command {
    private final UUID trainId;
    private final UUID assignedId;

    public RemoveAssignWagons(TrainBuilder trainBuilder, String trainId, String assignedId) {
        super(trainBuilder);
        this.trainId = Extensions.parseId(trainId);
        this.assignedId = Extensions.parseId(assignedId);
    }

    @Override
    public void execute() {
        trainBuilder.removeWagonFromTheTrain(trainId, assignedId);
    }
}
