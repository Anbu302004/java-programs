import java.util.Scanner;

class Flight {
    int flightID;
    String flightName;
    int totalSeats = 50;
    int bookedSeats = 0;
    int basePrice = 5000;
    Passenger[] passengers = new Passenger[50];

    public void bookTicket(int passengerID, String passengerName) {
        if (bookedSeats >= totalSeats) {
            System.out.println("Booking Failed: Flight is Full!");
            return;
        }
        int ticketPrice = basePrice + (bookedSeats * 200);
        Passenger p = new Passenger(passengerID, passengerName, ticketPrice);
        passengers[bookedSeats] = p;
        bookedSeats++;
        System.out.println("Ticket booked successfully for " + passengerName + " at price: " + ticketPrice);
    }

    public void cancelTicket(int passengerID) {
        int found = -1;
        for (int i = 0; i < bookedSeats; i++) {
            if (passengers[i].id == passengerID) {
                found = i;
                break;
            }
        }
        if (found == -1) {
            System.out.println("No booking found with Passenger ID " + passengerID);
            return;
        }

        int refund = passengers[found].cost - 200;
        System.out.println("Ticket cancelled for " + passengers[found].name + ". Refund Amount: " + refund);

        for (int i = found; i < bookedSeats - 1; i++) {
            passengers[i] = passengers[i + 1];
        }
        passengers[bookedSeats - 1] = null;
        bookedSeats--;
    }

    public void printFlightDetails() {
        System.out.println("Flight Details:");
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Booked Seats: " + bookedSeats);
        System.out.println("Available Seats: " + (totalSeats - bookedSeats));
        System.out.println("Passenger List:");
        for (int i = 0; i < bookedSeats; i++) {
            System.out.println(
                    "ID: " + passengers[i].id + ", Name: " + passengers[i].name + ", Cost: " + passengers[i].cost);
        }
    }
}

class Passenger {
    int id;
    String name;
    int cost;

    Passenger(int id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
}

public class FlightReservation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Flight flight = new Flight();

        while (true) {
            System.out.println("===== Flight Ticket Reservation System =====");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Flight Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // check integer safely
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); // clear invalid input
                continue;
            }
            int ch = sc.nextInt();
            sc.nextLine(); // clear newline

            if (ch == 1) {
                System.out.print("Enter Passenger ID: ");
                int id = sc.nextInt();
                sc.nextLine(); // consume newline

                System.out.print("Enter Passenger Name: ");
                String name = sc.nextLine(); // ✅ allows full name with spaces

                flight.bookTicket(id, name);

            } else if (ch == 2) {
                System.out.print("Enter Passenger ID to Cancel: ");
                int id = sc.nextInt();
                sc.nextLine(); // consume newline
                flight.cancelTicket(id);

            } else if (ch == 3) {
                flight.printFlightDetails();

            } else if (ch == 4) {
                System.out.println("Exiting System...");
                break;

            } else {
                System.out.println("Invalid Choice!");
            }
        }
        sc.close();
    }
}
