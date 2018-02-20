package trade.report.generator.util;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import trade.report.settlement.util.ArabSettlementDateCalculator;
import trade.report.settlement.util.SettlementDateCalculator;

public class ArabSettlementDateCalculatorTest{

	 private SettlementDateCalculator settlementDateCalculator;
	    @Before
	    public void setUp() throws Exception {
	    	settlementDateCalculator = new ArabSettlementDateCalculator();
	    } 
	    @Test
	    public void testFindSettlementDateForFriday() throws Exception {
	        final LocalDate fri = LocalDate.of(2018, 2, 16);
	        assertEquals(fri.plusDays(2), settlementDateCalculator.findSettlementDate(fri));
	    }
	    @Test
	    public void testFindSettlementDateForSunday() throws Exception {
	        final LocalDate sun = LocalDate.of(2018, 2, 18);
	        assertEquals(sun, settlementDateCalculator.findSettlementDate(sun));
	    }
	    @Test
	    public void testFindSettlementDateForSaturday() throws Exception {
	        final LocalDate sat = LocalDate.of(2018, 2, 17);
	        assertEquals(sat.plusDays(1), settlementDateCalculator.findSettlementDate(sat));
	    }
}
