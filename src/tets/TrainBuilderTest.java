package tets;

import main.app.TrainBuilder;
import main.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TrainBuilderTest extends TrainBuilder {
    private Train testTrain;
    private AssignedWagon testAssignedWagon;
    private float[] coeff;
    private Wagon testWagon;

    @Before
    public void setUp() {
        testTrain = new Train("TEST", "17AR8FE8");
        testWagon = new Wagon(new WagonType(ComfortTypes.ordinary, 100, 20));

        trains = new ArrayList<>();
        wagons = new ArrayList<>();

        wagons.add(new Wagon(new WagonType(ComfortTypes.ordinary, 150, 20)));
        wagons.add(new Wagon(new WagonType(ComfortTypes.ordinary, 174, 15)));
        wagons.add(new Wagon(new WagonType(ComfortTypes.business, 95, 30)));
        wagons.add(new Wagon(new WagonType(ComfortTypes.business, 90, 35)));
        wagons.add(new Wagon(new WagonType(ComfortTypes.vip, 40, 100)));
        wagons.add(new Wagon(new WagonType(ComfortTypes.vip, 40, 100)));

        coeff = new float[3];
        coeff[0] = 0.5F;
        coeff[1] = 0.3F;
        coeff[2] = 0.2F;

        createTrain(testTrain, 1250, coeff);
        testAssignedWagon = new AssignedWagon(wagons.get(0), testTrain.getId(), 5);
    }

    @Test
    public void createTrainDataTest() {
        int unexpectedTrainsSize = 0;
        createTrain(testTrain, 1000, coeff);
        assertNotEquals(unexpectedTrainsSize, trains.size());
    }

    @Test
    public void addWagonTest() {
        wagons.clear();
        int unexpectedWagonsSize = 0;
        addWagon(ComfortTypes.ordinary, 150, 20);
        assertNotEquals(unexpectedWagonsSize, wagons.size());
    }

    @Test
    public void addWagonToTheTrainTest() {
        trains.add(testTrain);
        wagons.add(testWagon);
        int expectedAssignedWagonSize = 1;
        addWagonToTheTrain(testTrain.getId(), testWagon.getWagonId(), 5);
        assertEquals(expectedAssignedWagonSize, testTrain.getWagons().size());
    }

    /* SORT */
    @Test
    public void sortWagonsTest() {
        createTrain(testTrain, 1000, coeff);
        UUID id = trains.get(1).getId();
        sortWagons(id);
    }

    /* FINDER */
    @Test
    public void findWagonsBySeatsNumberTest() {
        ArrayList<Wagon> founded = findWagonsBySeatsNumber(100, 200);
        int unexpectedSize = 0;
        assertNotEquals(unexpectedSize, founded.size());
    }

    /* DELETE */

    @Test
    public void removeWagonFromTheTrainTest() {
        trains.clear();
        trains.add(testTrain);
        trains.get(0).setWagons(new ArrayList<>());
        trains.get(0).getWagons().add(testAssignedWagon);
        int expectedAssignedWagonSize = 0;
        removeWagonFromTheTrain(testTrain.getId(), testAssignedWagon.getAssignedId());
        assertEquals(expectedAssignedWagonSize, trains.get(0).getWagons().size());
    }

    @Test
    public void deleteTrainTest() {
        trains.clear();
        trains.add(testTrain);
        int expectedTrainsSize = 0;
        deleteTrain(testTrain.getId());
        assertNotEquals(expectedTrainsSize, trains.size());
    }

    @Test
    public void deleteWagonTest() {
        wagons.clear();
        wagons.add(testWagon);
        int expectedWagonsSize = 0;
        deleteWagon(testWagon.getWagonId());
        assertEquals(expectedWagonsSize, wagons.size());
    }

    /* OTHERS */
    @Test
    public void createAssignedWagonTest() {
        int expectedWagonsNumber = 2;
        var result = createAssignedWagon(wagons.get(0), 300, UUID.randomUUID());
        var result2 = createAssignedWagon(wagons.get(0), 300, UUID.randomUUID());
        assertEquals(expectedWagonsNumber, result.getNumber());
        assertEquals(expectedWagonsNumber, result2.getNumber());
    }

    @Test
    public void getWagonsByComfortTest() {
        int expectedSize = 2;
        var result = getWagonsByComfort(wagons, ComfortTypes.vip.name());
        assertEquals(expectedSize, result.size());
    }
}
