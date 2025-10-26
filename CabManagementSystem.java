import java.util.*;
import java.time.*;

class Cab {
    String type;
    double costPerKm;

    Cab(String type, double costPerKm) {
        this.type = type;
        this.costPerKm = costPerKm;
    }
}

class Staff {
    String name;
    String designation;
    String address;
    Cab cab;

    Staff(String name, String designation, String address, Cab cab) {
        this.name = name;
        this.designation = designation;
        this.address = address;
        this.cab = cab;
    }
}

class Trip {
    Staff staff;
    double distance;
    LocalDate date;

    Trip(Staff staff, double distance, LocalDate date) {
        this.staff = staff;
        this.distance = distance;
        this.date = date;
    }

    double calculateCost() {
        return distance * staff.cab.costPerKm;
    }
}

class ReportGenerator {
    List<Trip> trips;

    ReportGenerator(List<Trip> trips) {
        this.trips = trips;
    }

    void totalCostPerStaff() {
        Map<String, Double> map = new HashMap<>();
        for (Trip t : trips) {
            map.put(t.staff.name, map.getOrDefault(t.staff.name, 0.0) + t.calculateCost());
        }
        System.out.println("\nTotal Cost Per Staff:");
        map.forEach((k, v) -> System.out.println(k + " : Rs" + v));
    }

    void totalCostPerMonth(int month) {
        double total = 0;
        for (Trip t : trips) {
            if (t.date.getMonthValue() == month)
                total += t.calculateCost();
        }
        System.out.println("\nTotal Cost in month " + month + " : ₹" + total);
    }

    void tripsByStaff(String name) {
        System.out.println("\nTrips by " + name + ":");
        boolean found = false;
        for (Trip t : trips) {
            if (t.staff.name.equalsIgnoreCase(name)) {
                System.out.println(t.date + " -> " + t.distance + " km, Cost ₹" + t.calculateCost());
                found = true;
            }
        }
        if (!found)
            System.out.println("No trips found for " + name);
    }
}

public class CabManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Define cab types
        Cab normal = new Cab("Normal", 10);
        Cab ac = new Cab("AC", 20);
        Cab luxury = new Cab("Luxury", 30);

        // Step 2: Create staff members
        System.out.print("Enter number of staff: ");
        int staffCount = sc.nextInt();
        sc.nextLine();

        List<Staff> staffList = new ArrayList<>();

        for (int i = 0; i < staffCount; i++) {
            System.out.println("\nEnter details for Staff " + (i + 1));
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Designation (Intern/Senior Dev/Manager): ");
            String desig = sc.nextLine();
            System.out.print("Address (A/B/C/...): ");
            String address = sc.nextLine();

            Cab assignedCab;
            if (desig.equalsIgnoreCase("Manager"))
                assignedCab = luxury;
            else if (desig.equalsIgnoreCase("Senior Dev"))
                assignedCab = ac;
            else
                assignedCab = normal;

            staffList.add(new Staff(name, desig, address, assignedCab));
        }

        // Step 3: Record trips
        List<Trip> trips = new ArrayList<>();
        System.out.print("\nEnter number of trips: ");
        int tripCount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < tripCount; i++) {
            System.out.println("\nTrip " + (i + 1));
            System.out.print("Enter staff name: ");
            String name = sc.nextLine();
            Staff staff = null;
            for (Staff s : staffList) {
                if (s.name.equalsIgnoreCase(name)) {
                    staff = s;
                    break;
                }
            }
            if (staff == null) {
                System.out.println("Staff not found! Skipping trip...");
                continue;
            }

            System.out.print("Enter distance (km): ");
            double dist = sc.nextDouble();
            sc.nextLine();
            System.out.print("Enter date (yyyy-mm-dd): ");
            String dateStr = sc.nextLine();
            LocalDate date = LocalDate.parse(dateStr);

            trips.add(new Trip(staff, dist, date));
        }

        // Step 4: Generate Reports
        ReportGenerator rg = new ReportGenerator(trips);

        while (true) {
            System.out.println("\n=== Report Menu ===");
            System.out.println("1. Total Cost Per Staff");
            System.out.println("2. Total Cost Per Month");
            System.out.println("3. Trips By Staff");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    rg.totalCostPerStaff();
                    break;
                case 2:
                    System.out.print("Enter month (1-12): ");
                    int month = sc.nextInt();
                    rg.totalCostPerMonth(month);
                    break;
                case 3:
                    System.out.print("Enter staff name: ");
                    String sname = sc.nextLine();
                    rg.tripsByStaff(sname);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
