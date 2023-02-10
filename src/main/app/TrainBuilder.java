package main.app;


import main.extensions.FileManager;
import main.model.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;


public class TrainBuilder {
    // Our trains
    protected ArrayList<Train> trains;

    // Види вагонів, які є у наявності
    protected ArrayList<Wagon> wagons;

    // Список вагонів, на основі видів вагонів <Wagon>, що вже залучені до потягу
    private ArrayList<AssignedWagon> assignedWagons;

    public TrainBuilder() {
        TransportApp.initLogger();
        TransportApp.logger.info("Starts initialize TrainBuilder");
        trains = FileManager.readTrains();
        wagons = FileManager.readWagons();
        assignedWagons = FileManager.readAssignedWagons();
        assignWagonsToTheTrains(trains, assignedWagons);
        TransportApp.logger.info("Finished initialize TrainBuilder");
    }

    public void saveData() {
        FileManager.saveData(trains, wagons);
    }

    public void createTrain(Train trainData, int expectedSeatsNumber, float[] coefficients) {
        TransportApp.logger.info("Starting execute 'createTrain' method");
        Train train = new Train(trainData.getName(), trainData.getCode());

        TransportApp.logger.info("Sorting wagons by the same comfort type");
        // Обираємо один варіант
        Wagon ordinaryWagon = chooseWagon(getWagonsByComfort(wagons, ComfortTypes.ordinary.name()));
        Wagon businessWagon = chooseWagon(getWagonsByComfort(wagons, ComfortTypes.business.name()));
        Wagon vipWagon = chooseWagon(getWagonsByComfort(wagons, ComfortTypes.vip.name()));

        ArrayList<AssignedWagon> assignedWagons = new ArrayList<>();

        TransportApp.logger.info("Getting and writing in ArrayList AssignedWagon's");
        var wagon = createAssignedWagon(ordinaryWagon, Math.round(expectedSeatsNumber * coefficients[0]), train.getId());
        if (wagon != null) {
            assignedWagons.add(wagon);
        }
        wagon = createAssignedWagon(businessWagon, Math.round(expectedSeatsNumber * coefficients[1]), train.getId());
        if (wagon != null) {
            assignedWagons.add(wagon);
        }
        wagon = createAssignedWagon(vipWagon, Math.round(expectedSeatsNumber * coefficients[2]), train.getId());
        if (wagon != null) {
            assignedWagons.add(wagon);
        }

        TransportApp.logger.info("Assign them to the train");
        train.assignWagons(assignedWagons);
        trains.add(train);
    }

    public void deleteTrain(UUID id) {
        TransportApp.logger.info("'deleteTrain' was executed");
        for (int i = 0; i < trains.size(); i++) {
            if (trains.get(i).getId().compareTo(id) == 0) {
                if (assignedWagons.size() != 0) {
                    assignedWagons.removeAll(trains.get(i).getWagons());
                    trains.remove(i);
                }
                TransportApp.logger.info("train was deleted!");
                break;
            }
        }
    }

    public void addWagon(ComfortTypes type, int seatsNumber, float thingsWeight) {
        Wagon wagon = new Wagon(new WagonType(type, seatsNumber, thingsWeight));
        wagons.add(wagon);
        TransportApp.logger.info("Added new wagon!");
    }

    public void deleteWagon(UUID id) {
        wagons.removeIf(wagon -> wagon.getWagonId().compareTo(id) == 0);
        TransportApp.logger.info("Wagon was deleted");
    }

    public void addWagonToTheTrain(UUID trainId, UUID wagonId, int number) {
        TransportApp.logger.info("'addWagonToTheTrain' executed");
        for (Train train : trains) {
            if (train.getId().compareTo(trainId) == 0) {
                TransportApp.logger.info("Find train");
                for (var wagon : wagons) {
                    if (wagon.getWagonId().compareTo(wagonId) == 0) {
                        if (train.getWagons() == null) {
                            train.setWagons(new ArrayList<>());
                        }
                        train.getWagons().add(new AssignedWagon(wagon, trainId, number));
                        TransportApp.logger.info("Wagon was added");
                    }
                }
            }
        }
    }

    public void removeWagonFromTheTrain(UUID trainId, UUID assignedId) {
        for (Train train : trains) {
            if (train.getId().compareTo(trainId) == 0) {
                train.getWagons().removeIf(wagon -> wagon.getAssignedId().compareTo(assignedId) == 0);
                TransportApp.logger.info("Wagon was deleted");
            }
        }
    }

    // USING REVERSE BUBBLE SORTING
    // From VIP in the head of train to ordinary in the end
    public void sortWagons(UUID trainId) {
        TransportApp.logger.info("'sortWagons' was executed!");
        for (Train train : trains) {
            if (train.getId().compareTo(trainId) == 0) {
                TransportApp.logger.info("Find train that we looking for!");
                ArrayList<AssignedWagon> wagons = train.getWagons();
                for (int i = 0; i < wagons.size() - 1; i++) {
                    for (int j = 0; j < wagons.size() - i - 1; j++) {
                        if (wagons.get(j).getWagonType().getComfortType().ordinal() <= wagons.get(j + 1).getWagonType().getComfortType().ordinal()) {
                            AssignedWagon temp = wagons.get(j + 1);
                            wagons.set(j + 1, wagons.get(j));
                            wagons.set(j, temp);
                        }
                    }
                }
                TransportApp.logger.info("Sorting was finished");
                break;
            }
        }
    }

    public ArrayList<Wagon> findWagonsBySeatsNumber(int minValue, int maxValue) {
        ArrayList<Wagon> result = new ArrayList<>();
        for (Wagon wagon : wagons) {
            int temp = wagon.getWagonType().getSeatsNumber();
            if (temp > minValue && temp < maxValue) {
                result.add(wagon);
            }
        }
        return result;
    }


    /* PRINTS */
    public void printTrains() {
        trains.forEach(train -> {
            System.out.println(train.toString());
        });
    }

    public void printTrain(UUID id) {
        for (var train : trains) {
            if (train.getId().compareTo(id) == 0) {
                System.out.println(train);
                train.getWagons().forEach(System.out::println);
            }
        }
    }

    public void printWagons() {
        wagons.forEach(wagon -> System.out.println(wagon.toString()));
    }


    protected void assignWagonsToTheTrains(ArrayList<Train> trains, ArrayList<AssignedWagon> wagons) {
        TransportApp.logger.info("Starts assigning wagons to the trains");
        for (Train train : trains) {
            ArrayList<AssignedWagon> temp = new ArrayList<>();
            for (AssignedWagon wagon : wagons) {
                if (train.getId().compareTo(wagon.getTrainId()) == 0) {
                    temp.add(wagon);
                }
            }
            train.assignWagons(temp);
        }
    }

    /* ON CREATE TRAIN METHODS */

    protected @Nullable AssignedWagon createAssignedWagon(Wagon wagon, int seatsNumber, UUID trainId) {
        if (wagon != null) {
            int wagonsNumber = Math.round(seatsNumber / wagon.getWagonType().getSeatsNumber());
            return new AssignedWagon(wagon, trainId, wagonsNumber);
        }
        return null;
    }

    protected @Nullable Wagon chooseWagon(ArrayList<Wagon> wagons) {
        TransportApp.logger.info("Choosing wagon with better proportion (seats/weight per person)");
        if (wagons.isEmpty()) {
            TransportApp.logger.info("Wagons are empty!");
            return null;
        } else if (wagons.size() > 1) {
            // The proportion of the number of passengers to the weight of things per person
            float currentCoeff = 0;
            // Saved instance of wagon with the best proportion
            int savedIndex = 0;

            // For each wagon
            for (int i = 0; i < wagons.size(); i++) {
                // If index is initialize...
                if (currentCoeff != 0) {
                    float temp = wagons.get(i).getWagonType().getSeatsNumber() / wagons.get(i).getWagonType().getMaxThingsWeightPerPerson();
                    if (currentCoeff < temp) {
                        currentCoeff = temp;
                        savedIndex = i;
                    }
                } else {
                    // initialize
                    currentCoeff = wagons.get(i).getWagonType().getSeatsNumber() / wagons.get(i).getWagonType().getMaxThingsWeightPerPerson();
                }
            }


        } else {
            return wagons.get(0);
        }
        return null;
    }

    protected ArrayList<Wagon> getWagonsByComfort(ArrayList<Wagon> wagons, String comfortType) {
        TransportApp.logger.info("Sorting wagons by the same comfort type");
        var result = new ArrayList<Wagon>();
        for (var wagon : wagons) {
            if (Objects.equals(wagon.getWagonType().getComfortTypeName(), comfortType)) {
                result.add(wagon);
            }
        }
        return result;
    }
}
