
/**
 * CMotorbike - Motorbike class
 * @author Kenneth Otero
 *
 */
public class CMotorbike extends CVehicle {
	// Declare variables
	private int intMPG;
	private int intWheels;
	
	/**
	 * CMotorbike - default constructor
	 */
	public CMotorbike() {
		Initialize(0, 0);
	}
	
	/**
	 * CMotorbike - constructor
	 * @param intTires - # of wheels
	 */
	public CMotorbike(int intTires) {
		Initialize(intTires, 0);
	}
	
	/**
	 * CMotorbike - constructor
	 * @param intTires - # of wheels
	 * @param intMileage - MPG
	 */
	public CMotorbike(int intTires, int intMileage) {
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
	 * GetHowToDrive - use handle bars to drive
	 */
	public void GetHowToDrive() {
		System.out.println("Handle bars.");
	}
	
	/**
	 * SetWheels - # of wheels
	 * @param intTires - # of wheels
	 */
	public void SetWheels(int intTires) {
		// Boundary check
		if (intTires < 0 || intTires > 2) {
			intTires = 2;
		}
		
		// Assign wheels
		intWheels = intTires;
	}
	
	/**
	 * GetWheels - input # of wheels
	 * @return intWheels - # of wheels
	 */
	public int GetWheels() {
		return intWheels;
	}
	
	/**
	 * SetMPG - sets MPG
	 * @param intMileage - MPG
	 */
	public void SetMPG(int intMileage) {
		// Boundary check
		if (intMileage < 0 || intMileage > 100) {
			intMileage = 20;
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
	 * @return intPrice - Daily rent price
	 */
	public int GetPrice() {
		// Assign daily rent price
		int intPrice = 40;
		
		// Return price
		return intPrice;
	}
}
