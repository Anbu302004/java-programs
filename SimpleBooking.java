import java.util.*;

class Seat{
    int seatNo;
    boolean isBooked;
    String passengerName;

    Seat(int seatNo){
        this.seatNo = seatNo;
        this.isBooked = false;
        this.passengerName = "";
    }
}
public class SimpleBooking{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Seat[] seats = new Seat[10];
        for(int i=0; i<seats.length; i++){
            seats[i] = new Seat(i+1);
        }
        while(true){
            System.out.println("1. Book Seat");
            System.out.println("2. Show available Seats");
            System.out.println("3. Cancel Booking:");
            System.out.println("4. Exit");
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
                    for(Seat s : seats){
                        if(!s.isBooked){
                            s.isBooked = true;
                            s.passengerName = name;
                            bookedCount++;
                            System.out.println("Seat "+s.seatNo+" booked successfully for "+name);
                            if(bookedCount == n) break;
                        }
                    }
                if(bookedCount < n){
                    System.out.println("Only "+bookedCount+" seats were available and booked.");
                }
                break;
                case 2:
                    System.out.println("Available Seats:");
                    boolean anyAvailable = false;
                    for(Seat s : seats){
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
                System.out.print("Enter Seat Number to cancel:"); 
                int cancelSeat = sc.nextInt();
                if(cancelSeat >=1 && cancelSeat <=seats.length){
                    Seat s = seats[cancelSeat -1];
                    if(s.isBooked){
                        System.out.println("Booking for Seat "+s.seatNo+" cancelled successfully.");
                        s.isBooked = false;
                        s.passengerName = "";
                    }else{
                        System.out.println("Seat "+s.seatNo+" is not booked.");
                    }
                }else{
                    System.out.println("Invalid Seat Number.");
                }
                break;
                case 4:
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