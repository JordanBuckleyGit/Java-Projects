/* Jordan Buckley - CC
   date - 24/4/2024 */


// Class representing a bus in the rental system
public class bus extends customer{

    /*
     * Gather details specific to a bus rental.
     */
    public void busDetails() {
        gatherPassengerCount();
        determineBusType();
        gatherLuxuryChoice();
    }

    // Method to gather passenger count input from the user
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

    // Method to determine the type of bus based on passenger count
    private void determineBusType() {
        if (numPassengers <= 16) {
            busType = "minibus";
            System.out.println("A minibus is perfect for your group of " + numPassengers + " passengers.");
        } else {
            busType = "standard bus";
            System.out.println("A standard bus will comfortably accommodate your group of " + numPassengers + " passengers.");
        }
    }

    // Method to gather luxury choice input from the user
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


    // Calculate the rental costs for a bus
    public float[] calcCost(int numberOfDays, boolean insurance) {
        float[] costs = new float[6]; // Array to hold the cost values

        float baseCost = 0.0f;
        float totalBaseCost = 0.0f;
        float insuranceCost = 0.0f;
        float luxuryCost = 0.0f;
        float discountValue = 0.0f;

        // Determine base cost based on selected bus type
        if (busType.equals("minibus")) {
            baseCost = 480.0f;
            if (luxuryChoice.equals("L")) {
                baseCost += 103.99f;
            }
        } else {
            baseCost = 578.99f;
            if (luxuryChoice.equals("L")) {
                baseCost += 123.99f;
            }
        }
        
        totalBaseCost = baseCost * numberOfDays;

        // Apply discount for rentals longer than 10 days (if applicable)
        float discount = 0.12f;
        if (numberOfDays > 10) {
            discountValue = totalBaseCost * discount;
        }

        // Add insurance cost
        if (!insurance) {
            insuranceCost = 99.99f * numberOfDays;
        }

        // Total cost calculation
        float totalCost = totalBaseCost - discountValue + insuranceCost + luxuryCost;

        // Assign calculated values to the array
        costs[0] = baseCost;
        costs[1] = insuranceCost;
        costs[2] = luxuryCost; // This seems to be missing in your original code
        costs[3] = totalCost;
        costs[4] = discountValue;
        costs[5] = totalBaseCost; // Add totalBaseCost to the array

        return costs;
    }

    // Getters for bus details
    public String getLuxuryChoice() {
        return this.luxuryChoice;
    }

    public int getPassengers() {
        return this.numPassengers;
    }

    public String getBusType() {
        return this.busType;
    }

}
