
/**
 * CTrailer - Trailer class
 * @author Kenneth Otero
 *
 */
public class CTrailer extends CVehicle{
	// Declare variables
	int intWheels = 0;
	int intMPG = 0;
	
	/**
	 * CTrailer - default constructor
	 */
	public CTrailer() {
		Initialize(0, 0);
	}
	
	/**
	 * CTrailer -  constructor
	 * @param intTires - # of tires
	 */
	public CTrailer(int intTires) {
		Initialize(intTires, 0);
	}
	
	/**
	 * CTrailer - default constructor
	 * @param intTires - # of tires
	 * @param intMileage - MPG
	 */
	public CTrailer(int intTires, int intMileage) {
		Initialize(intTires, intMileage);
	}
	
	/**
	 * Initialize - sets the properties
	 * @param intTires - # of wheels
	 * @param intMileage - MPG
	 */
	public void Initialize(int intTires, int intMileage) {
		SetWheels(intTires);
		SetMPG(intMileage);
	}
	
	/**
	 * GetHowToDrive - 0 MPG for trailers
	 */
	public void GetHowToDrive() {
		System.out.println("Use another vehicle to pull.");
	}
	
	/**
	 * SetWheels - sets # of wheels
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
		if (intMileage != 0) {
			intMileage = 0;
		}
		
		// Assign wheels
		intMPG = intMileage;
	}
	
	/**
	 * GetMPG - Get the MPG
	 * @return intMPG - MPG
	 */
	public int GetMPG() {
		return intMPG;
	}
	
	/**
	 * GetPrice - Gets the daily rent price
	 * @return intPrice - daily rent price
	 */
	public int GetPrice() {
		// Assign daily rent price
		int intPrice = 20;
		
		// Return price
		return intPrice;
	}
}
