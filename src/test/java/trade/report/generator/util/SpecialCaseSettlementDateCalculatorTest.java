package trade.report.generator.util;

import static org.junit.Assert.assertEquals;
import static trade.report.generator.util.Constants.*;

import org.junit.Before;
import org.junit.Test;

import trade.report.generator.util.SettlementDateCalculator;
import trade.report.generator.util.SpecialCaseSettlementDateCalculator;

public class SpecialCaseSettlementDateCalculatorTest {

	private SettlementDateCalculator settlementDateCalculator;

	@Before
	public void setUp() throws Exception {
		settlementDateCalculator = new SpecialCaseSettlementDateCalculator();
	}
	
	@Test
	public void testFindSettlementDateForFriday() throws Exception {
		assertEquals(SUNDAY, settlementDateCalculator.findSettlementDate(FRIDAY));
	}
	@Test
	public void testFindSettlementDateForSaturday() throws Exception {
		assertEquals(SUNDAY, settlementDateCalculator.findSettlementDate(SATURDAY));
	}
	@Test
	public void testFindSettlementDateForSunday() throws Exception {
		assertEquals(SUNDAY, settlementDateCalculator.findSettlementDate(SUNDAY));
	}
	@Test
	public void testFindSettlementDateForMonday() throws Exception {
		assertEquals(MONDAY, settlementDateCalculator.findSettlementDate(MONDAY));
	}


}
