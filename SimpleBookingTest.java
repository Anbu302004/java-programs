import java.util.*;

class  Seats{
    int seatNo;
    boolean isBooked;
    String passengerName;

    Seats(int seatNo){
        this.seatNo = seatNo;
        this.isBooked = false;
        this.passengerName = "";
    }
}
public class SimpleBookingTest{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Seats[] seats = new Seats[10];
        int nextAvailable = 0;
        String[] waitingList = new String[5];
        int waitingCount = 0;
        for(int i=0; i<seats.length; i++){
            seats[i] = new Seats(i+1);
        }
        while(true){
            System.out.println("1. Book Seat");
            System.out.println("2. Show available Seats");
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
                    System.out.print("Enter number of Seats to book:");
                    int n = sc.nextInt();
                    int bookedCount = 0; 

                     for(int i=0; i<n; i++){
                        if(nextAvailable >= seats.length){
                            break;
                        }
                        Seats s = seats[nextAvailable];
                        if(!s.isBooked){
                            s.isBooked = true;
                            s.passengerName = name;
                            bookedCount++;
                            System.out.println("Seat No "+s.seatNo+ " booked Successfully for "+name);
                            nextAvailable++;
                        }
                     }
                if(bookedCount < n){
                    System.out.println("Only "+seats.length+" seats were available and booked.");
                }
                break;
                case 2:
                    System.out.println("Available Seats:");
                    boolean anyAvailable = false;
                    for(Seats s : seats){
                        if(!s.isBooked){
                            System.out.println("Seat No:" +s.seatNo);
                            anyAvailable = true;
                        }
                    }
                if(!anyAvailable){
                    System.out.println("No seats available.");
                }else{
                    System.out.println("All seats are booked.");
                }
                break;
                case 3:
                    System.out.print("Enter how many seats you want to cancel: ");
                    int cancelCount = sc.nextInt();
                    for (int i = 0; i < cancelCount; i++) {
                        System.out.print("Enter Seat Number to cancel: ");
                        int cancelSeat = sc.nextInt();

                        if (cancelSeat >= 1 && cancelSeat <= seats.length) {
                            Seats s = seats[cancelSeat - 1];
                            if (s.isBooked) {
                                System.out.println("Booking for Seat " + s.seatNo + " cancelled successfully.");
                                s.isBooked = false;
                                s.passengerName = "";
                            } else {
                                System.out.println("Seat " + s.seatNo + " is not booked.");
                            }
                        } else {
                            System.out.println("Invalid Seat Number: " + cancelSeat);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Waiting List");
                    if(waitingCount == 0){
                        System.out.println("No one in the waiting List.");
                    }else{
                        int pos = 1;
                        for(String pname : waitingList){
                            System.out.println(pos+" . "+pname);
                            pos++;
                        }
                    } 
                break;  
                case 5:
                    System.out.println("Exiting...");
                    sc.close(); 
                    return;
                default:
                    System.out.println("Invaild choice. Please try again.");
                    break;
                }
            }
        }
    }