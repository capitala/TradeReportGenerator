package trade.report.settlement.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public abstract class SettlementDateCalculator {
	// Variable to store the list of the off days as er the currency 
	protected Set<DayOfWeek> dayOffList = new HashSet<>();

	/**
	 * Method to setup the offDays as per the currency type
	 */
	protected abstract void setupOffDays();

	public SettlementDateCalculator() {
		setupOffDays();
	}

	/**
	 * Method to return the avaialable settlement date as per the date provided in
	 * the instruction for settlement
	 * 
	 * @param date
	 * @return
	 */
	public LocalDate findSettlementDate(LocalDate date) {
		final DayOfWeek inputDay = date.getDayOfWeek();

		// in case the given date is an offDay check for next available date
		if (dayOffList.contains(inputDay)) {
			return findSettlementDate(date.plusDays(1));
		} else {
			// otherwise return the date
			return date;
		}
	}
}
