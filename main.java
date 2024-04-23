
public class main {
	
	public static void main(String[] args) {
		customer customer = new customer();
        customer.printHeader();
		customer.gatherName();
		customer.selectVehicle();

		while (true) {
            if (customer.getTypeOfCar().equals("C")) {
                car car = new car(customer.getName(), customer.getSelectVehicle());
				car.gatherLicence();
				car.gatherInsurance();
				car.gatherRentalDuration();
				car.gatherVehicleType();
				car.gatherTransmission();
                car.carDetails();
                car.calcCost(car.getNumberOfDays(), car.isInsurance()); // Call calcCost() method 
				customer.headerDisplay();
				car.carDisplay();
				car.costDisplay();
                break;

            } else if (customer.getTypeOfCar().equals("B")) {
                bus bus = new bus(customer.getName(), customer.getSelectVehicle());
				bus.gatherLicence();
				bus.gatherInsurance();
				bus.gatherRentalDuration();
				bus.gatherVehicleType();
				bus.gatherTransmission();
                bus.busDetails();
                bus.calcCost();
				customer.headerDisplay();
                break;
			}
		}
	}

}
