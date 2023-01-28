package main.commands.assign.wagon;

import main.app.TrainBuilder;
import main.commands.Command;
import main.extensions.Extensions;

import java.util.UUID;

public class AddAssignWagonCommand extends Command {
    private final UUID trainId;
    private final UUID wagonId;
    private final int number;

    public AddAssignWagonCommand(TrainBuilder trainBuilder, String trainId, String wagonId, String number) {
        super(trainBuilder);
        this.trainId = Extensions.parseId(trainId);
        this.wagonId = Extensions.parseId(wagonId);
        this.number = Extensions.parseInt(number);
    }

    @Override
    public void execute() {
        trainBuilder.addWagonToTheTrain(trainId, wagonId, number);
    }
}
