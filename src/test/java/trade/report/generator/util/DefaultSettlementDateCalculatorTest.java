package trade.report.generator.util;



import static org.junit.Assert.assertEquals;
import static trade.report.generator.util.Constants.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import trade.report.generator.util.DefaultSettlementDateCalculator;
import trade.report.generator.util.SettlementDateCalculator;

public class DefaultSettlementDateCalculatorTest{
	private SettlementDateCalculator settlementDateCalculator;
    @Before
    public void setUp() throws Exception {
    	settlementDateCalculator = new DefaultSettlementDateCalculator();
    } @Test
    public void testFindSettlementDateForFriday() throws Exception {
        assertEquals(FRIDAY, settlementDateCalculator.findSettlementDate(FRIDAY));
    }
    @Test
    public void testFindSettlementDateForSaturday() throws Exception {
        assertEquals(MONDAY, settlementDateCalculator.findSettlementDate(SATURDAY));
    }
    @Test
    public void testFindSettlementDateForSunday() throws Exception {
        assertEquals(MONDAY, settlementDateCalculator.findSettlementDate(SUNDAY));
    }
    @Test
    public void testFindSettlementDateForMonday() throws Exception {
        assertEquals(MONDAY, settlementDateCalculator.findSettlementDate(MONDAY));
    }
}
