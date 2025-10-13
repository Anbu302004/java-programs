import java.util.Scanner;

class Passenger {
    String name;
    int age;
    int seatNo;
    boolean isWaiting;

    Passenger(String name, int age, int seatNo, boolean isWaiting) {
        this.name = name;
        this.age = age;
        this.seatNo = seatNo;
        this.isWaiting = isWaiting;
    }
}

class Coach {
    String type;
    Passenger[] confirmed = new Passenger[60];
    Passenger[] waiting = new Passenger[10];
    int confirmedCount = 0;
    int waitingCount = 0;

    Coach(String type) {
        this.type = type;
    }

    void bookTicket(String name, int age) {
        if (confirmedCount < 60) {
            confirmed[confirmedCount++] = new Passenger(name, age, confirmedCount, false);
            System.out.println("Ticket confirmed! Seat No: " + confirmedCount);
        } else if (waitingCount < 10) {
            waiting[waitingCount++] = new Passenger(name, age, 0, true);
            System.out.println("All seats are full. Added to waiting list. Waiting No: " + waitingCount);
        } else {
            System.out.println("Sorry, all seats and waiting list are full!");
        }
    }
}

public class RailwayReservationSystems {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Coach sleeper = new Coach("Sleeper");

        while (true) {
            System.out.println("\n--- Railway Reservation System ---");
            System.out.println("1. Book Ticket");
            System.out.println("2. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.next();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sleeper.bookTicket(name, age);
                    break;
                case 2:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
