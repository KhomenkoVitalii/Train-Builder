package main.commands.wagons;

import main.app.TrainBuilder;
import main.commands.Command;
import main.extensions.Extensions;

import java.util.UUID;

public class DeleteWagonCommand extends Command {
    private final UUID id;
    public DeleteWagonCommand(TrainBuilder trainBuilder, String id) {
        super(trainBuilder);
        this.id = Extensions.parseId(id);
    }

    @Override
    public void execute() {
        if (id != null) {
            trainBuilder.deleteWagon(id);
        } else System.out.println("Incorrect entered data!");

    }
}
