/* Jordan Buckley - CC
   date - 24/4/2024 */

// Class representing a car in the rental system
public class car extends customer {

    /*
     * Gather details specific to a car rental.
     */
    public void carDetails() {
        gatherEngineSize();
        gatherCarStyle();
        gatherCarChoice();
    }

    // Method to gather engine size input from the user
    private void gatherEngineSize() {
        System.out.print("\nWhat size engine do you require in your car rental? (Please enter a value between 1.1 liters and 1.6 liters): ");
        this.engineSize = sc.nextFloat();
        sc.nextLine(); // Consume newline character
        if (engineSize >= 1.1 && engineSize < 1.7) {
            System.out.println("Great! We have cars with an engine size of " + engineSize + " liters available.");
        } else {
            System.out.println("Not a valid engine size, please re-enter one between 1.1 litres and 1.6 litres: ");
            gatherEngineSize(); // Recursively call method until a valid engine size is entered
        }
    }

    // Method to gather car style preference input from the user
    private void gatherCarStyle() {
        System.out.print("\nWhat type of car body style would you prefer? (H for hatchback, S for saloon): ");
        this.carPref = sc.nextLine().toUpperCase();
        if (carPref.equals("H")) {
            System.out.println("Great choice! Hatchbacks offer practicality and maneuverability.");
        } else if (carPref.equals("S")) {
            System.out.println("Excellent! Saloons offer comfort and a spacious interior.");
        } else {
            System.out.println("Invalid car style. Please enter H or S.");
            gatherCarStyle(); // Recursively call method until a valid car style is entered
        }
    }

    // Method to gather car choice input from the user
    private void gatherCarChoice() {
        System.out.print("\nWhich car would you prefer to rent? (F for family car or P for people carrier): ");
        this.carChoice = sc.nextLine().toUpperCase();
        if (carChoice.equals("F")) {
            System.out.println("Excellent choice for families of all kinds!");
        } else if (carChoice.equals("P")) {
            System.out.println("Amazing! People Carriers offer comfort and great space.");
        } else {
            System.out.println("Invalid option. Please enter F or P.");
            gatherCarChoice(); // Recursively call method until a valid car choice is entered
        }
    }
    
    /*
     * Calculate the rental costs for a car.
     * @return Array containing the calculated costs
     */
    public float[] calcCost(int numberOfDays, boolean insurance) {
        float[] costs = new float[5]; // Array to hold the cost values
    	float costOfRentalDaily = 0.0f; // Initialize the daily rental cost
        float totalCostOfRental = 0.0f; // Initialize the total rental cost
        float insuranceValue = 0.0f; // Initialize the insurance cost
        float discountValue = 0.0f; // Initialize the discount value

        // Engine Size rules
        if (engineSize > 1.3) {
            costOfRentalDaily += 45.67;
        } else if (engineSize > 1.0) {
            costOfRentalDaily += 30.56;
        }
        // Car Type rules
        if (carChoice.equals("P")) { // Assuming vehicleType represents car type
            costOfRentalDaily += 2.99;
        }

        // Total Cost of Rental rule
        totalCostOfRental = costOfRentalDaily * numberOfDays;
        
        // Insurance rule
        if (!insurance) {
            insuranceValue = 15.72f * numberOfDays;
        }

        // Discount rule
        float discount = 0.0f; // Initialize the discount rate
        if (numberOfDays > 10) {
            discount = 0.10f;
            discountValue = totalCostOfRental * discount;
        }

        float totalCost = totalCostOfRental - discountValue + insuranceValue;
        
        costs[0] = costOfRentalDaily;
        costs[1] = totalCostOfRental;
        costs[2] = discountValue;
        costs[3] = insuranceValue;
        costs[4] = totalCost;
        return costs;
    }
    
    // Getters for car details
    public float getEngineSize() {
        return this.engineSize;
    }

    public String getCarPref() {
        return this.carPref;
    }

    public String getCarChoice() {
        return this.carChoice;
    }

}
