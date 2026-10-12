import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
 
public class BillTest {
 
	// TC01
	// Test Objective: Verify area is accepted when exactly 1
	// Input(s): Area = 1
	// Expected Output(s): Area accepted
	@Test
	public void area_minBoundary_valid() {
		assertTrue(Bill.isValidArea(1));
	}
 
	// TC02
	// Test Objective: Verify area is accepted when exactly 13
	// Input(s): Area = 13
	// Expected Output(s): Area accepted	
	@Test
	public void area_maxBoundary_valid() {
		assertTrue(Bill.isValidArea(13));
	}
 
	// TC03
	// Test Objective: Verify area is accepted when between 1 and 13
	// Input(s): Area = 7
	// Expected Output(s): Area accepted
	@Test
	public void area_middle_valid() {
		assertTrue(Bill.isValidArea(7));
	}
 
	// TC04
	// Test Objective: Verify area is rejected when less than 1
	// Input(s): Area = 0.99
	// Expected Output(s): Error message / Area rejected
	@Test
	public void area_belowMin_invalid() {
		assertFalse(Bill.isValidArea(0.99));
	}
 
	// TC05
	// Test Objective: Verify area is rejected when more than 13
	// Input(s): Area = 13.01
	// Expected Output(s): Error message / Area rejected
	@Test
	public void area_aboveMax_invalid() {
		assertFalse(Bill.isValidArea(13.01));
	}
 
	// TC17
	// Test Objective: Verify area is rejected when 0
	// Input(s): Area = 0
	// Expected Output(s): Error message / Area rejected
	@Test
	public void area_zero_invalid() {
		assertFalse(Bill.isValidArea(0));
	}
 
	// TC18
	// Test Objective: Verify area is rejected when negative
	// Input(s): Area = -5
	// Expected Output(s): Error message / Area rejected
	@Test
	public void area_negative_invalid() {
		assertFalse(Bill.isValidArea(-5));
	}
 
	// ---------- Customer number (exactly 16 characters) ----------
 
	// TC06
	// Test Objective: Verify customer number is accepted when exactly 16 characters
	// Input(s): Customer Number = "1234567890123456"
	// Expected Output(s): Customer number accepted
	@Test
	public void customerNum_16chars_valid() {
		assertTrue(Bill.isValidCustomerNum("1234567890123456"));
	}
 
	// TC07
	// Test Objective: Verify customer number is rejected when less than 16 characters
	// Input(s): Customer Number = "123456789012345"
	// Expected Output(s): Error message / Customer number rejected
	@Test
	public void customerNum_15chars_invalid() {
		assertFalse(Bill.isValidCustomerNum("123456789012345"));
	}
 
	// TC08
	// Test Objective: Verify customer number is rejected when more than 16 characters
	// Input(s): Customer Number = "12345678901234567"
	// Expected Output(s): Error message / Customer number rejected
	@Test
	public void customerNum_17chars_invalid() {
		assertFalse(Bill.isValidCustomerNum("12345678901234567"));
	}
 
	// TC19
	// Test Objective: Verify customer number is rejected when empty
	// Input(s): Customer Number = ""
	// Expected Output(s): Error message / Customer number rejected
	@Test
	public void customerNum_empty_invalid() {
		assertFalse(Bill.isValidCustomerNum(""));
	}
 
	// TC20
	// Test Objective: Verify customer number is rejected when null
	// Input(s): Customer Number = null
	// Expected Output(s): Error message / Customer number rejected
	@Test
	public void customerNum_null_invalid() {
		assertFalse(Bill.isValidCustomerNum(null));
	}
 
	// ---------- Docket number (exactly 16 characters) ----------
 
	// TC09
	// Test Objective: Verify docket number is accepted when exactly 16 characters
	// Input(s): Docket Number = "ABCD123456789012"
	// Expected Output(s): Docket number accepted
	@Test
	public void docketNum_16chars_valid() {
		assertTrue(Bill.isValidDocketNum("ABCD123456789012"));
	}
 
	// TC10
	// Test Objective: Verify docket number is rejected when less than 16 characters
	// Input(s): Docket Number = "ABCD12345678901"
	// Expected Output(s): Error message / Docket number rejected
	@Test
	public void docketNum_15chars_invalid() {
		assertFalse(Bill.isValidDocketNum("ABCD12345678901"));
	}
 
	// TC11
	// Test Objective: Verify docket number is rejected when more than 16 characters
	// Input(s): Docket Number = "ABCD1234567890123"
	// Expected Output(s): Error message / Docket number rejected
	@Test
	public void docketNum_17chars_invalid() {
		assertFalse(Bill.isValidDocketNum("ABCD1234567890123"));
	}
 
	// TC21
	// Test Objective: Verify docket number is rejected when empty
	// Input(s): Docket Number = ""
	// Expected Output(s): Error message / Docket number rejected
	@Test
	public void docketNum_empty_invalid() {
		assertFalse(Bill.isValidDocketNum(""));
	}
 
	// TC22
	// Test Objective: Verify docket number is rejected when null
	// Input(s): Docket Number = null
	// Expected Output(s): Error message / Docket number rejected
	@Test
	public void docketNum_null_invalid() {
		assertFalse(Bill.isValidDocketNum(null));
	}
 
}
 