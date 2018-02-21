package trade.report.generator.model;

import static org.junit.Assert.*;
import static trade.report.generator.util.Constants.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Test;

import trade.report.generator.model.Instruction.TradeType;

public class InstructionTest {

	@Test
	public void testTradeAmountCalculation() throws Exception {
		final Instruction instruction = new Instruction(SAMPLE_COMPANY, TradeType.SELL, MONDAY,
				MONDAY, Currency.getInstance(INR), SAMPLE_AGREEDFX, SAMPLE_UNITS, SAMPLE_PRICEPERUNIT);
		final BigDecimal calculatedAmount = SAMPLE_PRICEPERUNIT.multiply(SAMPLE_AGREEDFX).multiply(BigDecimal.valueOf(SAMPLE_UNITS)).setScale(3,
				BigDecimal.ROUND_HALF_EVEN);
		assertEquals(calculatedAmount, instruction.getTradeAmount());
	}

}
