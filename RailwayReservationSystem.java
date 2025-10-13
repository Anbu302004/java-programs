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
            confirmed[confirmedCount] = new Passenger(name, age, confirmedCount + 1, false);
            confirmedCount++;
            System.out.println("Ticket booked successfully. Seat No: " + confirmedCount + " (" + type + ")");
        } else if (waitingCount < 10) {
            waiting[waitingCount] = new Passenger(name, age, -1, true);
            waitingCount++;
            System.out.println("Added to Waiting List. Position: " + waitingCount);
        } else {
            System.out.println("No seats or waiting available in " + type + ".");
        }
    }

    void cancelTicket(String name) {
        boolean found = false;
        for (int i = 0; i < confirmedCount; i++) {
            if (confirmed[i] != null && confirmed[i].name.equalsIgnoreCase(name)) {
                found = true;
                System.out.println("Ticket cancelled for " + name + " in " + type);
                for (int j = i; j < confirmedCount - 1; j++) {
                    confirmed[j] = confirmed[j + 1];
                    if (confirmed[j] != null)
                        confirmed[j].seatNo = j + 1;
                }
                confirmed[confirmedCount - 1] = null;
                confirmedCount--;
                if (waitingCount > 0) {
                    Passenger moveUp = waiting[0];
                    moveUp.isWaiting = false;
                    moveUp.seatNo = confirmedCount + 1;
                    confirmed[confirmedCount] = moveUp;
                    confirmedCount++;
                    for (int k = 0; k < waitingCount - 1; k++)
                        waiting[k] = waiting[k + 1];
                    waiting[waitingCount - 1] = null;
                    waitingCount--;
                    System.out.println(moveUp.name + " moved from waiting list to confirmed. Seat " + moveUp.seatNo);
                }
                break;
            }
        }

        if (!found) {
            for (int i = 0; i < waitingCount; i++) {
                if (waiting[i] != null && waiting[i].name.equalsIgnoreCase(name)) {
                    found = true;
                    System.out.println(name + " removed from waiting list (" + type + ")");
                    for (int j = i; j < waitingCount - 1; j++)
                        waiting[j] = waiting[j + 1];
                    waiting[waitingCount - 1] = null;
                    waitingCount--;
                    break;
                }
            }
        }

        if (!found)
            System.out.println("No booking found for " + name + " in " + type);
    }

    void checkAvailability() {
        System.out.println("Availability for " + type);
        System.out.println("Confirmed Seats: " + confirmedCount + "/60");
        System.out.println("Waiting List: " + waitingCount + "/10");
    }

    void prepareChart() {
        System.out.println("Chart for " + type);
        if (confirmedCount == 0 && waitingCount == 0) {
            System.out.println("No passengers.");
            return;
        }
        for (int i = 0; i < confirmedCount; i++) {
            Passenger p = confirmed[i];
            System.out.println(p.name + " (" + p.age + ") - Seat " + p.seatNo);
        }
        for (int i = 0; i < waitingCount; i++) {
            Passenger p = waiting[i];
            System.out.println(p.name + " (" + p.age + ") - WAITING");
        }
    }
}

public class RailwayReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static Coach ac = new Coach("AC");
    static Coach nonAc = new Coach("Non-AC");
    static Coach seater = new Coach("Seater");

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Railway Reservation System");
            System.out.println("1. Book Ticket");
            System.out.println("2. Check Availability");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Prepare Chart");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    bookTicket();
                    break;
                case 2:
                    checkAvailability();
                    break;
                case 3:
                    cancelTicket();
                    break;
                case 4:
                    prepareChart();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);
    }

    static void bookTicket() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();
        Coach c = chooseCoach();
        c.bookTicket(name, age);
    }

    static void checkAvailability() {
        Coach c = chooseCoach();
        c.checkAvailability();
    }

    static void cancelTicket() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        Coach c = chooseCoach();
        c.cancelTicket(name);
    }

    static void prepareChart() {
        ac.prepareChart();
        nonAc.prepareChart();
        seater.prepareChart();
    }

    static Coach chooseCoach() {
        System.out.println("Choose Coach Type:");
        System.out.println("1. AC");
        System.out.println("2. Non-AC");
        System.out.println("3. Seater");
        int ch = sc.nextInt();
        sc.nextLine();
        switch (ch) {
            case 1:
                return ac;
            case 2:
                return nonAc;
            case 3:
                return seater;
            default:
                System.out.println("Invalid option. Defaulting to Seater.");
                return seater;
        }
    }
}