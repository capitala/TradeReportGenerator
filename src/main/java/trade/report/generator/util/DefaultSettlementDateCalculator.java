package trade.report.generator.util;

import java.time.DayOfWeek;

public class DefaultSettlementDateCalculator extends SettlementDateCalculator{

    public DefaultSettlementDateCalculator() {
        super();
    }

    @Override
    protected void setupOffDays() {
    	this.dayOffList.add(DayOfWeek.SATURDAY);
    	this.dayOffList.add(DayOfWeek.SUNDAY);
    }
}
