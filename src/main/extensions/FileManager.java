package main.extensions;

import main.Settings;
import main.app.TransportApp;
import main.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileManager {
    public static ArrayList<Train> readTrains() {
        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(Settings.trainsFilePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            TransportApp.logger.warning("IOEXCEPTION! " + e.getMessage());
            return new ArrayList<>();
        }

        ArrayList<Train> array = new ArrayList<>();

        try {
            FileReader reader = new FileReader(Settings.trainsFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            for (int i = 0; i < lines.size(); i++) {
                String[] data = bufferedReader.readLine().split(" ");

                Train train = new Train(
                        UUID.fromString(data[0]), // ID
                        data[1], // Name
                        data[2] // Code
                );
                array.add(train);
            }
        } catch (IOException e) {
            TransportApp.logger.warning("IOEXCEPTION! " + e.getMessage());
            return new ArrayList<>();
        }

        return array;
    }

    public static ArrayList<Wagon> readWagons() {
        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(Settings.wagonsFilePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            TransportApp.logger.warning("IOEXCEPTION! " + e.getMessage());
            return new ArrayList<>();
        }

        ArrayList<Wagon> array = new ArrayList<>();

        try {
            FileReader reader = new FileReader(Settings.wagonsFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            for (int i = 0; i < lines.size(); i++) {
                String[] data = bufferedReader.readLine().split(" ");

                Wagon wagon = new Wagon(
                        UUID.fromString(data[0]), // ID
                        new WagonType(
                                UUID.fromString(data[1]), // Wagon type id
                                ComfortTypes.valueOf(data[2]), // ComfortType
                                Integer.parseInt(data[3]), // seatsNumber
                                Float.parseFloat(data[4]) // Things weight per person
                        ) // WagonType
                );
                array.add(wagon);
            }
        } catch (IOException e) {
            TransportApp.logger.warning("IOEXCEPTION! " + e.getMessage());
            return new ArrayList<>();
        }

        return array;
    }

    public static ArrayList<AssignedWagon> readAssignedWagons() {
        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(Settings.assignedFilePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            TransportApp.logger.warning("IOEXCEPTION! " + e.getMessage());
            return new ArrayList<>();
        }

        ArrayList<AssignedWagon> array = new ArrayList<>();

        try {
            FileReader reader = new FileReader(Settings.assignedFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);

            for (int i = 0; i < lines.size(); i++) {
                String[] data = bufferedReader.readLine().split(" ");

                AssignedWagon wagon = new AssignedWagon(
                        UUID.fromString(data[0]),
                        new WagonType(
                                UUID.fromString(data[1]), // Wagon type id
                                ComfortTypes.valueOf(data[2]), // ComfortType
                                Integer.parseInt(data[3]), // seatsNumber
                                Float.parseFloat(data[4]) // Things weight per person
                        ),
                        UUID.fromString(data[5]), // this.id
                        UUID.fromString(data[6]), // train id
                        Integer.parseInt(data[7]) // Number
                );
                array.add(wagon);
            }
        } catch (IOException e) {
            TransportApp.logger.warning("IOEXCEPTION! " + e.getMessage());
            return new ArrayList<>();
        }

        return array;
    }

    public static void saveData(ArrayList<Train> trains, ArrayList<Wagon> wagons) {
        saveTrains(trains);
        saveAssignedWagons(trains);
        saveWagons(wagons);
    }

    public static boolean saveTrains(ArrayList<Train> trains) {
        TransportApp.logger.info("'saveTrains' was executed");
        try {
            FileWriter writer = new FileWriter(Settings.trainsFilePath);

            for (Train train : trains) {
                writer.write(train.toFile());
            }

            writer.close();
            TransportApp.logger.info("'saveTrains' was finished success!");
            return true;
        } catch (IOException e) {
            TransportApp.logger.warning("Cannot save 'CoffeeData'!\n" + e.getMessage());
            return false;
        }
    }

    public static boolean saveAssignedWagons(ArrayList<Train> trains) {
        TransportApp.logger.info("'saveAssignedWagons' was executed");
        try {
            FileWriter writer = new FileWriter(Settings.assignedFilePath);

            for (Train train: trains) {
                for (AssignedWagon wagons: train.getWagons()) {
                    writer.write(wagons.toFile());
                }
            }

            writer.close();
            TransportApp.logger.info("'saveAssignedWagons' was finished success!");
            return true;
        } catch (IOException e) {
            TransportApp.logger.warning("Cannot save  'VansData'!" + e.getMessage());
            return false;
        }
    }

    public static boolean saveWagons(ArrayList<Wagon> wagons) {
        TransportApp.logger.info("'saveWagons' was executed");
        try {
            FileWriter writer = new FileWriter(Settings.wagonsFilePath);

            for (Wagon wagon: wagons) {
                writer.write(wagon.toFile());
            }

            writer.close();
            TransportApp.logger.info("'saveWagons' was finished success!");
            return true;
        } catch (IOException e) {
            TransportApp.logger.warning("Cannot save 'VansGoods'!" + e.getMessage());
            return false;
        }
    }
}