package test.commands;

import main.app.TrainBuilder;
import main.commands.trains.CreateTrainCommand;
import main.commands.trains.DeleteTrainCommand;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class CommandTrainsTest {
    TrainBuilder trainBuilder;

    CreateTrainCommand createTrainCommand;
    DeleteTrainCommand deleteTrainCommand;

    @Before
    public void setUp() {
        trainBuilder = new TrainBuilder();
        createTrainCommand = new CreateTrainCommand(trainBuilder, "Test", "TestCode", "400", "0.4", "0.5", "0.1");
        deleteTrainCommand = new DeleteTrainCommand(trainBuilder, UUID.randomUUID().toString());
    }

    @Test
    public void setCreateTrainCommandTest() {
        createTrainCommand.execute();
    }

    @Test
    public void setDeleteTrainCommandTest() {
        deleteTrainCommand.execute();
    }
}
