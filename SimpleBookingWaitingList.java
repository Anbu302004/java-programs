import java.util.*;

class Ticket {
    int seatNo;
    boolean isBooked;
    String passengerName;

    Ticket(int seatNo) {
        this.seatNo = seatNo;
        this.isBooked = false;
        this.passengerName = "";
    }
}
 
class WaitingList {
    String[] list;
    int count;

    WaitingList(int size) {
        list = new String[size];
        count = 0;
    }

    boolean add(String name) {
        if (count >= list.length) {
            return false;
        }
        list[count++] = name;
        return true;
    }

    String remove() {
        if (count == 0) return null;
        String first = list[0];
        for (int i = 0; i < count - 1; i++) {
            list[i] = list[i + 1];
        }
        list[--count] = null;
        return first;
    }

    void show() {
        if (count == 0) {
            System.out.println("No one in the waiting list.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
    }

    boolean isEmpty() {
        return count == 0;
    }

    int size() {
        return count;
    }
}

public class SimpleBookingWaitingList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ticket[] tickets = new Ticket[10];
        int nextAvailable = 0;
        WaitingList waitingList = new WaitingList(5);

        for (int i = 0; i < tickets.length; i++) {
            tickets[i] = new Ticket(i + 1);
        }

        while (true) {
            System.out.println("\n1. Book Ticket");
            System.out.println("2. Show Available Tickets");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Show Waiting List");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter your Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter number of Tickets to book: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    int bookedCount = 0;

                    for (int i = 0; i < n; i++) {
                        if (nextAvailable >= tickets.length) break;

                        Ticket t = tickets[nextAvailable];
                        if (!t.isBooked) {
                            t.isBooked = true;
                            t.passengerName = name;
                            bookedCount++;
                            System.out.println("Ticket No " + t.seatNo + " booked successfully for " + name);
                            nextAvailable++;
                        }
                    }

                    if (bookedCount < n) {
                        int toWait = n - bookedCount;
                        for (int i = 0; i < toWait; i++) {
                            if (waitingList.add(name)) {
                                System.out.println(name + " added to waiting list (Position " + waitingList.size() + ")");
                            } else {
                                System.out.println("Waiting list is full. Cannot add more.");
                                break;
                            }
                        }
                    }
                    break;

                case 2:
                    System.out.println("Available Tickets:");
                    boolean anyAvailable = false;
                    for (Ticket t : tickets) {
                        if (!t.isBooked) {
                            System.out.println("Ticket No: " + t.seatNo);
                            anyAvailable = true;
                        }
                    }
                    if (!anyAvailable) {
                        System.out.println("No tickets available.");
                    }
                    break;

                case 3:
                    System.out.print("Enter how many tickets you want to cancel: ");
                    int cancelCount = sc.nextInt();
                    for (int i = 0; i < cancelCount; i++) {
                        System.out.print("Enter Ticket Number to cancel: ");
                        int cancelTicket = sc.nextInt();

                        if (cancelTicket >= 1 && cancelTicket <= tickets.length) {
                            Ticket t = tickets[cancelTicket - 1];
                            if (t.isBooked) {
                                System.out.println("Booking for Ticket " + t.seatNo + " cancelled successfully.");
                                t.isBooked = false;
                                t.passengerName = "";
 
                                if (!waitingList.isEmpty()) {
                                    String nextPerson = waitingList.remove();
                                    t.isBooked = true;
                                    t.passengerName = nextPerson;
                                    System.out.println("Ticket " + t.seatNo + " automatically assigned to " + nextPerson);
                                }
                            } else {
                                System.out.println("Ticket " + t.seatNo + " is not booked.");
                            }
                        } else {
                            System.out.println("Invalid Ticket Number: " + cancelTicket);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Waiting List:");
                    waitingList.show();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
