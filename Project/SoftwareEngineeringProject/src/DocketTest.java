import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

class DocketTest {
// --- Area Number ---
	
	// TC01
	// Test Objective: Verify area is accepted when exactly 1
	// Input(s): Area = 1
	// Expected Output(s): Area accepted
	@Test
	public void area_minBoundary_valid() {
		assertTrue(Docket.isValidArea(1));
	}
	 
	// TC02
	// Test Objective: Verify area is accepted when exactly 13
	// Input(s): Area = 13
	// Expected Output(s): Area accepted	
	@Test
	public void area_maxBoundary_valid() {
		assertTrue(Docket.isValidArea(13));
	}
	 
	// TC03
	// Test Objective: Verify area is accepted when between 1 and 13
	// Input(s): Area = 7
	// Expected Output(s): Area accepted
	@Test
	public void area_middle_valid() {
		assertTrue(Docket.isValidArea(7));
	}
	
	// TC04
	// Test Objective: Verify area is rejected when less than 1
	// Input(s): Area = 0.99
	// Expected Output(s): Error message / Area rejected
	@Test
	public void area_belowMin_invalid() {
		assertFalse(Docket.isValidArea(0.99));
	}
	 
	// TC05
	// Test Objective: Verify area is rejected when more than 13
	// Input(s): Area = 13.01
	// Expected Output(s): Error message / Area rejected
	@Test
	public void area_aboveMax_invalid() {
		assertFalse(Docket.isValidArea(13.01));
	}
	
	// TC06
	// Test Objective: Verify area is rejected when 0
	// Input(s): Area = 0
	// Expected Output(s): Error message / Area rejected
	@Test
	public void area_zero_invalid() {
		assertFalse(Docket.isValidArea(0));
	}
	 
	// TC07
	// Test Objective: Verify area is rejected when negative
	// Input(s): Area = -5
	// Expected Output(s): Error message / Area rejected
	@Test
	public void area_negative_invalid() {
		assertFalse(Docket.isValidArea(-5));
	}
// ---------- Customer number (exactly 16 characters) ----------
	 
	// TC08
	// Test Objective: Verify customer number is accepted when exactly 16 characters
	// Input(s): Customer Number = "1234567890123456"
	// Expected Output(s): Customer number accepted
	@Test
	public void customerNum_16chars_valid() {
		assertTrue(Docket.isValidCustomerNum("1234567890123456"));
	}
	 
	// TC09
	// Test Objective: Verify customer number is rejected when less than 16 characters
	// Input(s): Customer Number = "123456789012345"
	// Expected Output(s): Error message / Customer number rejected
	@Test
	public void customerNum_15chars_invalid() {
		assertFalse(Docket.isValidCustomerNum("123456789012345"));
	}
	 
	// TC10
	// Test Objective: Verify customer number is rejected when more than 16 characters
	// Input(s): Customer Number = "12345678901234567"
	// Expected Output(s): Error message / Customer number rejected
	@Test
	public void customerNum_17chars_invalid() {
		assertFalse(Docket.isValidCustomerNum("12345678901234567"));
	}
	 
	// TC11
	// Test Objective: Verify customer number is rejected when empty
	// Input(s): Customer Number = ""
	// Expected Output(s): Error message / Customer number rejected
	@Test
	public void customerNum_empty_invalid() {
		assertFalse(Docket.isValidCustomerNum(""));
	}
	 
	// TC12
	// Test Objective: Verify customer number is rejected when null
	// Input(s): Customer Number = null
	// Expected Output(s): Error message / Customer number rejected
	@Test
	public void customerNum_null_invalid() {
		assertFalse(Docket.isValidCustomerNum(null));
	}
	 
	// ---------- Docket number (exactly 16 characters) ----------
	 
	// TC13
	// Test Objective: Verify docket number is accepted when exactly 16 characters
	// Input(s): Docket Number = "ABCD123456789012"
	// Expected Output(s): Docket number accepted
	@Test
	public void docketNum_16chars_valid() {
		assertTrue(Docket.isValidDocketNum("ABCD123456789012"));
	}
	 
	// TC14
	// Test Objective: Verify docket number is rejected when less than 16 characters
	// Input(s): Docket Number = "ABCD12345678901"
	// Expected Output(s): Error message / Docket number rejected
	@Test
	public void docketNum_15chars_invalid() {
		assertFalse(Docket.isValidDocketNum("ABCD12345678901"));
	}
	 
	// TC15
	// Test Objective: Verify docket number is rejected when more than 16 characters
	// Input(s): Docket Number = "ABCD1234567890123"
	// Expected Output(s): Error message / Docket number rejected
	@Test
	public void docketNum_17chars_invalid() {
		assertFalse(Docket.isValidDocketNum("ABCD1234567890123"));
	}
	 
	// TC16
	// Test Objective: Verify docket number is rejected when empty
	// Input(s): Docket Number = ""
	// Expected Output(s): Error message / Docket number rejected
	@Test
	public void docketNum_empty_invalid() {
		assertFalse(Docket.isValidDocketNum(""));
	}
	 
	// TC17
	// Test Objective: Verify docket number is rejected when null
	// Input(s): Docket Number = null
	// Expected Output(s): Error message / Docket number rejected
	@Test
	public void docketNum_null_invalid() {
		assertFalse(Docket.isValidDocketNum(null));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
