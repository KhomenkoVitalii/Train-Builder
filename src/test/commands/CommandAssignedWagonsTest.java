package test.commands;

import main.app.TrainBuilder;
import main.commands.assign.wagon.AssignWagonCommand;
import main.commands.assign.wagon.RemoveAssignWagons;
import main.commands.assign.wagon.SortWagonsCommand;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class CommandAssignedWagonsTest {
    TrainBuilder trainBuilder;

    AssignWagonCommand assignWagonCommand;
    RemoveAssignWagons removeAssignWagons;
    SortWagonsCommand sortWagonsCommand;

    @Before
    public void setUp() {
        trainBuilder = new TrainBuilder();

        assignWagonCommand = new AssignWagonCommand(trainBuilder, UUID.randomUUID().toString(), UUID.randomUUID().toString(), "10");
        removeAssignWagons = new RemoveAssignWagons(trainBuilder, UUID.randomUUID().toString(), UUID.randomUUID().toString());
        sortWagonsCommand = new SortWagonsCommand(trainBuilder, UUID.randomUUID().toString());
    }

    @Test
    public void setAddAssignWagonCommandTest() {
        assignWagonCommand.execute();
    }

    @Test
    public void setRemoveAssignWagonsTest() {
        removeAssignWagons.execute();
    }

    @Test
    public void setSortWagonsCommandTest() {
        sortWagonsCommand.execute();
    }
}
