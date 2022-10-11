
/**
 * CVehicle - Primary vehicle class
 * @author Kenneth Otero
 *
 */
public class CVehicle {
	// Declare class level variables
	private int intWheels = 0;
	private int intNumOfMPG = 0;
	
	/**
	 * GetHowToDrive - How to drive vehicle
	 */
	public void GetHowToDrive() {
		System.out.println("Drives with steering wheel");
	}
	
	/**
	 * SetMPG - MPG validation
	 * @param intMPG - MPG of the vehicle
	 */
	public void SetMPG(int intMPG) {
		// Boundary check MPG
		if (intMPG < 0) {
			intMPG = 0;
		}
		
		// Set the MPG
		intNumOfMPG = intMPG;
	}
	
	/**
	 * GetMPG - Gets the MPG of the vehicle
	 * @return intNumOfMPG - MPG
	 */
	public int GetMPG() {
		return intNumOfMPG;
	}
	
	/**
	 * SetWheels - wheel validation
	 * @param intTires - tires of the vehicle
	 */
	public void SetWheels(int intTires) {
		// Boundary check
		if (intTires < 0) {
			intTires = 0;
		}
		
		// Set the # of wheels
		intWheels = intTires;
	}
	
	/**
	 * GetWheels - Gets the number of wheels
	 * @return intWheels - # of wheels
	 */
	public int GetWheels() {
		return intWheels;
	}
}
