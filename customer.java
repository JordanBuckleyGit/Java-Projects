import java.util.Scanner;

public class customer {
    Scanner sc = new Scanner(System.in);
    protected String name, typeOfCar, licenceNumber;
    protected boolean hasLicence, insurance;
    protected int numberOfDays, vehicleType;
    protected byte transmission;
    public void printHeader() {
        System.out.println("--Welcome To Morrison's Island Rental--\n");
    }

    public void customerDetails() {
        gatherName();
        gatherLicence();
        gatherInsurance();
        gatherRentalDuration();
        gatherVehicleType();
        gatherTransmission();
        selectVehicle();
    }

    private void gatherName() {
        System.out.print("Enter your name: ");
        this.name = sc.nextLine();
    }

    private void gatherLicence() {
        while (true) {
            System.out.print("Do you have a licence? y/n: ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("y")) {
                this.hasLicence = true;
                enterLicenceNumber();
                break;
            } else if (input.equalsIgnoreCase("n")) {
                this.hasLicence = false;
                System.out.println("You don't have a licence. You are unable to rent a vehicle.");
                // Do not proceed with rental process
                break;
            } else {
                System.out.println("Invalid input. Please enter y or n.");
            }
        }
    }

    private void enterLicenceNumber() {
        while (true) {
            System.out.print("Enter your driver's licence number (5 digits followed by 4 letters): ");
            licenceNumber = sc.nextLine();
            if (validateLicenceNumber(licenceNumber)) {
                System.out.println("Your licence number is valid.");
                // Proceed with rental process
                break;
            } else {
                System.out.println("Invalid licence number format.");
            }
        }
    }

    public void gatherInsurance() {
        while (true) {
            System.out.print("Do you have insurance? y/n: ");
            String insuranceAns = sc.nextLine();
            if (insuranceAns.equalsIgnoreCase("y")) {
                this.insurance = true;
                System.out.println("Great! You have your own insurance.\n");
                break;
            } else if (insuranceAns.equalsIgnoreCase("n")) {
                this.insurance = false;
                System.out.println("You'll need to be insured by Morrison's Island Car and Bus Rental.");
                break;
            } else {
                System.out.println("Invalid input. Please enter y or n.");
            }
        }
    }

    public void gatherRentalDuration() {
        while (true) {
            System.out.print("How many days would you like to rent the car for?: ");
            int days = sc.nextInt();
            if (days > 0 && days <= 30) {
                this.numberOfDays = days;
                System.out.println(numberOfDays + " number of days is available!\n");
                break;
            } else {
                System.out.println("Invalid amount of days.");
            }
        }
    }

    private void gatherVehicleType() {
        while (true) {
            System.out.println("What type of car would you like to rent 1,2 or 3?");
            System.out.println("1. Fuel (diesel or petrol)");
            System.out.println("2. Fully electric vehicle");
            System.out.println("3. Hybrid vehicle");
            int type = sc.nextInt();
            if (type >= 1 && type <= 3) {
                this.vehicleType = type;
                System.out.println("You've selected a " + (vehicleType == 1 ? "fuel" : vehicleType == 2 ? "fully electric" : "hybrid") + " vehicle.\n");
                break;
            } else {
                System.out.println("Invalid vehicle choice. Please enter 1, 2, or 3.");
            }
        }
        sc.nextLine(); // Consume newline character
    }

    private void gatherTransmission() {
        if (vehicleType == 1) {
            while (true) {
                System.out.print("Which type of transmission would you like 1.Manual or 2. Automatic?: ");
                byte trans = sc.nextByte();
                if (trans == 1 || trans == 2) {
                    this.transmission = trans;
                    System.out.println("Great choice!\n");
                    break;
                } else {
                    System.out.println("Invalid transmission choice. Please enter 1 or 2.");
                }
            }
            sc.nextLine(); // Consume newline character
        }
    }

    private void selectVehicle() {
        while (true) {
            System.out.print("Which type of vehicle would you like to rent? (C for car, B for bus): ");
            this.typeOfCar = sc.nextLine().toUpperCase();
            System.out.println("Selected vehicle type: " + typeOfCar); // Debug statement

            if (typeOfCar.equals("C")) {
                car car = new car();
                car.carDetails();
                car.calcCost(numberOfDays, insurance);
                break;
            } else if (typeOfCar.equals("B")) {
                bus bus = new bus();
                bus.busDetails();
                bus.calcCost();
                break;
            } else {
                System.out.println("Invalid vehicle type. Please enter C or B.");
            }
        }
    }

    private boolean validateLicenceNumber(String licenceNumber) {
        return licenceNumber.matches("\\d{5}[a-zA-Z]{4}");
    }
    
    public void headerDisplay() {
        System.out.print("\n\n\nMorrison's Island Car and Bus Rental\n14 Morrison's Island\nCork\n✉ morrisonsrental@cork.ie\n☎ (021) 123456");
        System.out.print("\n\nPlease present this docket (on a device or printed) when you arrive at Morrison's Island Car and Bus rental desk.");
        switch (this.vehicleType) {
            case 1:
              System.out.printf("\nDocket details for car rental submitted by %s.", this.name);
              break;
            case 2:
              System.out.printf("\nDocket details for bus rental submitted by %s.", this.name);
              break;
            // Add a default case to prevent unexpected output
            default:
              System.out.println("Invalid vehicle type found in docket.");
        }
        
        System.out.printf("\n\nLicense No.:\t\t%s",this.licenceNumber);
        System.out.printf("\nNumber of days:\t\t%s",this.numberOfDays);
         switch (this.vehicleType) {
            case 1:
                System.out.printf("\nFuel type:\t\tDiesel or Petrol");break;
            case 2:
                System.out.printf("\nFuel type:\t\tElectric");break;
            case 3:
                System.out.printf("\nFuel type:\t\tHybrid");
        }
        switch (this.transmission) {
            case 1:
                System.out.printf("\nTransmission:\t\tManual");
                break;
            case 2:
                System.out.printf("\nTransmission:\t\tAutomatic");
        }

    }
}
