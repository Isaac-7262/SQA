package sqa.main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ShippingVehicleTest {

	@ParameterizedTest
	@CsvSource({
	    // Valid boundaries (min, mid, max for each)
	    "0,0,0,0",         // all min
	    "0,0,100,100",     // large max
	    "0,100,0,0",       // medium mid
	    "0,200,0,0",       // medium max
	    "0,0,1,1",         // large min+1
	    "0,1,0,0",         // medium min+1
	    "1,0,0,0",         // small min+1
	    "500,0,0,0",       // small max
	    "0,200,100,100",   // medium max, large max
	    "500,200,100,100", // all max

	    // Valid mixed
	    "1,1,1,1",
	    "250,100,50,50",   // mid values, under weight
	    "499,0,0,0",       // small just under max

	    // Over weight
	    "100,100,80,-1",   // over weight
	    "500,200,100,-1",  // all max, over weight

	    // Each just over max (invalid)
	    "501,0,0,-1",
	    "0,201,0,-1",
	    "0,0,101,-1",

	    // Each negative (invalid)
	    "-1,0,0,-1",
	    "0,-1,0,-1g",
	    "0,0,-1,-1",

	    // All negative
	    "-1,-1,-1,-1",

	    // All over max
	    "501,201,101,-1",

	    // Mixed invalids
	    "501,1,1,-1",
	    "1,201,1,-1",
	    "1,1,101,-1",

	    // Edge: valid but total weight just below limit
	    "0,199,0,199"      // 0+995+0=995 < 1000
	    
	})
	void testCalculate_CategoryPartition(int smallSize, int mediumSize, int largeSize, int expectedFirst) {
	    ShippingVehicle vehicle = new ShippingVehicle();
	    List<Integer> result = vehicle.calculate(smallSize, mediumSize, largeSize);
	    assertEquals(expectedFirst, result.get(0));
	    

	}
	

	

}
