package trade.report.generator.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;
import trade.report.generator.model.Instruction;
import trade.report.generator.model.Instruction.TradeType;


public class SampleDataProvider {

	public static Set<Instruction> getSampleInstructions() {
		return new HashSet<Instruction>(Arrays.asList(

				new Instruction("Company1", TradeType.BUY, LocalDate.of(2018, 2, 10), LocalDate.of(2018, 2, 11),
						Currency.getInstance("INR"), BigDecimal.valueOf(0.50), 200, BigDecimal.valueOf(100.25)),

				new Instruction("Company2", TradeType.SELL, LocalDate.of(2018, 2, 10), LocalDate.of(2018, 2, 12),
						Currency.getInstance("GBP"), BigDecimal.valueOf(0.22), 450, BigDecimal.valueOf(150.5)),

				new Instruction("Company3", TradeType.SELL, LocalDate.of(2018, 2, 9), LocalDate.of(2018, 2, 13),
						Currency.getInstance("AED"), BigDecimal.valueOf(0.27), 150, BigDecimal.valueOf(400.8)),

				new Instruction("Company4", TradeType.SELL, LocalDate.of(2018, 2, 1), LocalDate.of(2018, 2, 1),
						Currency.getInstance("SGD"), BigDecimal.valueOf(0.34), 50, BigDecimal.valueOf(500.6)),

				new Instruction("Company5", TradeType.BUY, LocalDate.of(2018, 2, 5), LocalDate.of(2018, 2, 7),
						Currency.getInstance("GBP"), BigDecimal.valueOf(0.34), 20, BigDecimal.valueOf(40.6)),

				new Instruction("Company6", TradeType.BUY, LocalDate.of(2017, 2, 8), LocalDate.of(2018, 2, 9),
						Currency.getInstance("EUR"), BigDecimal.valueOf(0.34), 20, BigDecimal.valueOf(40.6)),

				new Instruction("Company7", TradeType.SELL, LocalDate.of(2017, 2, 1), LocalDate.of(2018, 2, 10),
						Currency.getInstance("SAR"), BigDecimal.valueOf(0.34), 1000, BigDecimal.valueOf(160.6)),

				new Instruction("Company8", TradeType.SELL, LocalDate.of(2017, 1, 31), LocalDate.of(2018, 2, 11),
						Currency.getInstance("INR"), BigDecimal.valueOf(0.34), 120, BigDecimal.valueOf(500.6)),
				
				new Instruction("Company9", TradeType.SELL, LocalDate.of(2018, 1, 30), LocalDate.of(2018, 2, 1),
						Currency.getInstance("SGD"), BigDecimal.valueOf(0.34), 50, BigDecimal.valueOf(500.6)),
		
				new Instruction("Company10", TradeType.BUY, LocalDate.of(2018, 1, 29), LocalDate.of(2018, 2, 7),
						Currency.getInstance("GBP"), BigDecimal.valueOf(0.34), 20, BigDecimal.valueOf(40.6)),
		
				new Instruction("Company11", TradeType.BUY, LocalDate.of(2018, 1, 27), LocalDate.of(2018, 2, 9),
						Currency.getInstance("EUR"), BigDecimal.valueOf(0.34), 20, BigDecimal.valueOf(40.6)),
		
				new Instruction("Company12", TradeType.SELL, LocalDate.of(2018, 1, 26), LocalDate.of(2018, 2, 10),
						Currency.getInstance("SAR"), BigDecimal.valueOf(0.34), 1000, BigDecimal.valueOf(160.6)),
		
				new Instruction("Company13", TradeType.SELL, LocalDate.of(2018, 1, 29), LocalDate.of(2018, 2, 11),
						Currency.getInstance("INR"), BigDecimal.valueOf(0.34), 120, BigDecimal.valueOf(500.6))));

	}
}
