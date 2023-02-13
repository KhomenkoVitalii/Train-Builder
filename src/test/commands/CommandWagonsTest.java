package test.commands;

import main.app.TrainBuilder;
import main.commands.wagons.AddWagonCommand;
import main.commands.wagons.DeleteWagonCommand;
import main.commands.wagons.FindWagonsSeatsNumberCommand;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class CommandWagonsTest {
    TrainBuilder trainBuilder;

    AddWagonCommand addWagonCommand;
    DeleteWagonCommand deleteWagonCommand;
    FindWagonsSeatsNumberCommand findWagonsSeatsNumberCommand;

    @Before
    public void setUp() {
        trainBuilder = new TrainBuilder();
        // commands
        addWagonCommand = new AddWagonCommand(trainBuilder, "ordinary", "300", "45");
        deleteWagonCommand = new DeleteWagonCommand(trainBuilder, UUID.randomUUID().toString());
        findWagonsSeatsNumberCommand = new FindWagonsSeatsNumberCommand(trainBuilder, "100", "300");
    }

    @Test
    public void setAddWagonCommandTest() {
        addWagonCommand.execute();
    }

    @Test
    public void setDeleteWagonCommandTest() {
        deleteWagonCommand.execute();
    }

    @Test
    public void setFindWagonsSeatsNumberCommandTest() {
        findWagonsSeatsNumberCommand.execute();
    }
}
