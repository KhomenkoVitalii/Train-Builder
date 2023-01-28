package main.commands.assign.wagon;

import main.app.TrainBuilder;
import main.commands.Command;
import main.extensions.Extensions;

import java.util.UUID;

public class SortWagonsCommand extends Command {
    private final UUID trainId;

    public SortWagonsCommand(TrainBuilder trainBuilder, String trainId) {
        super(trainBuilder);
        this.trainId = Extensions.parseId(trainId);
    }

    @Override
    public void execute() {
        trainBuilder.sortWagons(trainId);
    }
}
