import java.util.*;
import java.text.*;

class Vendor {
    int id;
    String name;

    Vendor(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Software {
    int id;
    String name;
    Vendor vendor;
    double cost;

    Software(int id, String name, Vendor vendor, double cost) {
        this.id = id;
        this.name = name;
        this.vendor = vendor;
        this.cost = cost;
    }
}

class Employee {
    int id;
    String name;
    List<Device> devices = new ArrayList<>();

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Device {
    int id;
    String name;
    Employee employee;
    List<Installation> installations = new ArrayList<>();

    Device(int id, String name, Employee employee) {
        this.id = id;
        this.name = name;
        this.employee = employee;
    }
}

class Installation {
    Software software;
    Date installDate;
    Date expiryDate;

    Installation(Software software, Date installDate, Date expiryDate) {
        this.software = software;
        this.installDate = installDate;
        this.expiryDate = expiryDate;
    }
}

public class AssetManagementSystem {
    static List<Vendor> vendors = new ArrayList<>();
    static List<Software> softwares = new ArrayList<>();
    static List<Employee> employees = new ArrayList<>();
    static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) throws Exception {
        setupSampleData();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter start date (dd-MM-yyyy): ");
        Date start = sdf.parse(sc.nextLine());
        System.out.println("Enter end date (dd-MM-yyyy): ");
        Date end = sdf.parse(sc.nextLine());

        System.out.println("\n--- Reports ---");
        System.out.println("1. No. of installations of a particular software");
        System.out.println("2. No. of software installed in a device");
        System.out.println("3. No. of software installed for an employee");
        System.out.println("4. Amount spent for a software");
        System.out.println("5. Amount spent for an employee");
        System.out.println("6. Amount spent on a vendor");
        System.out.println("7. No. of installations of software from a vendor");
        System.out.println("8. Devices that have an expired software");
        System.out.print("\nEnter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter software name: ");
                String sname = sc.nextLine();
                System.out.println("Installations: " + countSoftwareInstallations(sname, start, end));
                break;
            case 2:
                System.out.print("Enter device name: ");
                String dname = sc.nextLine();
                System.out.println("Installed software count: " + countSoftwareInDevice(dname, start, end));
                break;
            case 3:
                System.out.print("Enter employee name: ");
                String ename = sc.nextLine();
                System.out.println("Installed software count: " + countSoftwareForEmployee(ename, start, end));
                break;
            case 4:
                System.out.print("Enter software name: ");
                String swname = sc.nextLine();
                System.out.println("Amount spent: " + amountSpentForSoftware(swname, start, end));
                break;
            case 5:
                System.out.print("Enter employee name: ");
                String empName = sc.nextLine();
                System.out.println("Amount spent: " + amountSpentForEmployee(empName, start, end));
                break;
            case 6:
                System.out.print("Enter vendor name: ");
                String vname = sc.nextLine();
                System.out.println("Amount spent: " + amountSpentOnVendor(vname, start, end));
                break;
            case 7:
                System.out.print("Enter vendor name: ");
                String venName = sc.nextLine();
                System.out.println("Installations: " + installationsFromVendor(venName, start, end));
                break;
            case 8:
                List<Device> expired = devicesWithExpiredSoftware();
                System.out.println("Devices with expired software:");
                for (Device d : expired) {
                    System.out.println("- " + d.name + " (Employee: " + d.employee.name + ")");
                }
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    // --- Sample Data ---
    static void setupSampleData() throws Exception {
        Vendor v1 = new Vendor(1, "Microsoft");
        Vendor v2 = new Vendor(2, "Adobe");
        vendors.add(v1);
        vendors.add(v2);

        Software s1 = new Software(1, "MS Office", v1, 5000);
        Software s2 = new Software(2, "Adobe Photoshop", v2, 7000);
        softwares.add(s1);
        softwares.add(s2);

        Employee e1 = new Employee(1, "Anbu");
        Employee e2 = new Employee(2, "Ravi");

        Device d1 = new Device(1, "Laptop-A1", e1);
        Device d2 = new Device(2, "Laptop-A2", e1);
        Device d3 = new Device(3, "Laptop-R1", e2);

        d1.installations.add(new Installation(s1, sdf.parse("01-01-2024"), sdf.parse("01-01-2025")));
        d1.installations.add(new Installation(s2, sdf.parse("15-01-2024"), sdf.parse("15-01-2025")));
        d2.installations.add(new Installation(s1, sdf.parse("10-02-2024"), sdf.parse("10-02-2025")));
        d3.installations.add(new Installation(s2, sdf.parse("05-03-2024"), sdf.parse("05-03-2025")));

        e1.devices.add(d1);
        e1.devices.add(d2);
        e2.devices.add(d3);

        employees.add(e1);
        employees.add(e2);
    }

    // --- Reports ---
    static int countSoftwareInstallations(String softwareName, Date start, Date end) {
        int count = 0;
        for (Employee e : employees)
            for (Device d : e.devices)
                for (Installation i : d.installations)
                    if (i.software.name.equalsIgnoreCase(softwareName)
                            && withinRange(i.installDate, start, end))
                        count++;
        return count;
    }

    static int countSoftwareInDevice(String deviceName, Date start, Date end) {
        for (Employee e : employees)
            for (Device d : e.devices)
                if (d.name.equalsIgnoreCase(deviceName)) {
                    int count = 0;
                    for (Installation i : d.installations)
                        if (withinRange(i.installDate, start, end))
                            count++;
                    return count;
                }
        return 0;
    }

    static int countSoftwareForEmployee(String empName, Date start, Date end) {
        for (Employee e : employees)
            if (e.name.equalsIgnoreCase(empName)) {
                int count = 0;
                for (Device d : e.devices)
                    for (Installation i : d.installations)
                        if (withinRange(i.installDate, start, end))
                            count++;
                return count;
            }
        return 0;
    }

    static double amountSpentForSoftware(String softwareName, Date start, Date end) {
        double total = 0;
        for (Employee e : employees)
            for (Device d : e.devices)
                for (Installation i : d.installations)
                    if (i.software.name.equalsIgnoreCase(softwareName)
                            && withinRange(i.installDate, start, end))
                        total += i.software.cost;
        return total;
    }

    static double amountSpentForEmployee(String empName, Date start, Date end) {
        double total = 0;
        for (Employee e : employees)
            if (e.name.equalsIgnoreCase(empName))
                for (Device d : e.devices)
                    for (Installation i : d.installations)
                        if (withinRange(i.installDate, start, end))
                            total += i.software.cost;
        return total;
    }

    static double amountSpentOnVendor(String vendorName, Date start, Date end) {
        double total = 0;
        for (Employee e : employees)
            for (Device d : e.devices)
                for (Installation i : d.installations)
                    if (i.software.vendor.name.equalsIgnoreCase(vendorName)
                            && withinRange(i.installDate, start, end))
                        total += i.software.cost;
        return total;
    }

    static int installationsFromVendor(String vendorName, Date start, Date end) {
        int count = 0;
        for (Employee e : employees)
            for (Device d : e.devices)
                for (Installation i : d.installations)
                    if (i.software.vendor.name.equalsIgnoreCase(vendorName)
                            && withinRange(i.installDate, start, end))
                        count++;
        return count;
    }

    static List<Device> devicesWithExpiredSoftware() {
        List<Device> expired = new ArrayList<>();
        Date today = new Date();
        for (Employee e : employees)
            for (Device d : e.devices)
                for (Installation i : d.installations)
                    if (i.expiryDate.before(today)) {
                        expired.add(d);
                        break;
                    }
        return expired;
    }

    static boolean withinRange(Date check, Date start, Date end) {
        return !check.before(start) && !check.after(end);
    }
}
