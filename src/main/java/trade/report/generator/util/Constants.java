package trade.report.generator.util;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Constants {
	public static final LocalDate TUESDAY = LocalDate.of(2018, 2, 13);
	public static final LocalDate WEDNESDAY = LocalDate.of(2018, 2, 14);
	public static final LocalDate THURSDAY = LocalDate.of(2018, 2, 15);
	public static final LocalDate FRIDAY = LocalDate.of(2018, 2, 16);
	public static final LocalDate SATURDAY = LocalDate.of(2018, 2, 17);
	public static final LocalDate SUNDAY = LocalDate.of(2018, 2, 18);
	public static final LocalDate MONDAY = LocalDate.of(2018, 2, 19);
	
	public static final String USD = "USD";
	public static final String SAR = "SAR";
	public static final String INR = "INR";
	public static final String AED = "AED";
	public static final String GBP = "GBP";
	public static final String EUR = "EUR";
	
	public static final String SAMPLE_COMPANY = "Company1";	
	public static final BigDecimal SAMPLE_AGREEDFX = new BigDecimal(0.20);	
	public static final BigDecimal SAMPLE_PRICEPERUNIT = BigDecimal.ONE;	
	public static final int SAMPLE_UNITS = 100;	
}
