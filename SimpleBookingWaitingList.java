import java.util.*;

class Ticket{
    int seatNo;
    boolean isBooked;
    String passengerName;

    Ticket(int seatNo){
        this.seatNo = seatNo;
        this.isBooked = false;
        this.passengerName = "";
    }
}

public class SimpleBookingWaitingList{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Ticket[] tickets = new Ticket[10];
        int nextAvailable = 0;
        String[] waitingList = new String[5];
        int waitingCount = 0;

        for(int i=0; i<tickets.length; i++){
            tickets[i] = new Ticket(i+1);
        }

        while(true){
            System.out.println("1. Book Ticket");
            System.out.println("2. Show available Tickets");
            System.out.println("3. Cancel Booking:");
            System.out.println("4. Show Waiting List");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.print("Enter your Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter number of Tickets to book:");
                    int n = sc.nextInt();
                    sc.nextLine();
                    int bookedCount = 0; 

                    // Book available tickets first
                    for(int i=0; i<n; i++){
                        if(nextAvailable >= tickets.length){
                            break;
                        }
                        Ticket t = tickets[nextAvailable];
                        if(!t.isBooked){
                            t.isBooked = true;
                            t.passengerName = name;
                            bookedCount++;
                            System.out.println("Ticket No "+t.seatNo+ " booked Successfully for "+name);
                            nextAvailable++;
                        }
                    }

                    // Add remaining to waiting list
                    if(bookedCount < n){
                        int toWait = n - bookedCount;
                        for(int i=0; i<toWait && waitingCount < waitingList.length; i++){
                            waitingList[waitingCount++] = name;
                            System.out.println(name+" added to waiting list (Position "+waitingCount+")");
                        }

                        if(waitingCount == waitingList.length && bookedCount + toWait > tickets.length){
                            System.out.println("Waiting list is full. Cannot add more.");
                        }
                    }

                    break;

                case 2:
                    System.out.println("Available Tickets:");
                    boolean anyAvailable = false;
                    for(Ticket t : tickets){
                        if(!t.isBooked){
                            System.out.println("Ticket No:" +t.seatNo);
                            anyAvailable = true;
                        }
                    }
                    if(!anyAvailable){
                        System.out.println("No tickets available.");
                    }else{
                        System.out.println("All tickets are booked.");
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

                                // Assign ticket to first waiting person if any
                                if(waitingCount > 0){
                                    String nextPerson = waitingList[0];
                                    t.isBooked = true;
                                    t.passengerName = nextPerson;
                                    System.out.println("Ticket " + t.seatNo + " automatically assigned to " + nextPerson);

                                    // Shift waiting list forward
                                    for(int j=0; j<waitingCount-1; j++){
                                        waitingList[j] = waitingList[j+1];
                                    }
                                    waitingList[waitingCount-1] = null;
                                    waitingCount--;
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
                    System.out.println("Waiting List");
                    if(waitingCount == 0){
                        System.out.println("No one in the waiting List.");
                    }else{
                        int pos = 1;
                        for(int i=0; i<waitingCount; i++){
                            System.out.println(pos+" . "+waitingList[i]);
                            pos++;
                        }
                    } 
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
