package trade.report.generator.util;

import static org.junit.Assert.assertEquals;
import static trade.report.generator.util.Constants.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Test;

import trade.report.generator.model.Instruction;
import trade.report.generator.model.Instruction.TradeType;
import trade.report.generator.util.InstructionSettlementDateCalculator;

public class InstructionSettlementDateCalculatorTest {

	@Test
	public void calculateDefaultSettlementDateFriday() throws Exception {
		final Instruction instruction = new Instruction(SAMPLE_COMPANY, TradeType.BUY, FRIDAY,
				FRIDAY, Currency.getInstance(USD), BigDecimal.valueOf(0.1), 100,
				BigDecimal.valueOf(1));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(FRIDAY, instruction.getSettlementDate());
	}
	
	@Test
	public void calculateAEDSARSettlementDateFriday() throws Exception {
		final Instruction instruction = new Instruction(SAMPLE_COMPANY, TradeType.BUY, FRIDAY,
				FRIDAY, Currency.getInstance(AED), 
				BigDecimal.valueOf(0.1), 100, BigDecimal.valueOf(1));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(SUNDAY, instruction.getSettlementDate());
	}
	
	@Test
	public void calculateDefaultSettlementDateSaturday() throws Exception {
		final Instruction instruction = new Instruction(SAMPLE_COMPANY, TradeType.BUY, SATURDAY,
				SATURDAY, Currency.getInstance(USD), BigDecimal.valueOf(0.1), 100,
				BigDecimal.valueOf(2));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(MONDAY, instruction.getSettlementDate());
	}
	
	@Test
	public void calculateAEDSARSettlementDateSaturday() throws Exception { 
		final Instruction instruction = new Instruction(SAMPLE_COMPANY, TradeType.BUY, FRIDAY,
				SATURDAY, Currency.getInstance(AED), 
				BigDecimal.valueOf(0.1), 100, BigDecimal.valueOf(1));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(SUNDAY, instruction.getSettlementDate());
	}

	@Test
	public void calculateDefaultSettlementDateSunday() throws Exception {
		final Instruction instruction = new Instruction(SAMPLE_COMPANY, TradeType.BUY, SUNDAY,
				SUNDAY, Currency.getInstance(USD), BigDecimal.valueOf(0.1), 100,
				BigDecimal.valueOf(1));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(MONDAY, instruction.getSettlementDate());
	}



	@Test
	public void calculateAEDSARSettlementDateSunday() throws Exception {
		final Instruction instruction = new Instruction(SAMPLE_COMPANY, TradeType.BUY, SUNDAY,
				SUNDAY, Currency.getInstance(AED),
				BigDecimal.valueOf(0.1), 100, BigDecimal.valueOf(1));
		InstructionSettlementDateCalculator.calculateSettlementDate(instruction);
		assertEquals(SUNDAY, instruction.getSettlementDate());
	}
}
