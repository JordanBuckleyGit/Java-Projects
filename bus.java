
public class bus extends customer{
    protected int numPassengers;
    protected String busType, luxuryChoice;

    bus(String name, String typeOfCar) {
        this.name = name;
        this.typeOfCar = typeOfCar;

    }

    public void busDetails() {
        gatherPassengerCount();
        determineBusType();
        gatherLuxuryChoice();
    }

    private void gatherPassengerCount() {
        System.out.println("\nHow many passengers will be on your bus trip? (Maximum 48)");
        this.numPassengers = sc.nextInt();
        if (numPassengers <= 0) {
            System.out.println("The minimum number of passengers is 1.");
            gatherPassengerCount(); // Recursively call method until a valid passenger count is entered
        } else if (numPassengers > 48) {
            System.out.println("We can't accommodate more than 48 passengers on a single bus.");
            System.out.println("Would you like to consider splitting your group or renting multiple buses?");
            // Offer alternative solutions or handle exceeding capacity
        }
    }

    private void determineBusType() {
        if (numPassengers <= 16) {
            busType = "minibus";
            System.out.println("A minibus is perfect for your group of " + numPassengers + " passengers.");
        } else {
            busType = "standard bus";
            System.out.println("A standard bus will comfortably accommodate your group of " + numPassengers + " passengers.");
        }
    }

    private void gatherLuxuryChoice() {
        System.out.println("Would you prefer a " + busType + " that is (L)uxury or (S)tandard?");
        sc.nextLine();
        this.luxuryChoice = sc.nextLine().toUpperCase();
        if (luxuryChoice.equals("L")) {
            System.out.println("Great choice! We offer luxurious " + (busType.equals("minibus") ? "mini coach" : "coach") + " options.");
        } else if (luxuryChoice.equals("S")) {
            System.out.println("Excellent! Our standard " + busType + " options are reliable and cost-effective.");
        } else {
            System.out.println("Invalid choice. Please enter L or S.");
            gatherLuxuryChoice(); // Recursively call method until a valid choice is entered
        }
    }


	
    public void calcCost() {
        double baseCost;
        double insuranceCost = 0.0;
        double luxuryCost = 0.0;
    
        // Determine base cost based on selected bus type
        if (busType.equals("minibus")) {
            baseCost = 480.0;
        } else {
            baseCost = 578.99;
        }
    
        // Apply discount for rentals longer than 10 days (if applicable)
        if (numberOfDays > 10) {
            baseCost *= 0.88; // 12% discount
        }
    
        // Add insurance cost
        if (insurance) {
            insuranceCost = 99.99 * numberOfDays;
        }
    
        // Add luxury cost (if applicable)
        if (luxuryChoice.equals("L")) {
            if (busType.equals("minibus")) {
                luxuryCost = 103.99 * numberOfDays; // Luxury mini coach
            } else {
                luxuryCost = 123.99 * numberOfDays; // Luxury coach
            }
        }
    
        // Total cost calculation
        double totalCost = baseCost * numberOfDays + insuranceCost + luxuryCost;
    
        // Print total cost
        System.out.println("Total cost of bus rental: €" + totalCost);
    }
    
}
