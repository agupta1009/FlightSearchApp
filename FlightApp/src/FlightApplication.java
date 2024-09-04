import com.flightapp.controller.FlightController;
import com.flightapp.model.Flight;
import com.flightapp.model.FlightSearchResult;

import java.time.LocalDateTime;
import java.util.List;

public class FlightApplication {
    public static void main(String[] args) {
        FlightController flightController = FlightController.getInstance();
        flightController.addCity("DEL");
        flightController.addCity("BLR");
        flightController.addCity("NYC");
        flightController.addCity("LON");
        flightController.addCity("PAR");
        flightController.addCity("MUB");


        flightController.addAirline("JetAir");
        flightController.addAirline("Indigo");
        flightController.addAirline("Delta");

        flightController.registerFlight("JetAir", "DEL", "BLR", 500, true, "Economy", true, LocalDateTime.of(2024, 8, 31, 8, 0),
                LocalDateTime.of(2024, 8, 31, 11, 0));
        flightController.registerFlight("JetAir", "DEL", "BLR", 250, true, "Economy", true, LocalDateTime.of(2024, 8, 31, 8, 0),
                LocalDateTime.of(2024, 8, 31, 11, 0));
        flightController.registerFlight("JetAir", "BLR", "LON", 1000, true, "Business", false,
                LocalDateTime.of(2024, 8, 31, 7, 0), LocalDateTime.of(2024, 8, 31, 10, 0));
        flightController.registerFlight("Delta", "DEL", "LON", 2000, true, "Economy", true, LocalDateTime.of(2024, 8, 31, 14, 0),
                LocalDateTime.of(2024, 8, 31, 23, 0));
        flightController.registerFlight("Delta", "LON", "NYC", 2000, true, "Business", false, LocalDateTime.of(2024, 8, 31, 9, 0),
                LocalDateTime.of(2024, 8, 31, 18, 0));
        flightController.registerFlight("Indigo", "LON", "NYC", 2500, true, "Economy", true, LocalDateTime.of(2024, 8, 31, 6, 0),
                LocalDateTime.of(2024, 8, 31, 12, 0));
        flightController.registerFlight("Indigo", "DEL", "BLR", 600, true, "Economy", true, LocalDateTime.of(2024, 8, 31, 13, 0),
                LocalDateTime.of(2024, 8, 31, 21, 0));
        flightController.registerFlight("Indigo", "BLR", "PAR", 800, true, "Economy", true, LocalDateTime.of(2024, 8, 31, 13, 0),
                LocalDateTime.of(2024, 8, 31, 21, 0));
        flightController.registerFlight("Indigo", "PAR", "LON", 300, true, "Economy", true, LocalDateTime.of(2024, 8, 31, 13, 0),
                LocalDateTime.of(2024, 8, 31, 21, 0));

        System.out.println("Search flights sorted by duration:");
        flightController.searchFlights("DEL", "NYC", null, true);


        System.out.println("\n Search flights filtered by meal and sorted by duration:");
        flightController.searchFlights("DEL", "NYC", List.of(Flight::isMealAvailable), true);

        flightController.searchFlights("DEL", "MUB", null, true).printResults();

    }
}