package trade.report.generator.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static trade.report.generator.util.Constants.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import trade.report.generator.entity.FinancialEntity;
import trade.report.generator.model.Instruction;
import trade.report.generator.model.Instruction.TradeType;
import trade.report.generator.util.FinancialEntityRankCalculator;
import trade.report.generator.util.InstructionSettlementDateCalculator;


public class FinancialEntityRankCalculatorTest {
	private Set<Instruction> instructions=new HashSet<Instruction>();

	@Before
	public void getSetOfInstructions() {
		instructions.add(new Instruction("Company1", TradeType.BUY, MONDAY, MONDAY,
				Currency.getInstance(USD), BigDecimal.valueOf(1), 100, BigDecimal.valueOf(1)));

		instructions.add(new Instruction("Company2", TradeType.BUY, MONDAY, MONDAY,
				Currency.getInstance(INR), BigDecimal.valueOf(1), 200, BigDecimal.valueOf(1)));

		instructions.add(new Instruction("Company3", TradeType.BUY, SATURDAY, MONDAY,
				Currency.getInstance(GBP), BigDecimal.valueOf(1), 300, BigDecimal.valueOf(1)));

		instructions.add(new Instruction("Company4", TradeType.SELL, SUNDAY, MONDAY,
				Currency.getInstance(GBP), BigDecimal.valueOf(1), 200, BigDecimal.valueOf(1)));
		
		instructions.add(new Instruction("Company5", TradeType.BUY, TUESDAY, TUESDAY,
				Currency.getInstance(INR), BigDecimal.valueOf(1), 400, BigDecimal.valueOf(1)));

		instructions.add(new Instruction("Company6", TradeType.SELL, TUESDAY, TUESDAY,
				Currency.getInstance(INR), BigDecimal.valueOf(1), 1000, BigDecimal.valueOf(1)));
		
		instructions.add(new Instruction("Company7", TradeType.BUY,  WEDNESDAY, WEDNESDAY,
				Currency.getInstance(USD), BigDecimal.valueOf(1), 7000, BigDecimal.valueOf(1)));

		InstructionSettlementDateCalculator.calculateSettlementDates(instructions);
	}

	@Test
	public void testDailyIncomingAmount() throws Exception {
		final Map<LocalDate, BigDecimal> dailyIncomingAmount = FinancialEntityRankCalculator
				.calculateDailyAmount(instructions,i -> i.getTradeType().equals(TradeType.SELL));

		assertEquals(2, dailyIncomingAmount.size());
		assertTrue(Objects.equals(dailyIncomingAmount.get(MONDAY),
				BigDecimal.valueOf(200.00).setScale(3, BigDecimal.ROUND_HALF_EVEN)));
		assertTrue(Objects.equals(dailyIncomingAmount.get(TUESDAY),
				BigDecimal.valueOf(1000.00).setScale(3, BigDecimal.ROUND_HALF_EVEN)));
	}

	@Test
	public void testDailyOutgoingAmount() throws Exception {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = FinancialEntityRankCalculator
				.calculateDailyAmount(instructions,i -> i.getTradeType().equals(TradeType.BUY));

		assertEquals(3, dailyOutgoingAmount.size());
		assertTrue(Objects.equals(dailyOutgoingAmount.get(MONDAY),
				BigDecimal.valueOf(600.00).setScale(3, BigDecimal.ROUND_HALF_EVEN)));
		assertTrue(Objects.equals(dailyOutgoingAmount.get(TUESDAY),
				BigDecimal.valueOf(400.00).setScale(3, BigDecimal.ROUND_HALF_EVEN)));
			}

	@Test
	public void testDailyIncomingRanking() throws Exception {
		final Map<LocalDate, LinkedList<FinancialEntity>> dailyIncomingRanking = FinancialEntityRankCalculator
				.calculateRanking(instructions,i -> i.getTradeType().equals(TradeType.SELL));

		assertEquals(2, dailyIncomingRanking.size());

		assertEquals(1, dailyIncomingRanking.get(MONDAY).size());
		assertEquals(1, dailyIncomingRanking.get(TUESDAY).size());

		assertTrue(dailyIncomingRanking.get(MONDAY).contains(new FinancialEntity(1, "Company4", MONDAY,new BigDecimal(200))));
		assertTrue(dailyIncomingRanking.get(TUESDAY).contains(new FinancialEntity(1, "Company6", TUESDAY,new BigDecimal(1000))));

	}

	@Test
	public void testDailyOutgoingRanking() throws Exception {
		final Map<LocalDate, LinkedList<FinancialEntity>> dailyOutgoingRanking = FinancialEntityRankCalculator
				.calculateRanking(instructions,i -> i.getTradeType().equals(TradeType.BUY));

		assertEquals(3, dailyOutgoingRanking.size());

		assertEquals(3, dailyOutgoingRanking.get(MONDAY).size());
		assertEquals(1, dailyOutgoingRanking.get(TUESDAY).size());
		assertEquals(1, dailyOutgoingRanking.get(WEDNESDAY).size());

		assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new FinancialEntity(1, "Company3", MONDAY,new BigDecimal(300))));
		assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new FinancialEntity(2, "Company2", MONDAY,new BigDecimal(200))));
		assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new FinancialEntity(3, "Company1", MONDAY,new BigDecimal(100))));

		assertTrue(dailyOutgoingRanking.get(TUESDAY).contains(new FinancialEntity(1, "Company5", TUESDAY,new BigDecimal(400))));

		assertTrue(dailyOutgoingRanking.get(WEDNESDAY).contains(new FinancialEntity(1, "Company7", WEDNESDAY,new BigDecimal(7000))));
	}
}
