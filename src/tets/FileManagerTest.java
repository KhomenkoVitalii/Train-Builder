package tets;

import main.app.TransportApp;
import main.extensions.FileManager;
import main.model.Train;
import main.model.Wagon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class FileManagerTest {
    ArrayList<Train> readiedTrains;
    ArrayList<Wagon> readiedWagons;

    @Before
    public void setUp() {
        TransportApp.initLogger();
        readiedTrains = new ArrayList<>();
        readiedWagons = new ArrayList<>();
    }

    @Test
    public void saveTrain() {
        boolean flag = FileManager.saveTrains(readiedTrains);
        assertTrue(flag);
    }

    @Test
    public void saveWagon() {
        boolean flag = FileManager.saveWagons(readiedWagons);
        assertTrue(flag);
    }

    @Test
    public void saveGoods() {
        boolean flag = FileManager.saveAssignedWagons(readiedTrains);
        assertTrue(flag);
    }

    @Test
    public void readCoffees() {
        readiedTrains = FileManager.readTrains();
    }

    @Test
    public void readVans() {
        readiedWagons = FileManager.readWagons();
    }
}
