package main.commands.trains;

import main.app.TrainBuilder;
import main.commands.Command;
import main.extensions.Extensions;
import main.model.Train;

public class CreateTrainCommand extends Command {
    private final String name;
    private final String code;
    private final int expectedSitsNumber;
    private final float[] coefficients;

    public CreateTrainCommand(TrainBuilder trainBuilder, String name, String code, String expectedSitsNumber, String coefficients) {
        super(trainBuilder);
        this.name = name;
        this.code = code;
        this.expectedSitsNumber = Extensions.parseInt(expectedSitsNumber);

        this.coefficients = new float[3];
        String[] temp = coefficients.split(" ");
        for (int i = 0; i < temp.length; i++){
            this.coefficients[i] = Extensions.parseFloat(temp[i]);
        }
    }

    @Override
    public void execute() {
        trainBuilder.createTrain(new Train(name, code), expectedSitsNumber, coefficients);
    }
}
