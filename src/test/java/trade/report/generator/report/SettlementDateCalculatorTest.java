package trade.report.generator.report;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import org.junit.Test;

import trade.report.generator.model.Instruction;
import trade.report.generator.model.Instruction.TradeType;
import trade.report.settlement.calculator.InstructionSettlementDateCalculator;

public class SettlementDateCalculatorTest {

	@Test
	public void calculateDefaultSettlementDateFriday() throws Exception {
		final LocalDate requestedSettlementDate = LocalDate.of(2018, 2, 16);
		final Instruction instruction = new Instruction("Company1", TradeType.BUY, LocalDate.of(2018, 2, 16),
				requestedSettlementDate, Currency.getInstance("USD"), BigDecimal.valueOf(0.1), 100,
				BigDecimal.valueOf(1));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(requestedSettlementDate, instruction.getSettlementDate());
	}
	
	@Test
	public void calculateAEDSARSettlementDateFriday() throws Exception {
		final LocalDate requestedSettlementDate = LocalDate.of(2018, 2, 16); 
		final Instruction instruction = new Instruction("Company1", TradeType.BUY, LocalDate.of(2018, 2, 16),
				requestedSettlementDate, Currency.getInstance("AED"), 
				BigDecimal.valueOf(0.1), 100, BigDecimal.valueOf(1));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(requestedSettlementDate.plusDays(2), instruction.getSettlementDate());
	}
	
	@Test
	public void calculateDefaultSettlementDateSaturday() throws Exception {
		final LocalDate requestedSettlementDate = LocalDate.of(2018, 2, 17);
		final Instruction instruction = new Instruction("Company1", TradeType.BUY, LocalDate.of(2018, 2, 17),
				requestedSettlementDate, Currency.getInstance("USD"), BigDecimal.valueOf(0.1), 100,
				BigDecimal.valueOf(2));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(requestedSettlementDate.plusDays(2), instruction.getSettlementDate());
	}
	
	@Test
	public void calculateAEDSARSettlementDateSaturday() throws Exception {
		final LocalDate requestedSettlementDate = LocalDate.of(2018, 2, 17); 
		final Instruction instruction = new Instruction("Company1", TradeType.BUY, LocalDate.of(2018, 2, 17),
				requestedSettlementDate, Currency.getInstance("AED"), 
				BigDecimal.valueOf(0.1), 100, BigDecimal.valueOf(1));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(requestedSettlementDate.plusDays(1), instruction.getSettlementDate());
	}

	@Test
	public void calculateDefaultSettlementDateSunday() throws Exception {
		final LocalDate requestedSettlementDate = LocalDate.of(2018, 2, 18); // Sunday
		final Instruction instruction = new Instruction("Company1", TradeType.BUY, LocalDate.of(2018, 2, 18),
				requestedSettlementDate, Currency.getInstance("USD"), BigDecimal.valueOf(0.1), 100,
				BigDecimal.valueOf(1));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(requestedSettlementDate.plusDays(1), instruction.getSettlementDate());
	}



	@Test
	public void calculateAEDSARSettlementDateSunday() throws Exception {
		final LocalDate requestedSettlementDate = LocalDate.of(2018, 2, 18); 
		final Instruction instruction = new Instruction("Company1", TradeType.BUY, LocalDate.of(2018, 2, 18),
				requestedSettlementDate, Currency.getInstance("AED"),
				BigDecimal.valueOf(0.1), 100, BigDecimal.valueOf(1));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(requestedSettlementDate, instruction.getSettlementDate());
	}
}
