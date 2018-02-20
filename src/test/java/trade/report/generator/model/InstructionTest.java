package trade.report.generator.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import org.junit.Test;

import trade.report.generator.model.Instruction.TradeType;

public class InstructionTest {

	@Test
	public void testTradeAmountCalculation() throws Exception {
		final BigDecimal agreedFx = BigDecimal.valueOf(0.10);
		final BigDecimal pricePerUnit = BigDecimal.valueOf(1);
		final int units = 100;
		final Instruction instruction = new Instruction("Company1", TradeType.SELL, LocalDate.of(2018, 2, 1),
				LocalDate.of(2018, 2, 2), Currency.getInstance("INR"), agreedFx, units, pricePerUnit);
		final BigDecimal calculatedAmount = pricePerUnit.multiply(agreedFx).multiply(BigDecimal.valueOf(units)).setScale(3,
				BigDecimal.ROUND_HALF_EVEN);
		assertEquals(calculatedAmount, instruction.getTradeAmount());
	}

}
