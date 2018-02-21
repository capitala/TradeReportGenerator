package trade.report.generator.sample.data;

import static trade.report.generator.util.Constants.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;
import trade.report.generator.model.Instruction;
import trade.report.generator.model.Instruction.TradeType;
/**
 *  Generates the sample data for the report generation
 */
public class SampleDataProvider {

	public static Set<Instruction> getSampleInstructions() {
		return new HashSet<Instruction>(Arrays.asList(

				new Instruction("Company1", TradeType.BUY, LocalDate.of(2018, 2, 10), LocalDate.of(2018, 2, 11),
						Currency.getInstance(INR), BigDecimal.valueOf(0.50), 200, BigDecimal.valueOf(100)),

				new Instruction("Company2", TradeType.SELL, LocalDate.of(2018, 2, 10), LocalDate.of(2018, 2, 12),
						Currency.getInstance(GBP), BigDecimal.valueOf(0.60), 450, BigDecimal.valueOf(150)),

				new Instruction("Company3", TradeType.SELL, LocalDate.of(2018, 2, 9), LocalDate.of(2018, 2, 13),
						Currency.getInstance(AED), BigDecimal.valueOf(0.40), 150, BigDecimal.valueOf(400)),

				new Instruction("Company4", TradeType.SELL, LocalDate.of(2018, 2, 1), LocalDate.of(2018, 2, 1),
						Currency.getInstance(USD), BigDecimal.valueOf(0.70), 50, BigDecimal.valueOf(500)),

				new Instruction("Company5", TradeType.BUY, LocalDate.of(2018, 2, 5), LocalDate.of(2018, 2, 7),
						Currency.getInstance(GBP), BigDecimal.valueOf(0.60), 20, BigDecimal.valueOf(1000)),

				new Instruction("Company6", TradeType.BUY, LocalDate.of(2017, 2, 8), LocalDate.of(2018, 2, 9),
						Currency.getInstance(EUR), BigDecimal.valueOf(0.50), 20, BigDecimal.valueOf(500)),

				new Instruction("Company7", TradeType.SELL, LocalDate.of(2017, 2, 1), LocalDate.of(2018, 2, 10),
						Currency.getInstance(SAR), BigDecimal.valueOf(0.40), 1000, BigDecimal.valueOf(600)),

				new Instruction("Company8", TradeType.SELL, LocalDate.of(2017, 1, 31), LocalDate.of(2018, 2, 11),
						Currency.getInstance(INR), BigDecimal.valueOf(0.50), 120, BigDecimal.valueOf(800)),
				
				new Instruction("Company9", TradeType.SELL, LocalDate.of(2018, 1, 30), LocalDate.of(2018, 2, 1),
						Currency.getInstance(USD), BigDecimal.valueOf(0.60), 50, BigDecimal.valueOf(500)),
		
				new Instruction("Company10", TradeType.BUY, LocalDate.of(2018, 1, 29), LocalDate.of(2018, 2, 7),
						Currency.getInstance(GBP), BigDecimal.valueOf(0.50), 20, BigDecimal.valueOf(900)),
		
				new Instruction("Company11", TradeType.BUY, LocalDate.of(2018, 1, 27), LocalDate.of(2018, 2, 9),
						Currency.getInstance(EUR), BigDecimal.valueOf(0.40), 20, BigDecimal.valueOf(1000)),
		
				new Instruction("Company12", TradeType.SELL, LocalDate.of(2018, 1, 26), LocalDate.of(2018, 2, 10),
						Currency.getInstance(AED), BigDecimal.valueOf(0.60), 1000, BigDecimal.valueOf(1100)),
		
				new Instruction("Company13", TradeType.SELL, LocalDate.of(2018, 1, 29), LocalDate.of(2018, 2, 11),
						Currency.getInstance(INR), BigDecimal.valueOf(0.50), 120, BigDecimal.valueOf(500))));

	}
}
