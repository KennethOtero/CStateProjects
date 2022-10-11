
/**
 * CCar - Car class
 * @author Kenneth Otero
 *
 */
public class CCar extends CVehicle {
	// Declare variables
	private int intMPG = 0;
	private int intWheels = 0;
	
	/**
	 * CCar - default constructor
	 */
	public CCar() {
		Initialize(0, 0);
	}
	
	/**
	 * CCar - constructor
	 * @param intTires - # of wheels
	 */
	public CCar(int intTires) {
		Initialize(intTires, 0);
	}
	
	/**
	 * CCar - constructor
	 * @param intTires - # of wheels
	 * @param intMileage - MPG
	 */
	public CCar(int intTires, int intMileage) {
		Initialize(intTires, intMileage);
	}
	
	/**
	 * Initialize - sets properties of the car
	 * @param intTires - # of wheels
	 * @param intMileage - MPG
	 */
	public void Initialize(int intTires, int intMileage) {
		SetMPG(intMileage);
		SetWheels(intTires);
	}
	
	/**
	 * GetHowToDrive - use steering wheel to drive
	 */
	public void GetHowToDrive() {
		System.out.println("Steering wheel.");
	}
	
	/**
	 * SetWheels - Sets the wheels of the car
	 * @param intTires - # of wheels
	 */
	public void SetWheels(int intTires) {
		// Boundary check
		if (intTires < 0 || intTires > 4) {
			intTires = 4;
		}
		
		// Assign wheels
		intWheels = intTires;
	}
	
	/**
	 * GetWheels - gets the # of wheels
	 */
	public int GetWheels() {
		return intWheels;
	}
	
	/**
	 * SetMPG - Sets the car's MPG
	 * @param intMileage - MPG
	 */
	public void SetMPG(int intMileage) {
		// Boundary check
		if (intMileage < 0 || intMileage > 100) {
			intMileage = 40;
		}
		
		// Assign MPG
		intMPG = intMileage;
	}
	
	/**
	 * GetMPG - Get the MPG
	 */
	public int GetMPG() {
		return intMPG;
	}
	
	/**
	 * GetPrice - Gets the daily rent price
	 * @return intPrice
	 */
	public int GetPrice() {
		// Assign daily rent price
		int intPrice = 50;
		
		// Return price
		return intPrice;
	}

}
