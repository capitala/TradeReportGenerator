package trade.report.generator.util;

import java.time.DayOfWeek;

public class SpecialCaseSettlementDateCalculator extends SettlementDateCalculator{

    public SpecialCaseSettlementDateCalculator() {
        super();
    }
	/**
	 * Method to setup the offDays as per the currency type 'AED'  and 'SAR'
	 */
    @Override
    protected void setupOffDays() {
    	this.dayOffList.add(DayOfWeek.FRIDAY);
    	this.dayOffList.add(DayOfWeek.SATURDAY);    	
    }
}
