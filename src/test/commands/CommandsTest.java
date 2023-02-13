package test.commands;

import main.app.TrainBuilder;
import main.commands.SaveCommand;
import main.commands.prints.PrintTrainCommand;
import main.commands.prints.PrintTrainsCommand;
import main.commands.prints.PrintWagonsCommand;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class CommandsTest {
    TrainBuilder trainBuilder;

    PrintTrainCommand printTrainCommand;
    PrintTrainsCommand printTrainsCommand;
    PrintWagonsCommand printWagonsCommand;
    SaveCommand saveCommand;

    @Before
    public void setUp() {
        trainBuilder = new TrainBuilder();
        // commands
        printTrainCommand = new PrintTrainCommand(trainBuilder, UUID.randomUUID().toString());
        printTrainsCommand = new PrintTrainsCommand(trainBuilder);
        printWagonsCommand = new PrintWagonsCommand(trainBuilder);
        saveCommand = new SaveCommand(trainBuilder);
    }

    @Test
    public void printTrainCommandTest() {
        printTrainCommand.execute();
    }

    @Test
    public void printTrainsCommandTest() {
        printTrainsCommand.execute();
    }

    @Test
    public void printWagonsCommandTest() {
        printWagonsCommand.execute();
    }

    @Test
    public void saveCommandTest() {
        saveCommand.execute();
    }
}
