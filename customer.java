import java.util.Scanner;

//Class representing the main customer functionality
public class customer {
 Scanner sc = new Scanner(System.in);

 // Customer details
 protected String name, typeOfCar, licenceNumber, carPref, carChoice, busType, luxuryChoice;

 // Flags and counters
 protected boolean hasLicence, insurance;
 protected int numberOfDays, vehicleType, numPassengers;
 protected byte transmission;

 // Cost variables
 protected float costOfRentalDaily, totalCostOfRental, discountValue, insuranceValue, totalCost, engineSize, insuranceCost, baseCost, totalBaseCost;

 /*
  * Print the welcome header for the rental system.
  */
 public void printHeader() {
     System.out.println("--Welcome To Morrison's Island Rental--\n");
 }

 /*
  * Gather customer details and proceed with the rental process.
  */
 public void customerDetails() {
     gatherName();
     gatherLicence();
     gatherInsurance();
     gatherRentalDuration();
     gatherVehicleType();
     gatherTransmission();
     selectVehicle();
     headerDisplay();
     if (typeOfCar.equals("C")) {
         carDisplay(engineSize, carPref, carChoice);
     } else if (typeOfCar.equals("B")) {
         busDisplay(numPassengers, busType, luxuryChoice);
     }
     displayCost();
 }

 // Method to gather customer name
 private void gatherName() {
     System.out.print("Enter your name: ");
     this.name = sc.nextLine();
 }

 // Method to gather information about whether the customer has a driver's license
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
             gatherLicence();
         } else {
             System.out.println("Invalid input. Please enter y or n.");
         }
     }
 }

 // Method to gather and validate the driver's license number
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

 // Method to gather information about insurance
 private void gatherInsurance() {
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

 // Method to gather information about the rental duration
 private void gatherRentalDuration() {
     while (true) {
         System.out.print("How many days would you like to rent the vehicle for?: ");
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

 // Method to gather information about the vehicle type
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

 // Method to gather information about the transmission type
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

 // Method to select the vehicle type
 private void selectVehicle() {
     while (true) {
         System.out.print("Which type of vehicle would you like to rent? (C for car, B for bus): ");
         this.typeOfCar = sc.nextLine().toUpperCase();
         System.out.println("Selected vehicle type: " + typeOfCar); // Debug statement

         if (typeOfCar.equals("C")) {
             car car = new car();
             car.carDetails();
             this.engineSize = car.getEngineSize();
             this.carPref = car.getCarPref(); // Set car preferences
             this.carChoice = car.getCarChoice(); // Set car choice
             float[] costs = car.calcCost(numberOfDays, insurance); // Retrieve the cost values
             this.costOfRentalDaily = costs[0];
             this.totalCostOfRental = costs[1];
             this.discountValue = costs[2];
             this.insuranceValue = costs[3];
             this.totalCost = costs[4];
             break;
         } else if (typeOfCar.equals("B")) {
             bus bus = new bus();
             bus.busDetails();
             this.busType = bus.getBusType();
             this.luxuryChoice = bus.getLuxuryChoice();
             this.numPassengers = bus.getPassengers();
             float[] costs = bus.calcCost(numberOfDays, insurance); // Capture costs
             this.baseCost = costs[0];
             this.insuranceCost = costs[1];
             this.totalBaseCost = costs[5];
             this.totalCost = costs[3];
             this.discountValue = costs[4]; // Discounts are at index 4
             break;
         } else {
             System.out.println("Invalid choice. Please enter C or B.");
         }
     }
 }

 // Method to validate the format of the license number
 private boolean validateLicenceNumber(String licenceNumber) {
     return licenceNumber.matches("\\d{5}[a-zA-Z]{4}");
 }

 /*
  * Display the header with customer details.
  */
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

     System.out.printf("\n\nLicense No.:\t\t%s", this.licenceNumber);
     System.out.printf("\nNumber of days:\t\t%s", this.numberOfDays);
     switch (this.vehicleType) {
         case 1:
             System.out.printf("\nFuel type:\t\tDiesel or Petrol");
             break;
         case 2:
             System.out.printf("\nFuel type:\t\tElectric");
             break;
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

 /*
  * Display car details.
  */
 private void carDisplay(float engineSize, String carPref, String carChoice) {
     System.out.printf("\nEngine Size:\t\t%.1f\n", engineSize);
     System.out.print("Boot:\t\t\t");
     if (carPref.equals("H")) {
         System.out.println("Hatchback");
     } else if (carPref.equals("S")) {
         System.out.println("Saloon");
     }

     System.out.print("Car size:\t\t");
     if (carChoice.equals("F")) {
         System.out.println("Family car");
     } else if (carChoice.equals("P")) {
         System.out.println("People carrier");
     }
 }

 /*
  * Display bus details.
  */
 private void busDisplay(int numPassengers, String busType, String luxuryChoice) {
     System.out.printf("\nNumber of Passengers: %d\n", numPassengers);
     System.out.printf("Bus Type: %s\n", busType);
     System.out.printf("Luxury Choice: %s\n", luxuryChoice);
 }

 /*
  * Display the cost breakdown.
  */
 private void displayCost() {
     System.out.println("\n\nCost Breakdown:");

     if (typeOfCar.equals("C")) {
         System.out.printf("%d days at €%,.2f/daily\n", numberOfDays, costOfRentalDaily);
         System.out.printf("Total Cost of Rental:\t€%,.2f\n", totalCostOfRental);
     } else if (typeOfCar.equals("B")) {
         System.out.printf("%d days at €%,.2f/daily\n", numberOfDays, baseCost);
         System.out.printf("Total Cost of Rental:\t€%,.2f\n", totalBaseCost);

     }
     System.out.printf("Discount:\t\t€%,.2f\n", discountValue);

     System.out.print("Insurance:\t\t");
     if (!insurance) {
         if (typeOfCar.equals("C")) {
             System.out.printf("€%,.2f\n", insuranceValue);
         } else if (typeOfCar.equals("B")) {
             System.out.printf("€%,.2f\n", insuranceCost);
         }
     } else {
         System.out.println("Customer has their own insurance");
     }

     System.out.printf("Total Cost:\t\t€%,.2f\n", totalCost);
 }
}
