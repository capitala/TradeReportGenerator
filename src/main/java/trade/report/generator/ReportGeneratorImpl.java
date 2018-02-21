package trade.report.generator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import trade.report.generator.entity.FinancialEntity;
import trade.report.generator.model.Instruction;
import trade.report.generator.model.Instruction.TradeType;
import trade.report.generator.util.FinancialEntityRankCalculator;
import trade.report.generator.util.InstructionSettlementDateCalculator;


/**
 *
 *
 */
public class ReportGeneratorImpl implements ReportGenerator {

	private StringBuilder stringBuilder = new StringBuilder();

	@Override
	public String generateReportForInstructions(Set<Instruction> instructions) {
		// first calculate the correct settlement dates
		InstructionSettlementDateCalculator.calculateSettlementDates(instructions);

		// Build the report string
		return generateDailyOutgoingRanking(instructions, generateDailyIncomingRanking(instructions,
				generateDailyIncomingAmount(instructions, generateDailyOutgoingAmount(instructions, stringBuilder))))
						.toString();
	}

	private static StringBuilder generateDailyOutgoingAmount(Set<Instruction> instructions,
			StringBuilder stringBuilder) {
		final Map<LocalDate, BigDecimal> dailyOutgoingAmount = FinancialEntityRankCalculator
				.calculateDailyAmount(instructions,i -> i.getTradeType().equals(TradeType.BUY));

		stringBuilder.append("\n\n")
				.append("Daily Amount(Outgoing)\n\n")
				.append("      Date       : Trade Amount in USD      \n\n");

		for (LocalDate date : dailyOutgoingAmount.keySet()) {
			stringBuilder.append(date).append("       :      ").append(dailyOutgoingAmount.get(date)).append("\n");
		}
		stringBuilder.append("\n\n");
		return stringBuilder;
	}

	private static StringBuilder generateDailyIncomingAmount(Set<Instruction> instructions,
			StringBuilder stringBuilder) {
		final Map<LocalDate, BigDecimal> dailyIncomingAmount = FinancialEntityRankCalculator
				.calculateDailyAmount(instructions,i -> i.getTradeType().equals(TradeType.SELL));

		stringBuilder.append("\n\n")
				.append("Daily Amount(Incoming)\n\n")
				.append("      Date       : Trade Amount In USD    \n\n");

		for (LocalDate date : dailyIncomingAmount.keySet()) {
			stringBuilder.append(date).append("       :      ").append(dailyIncomingAmount.get(date)).append("\n");
		}
		stringBuilder.append("\n\n");
		return stringBuilder;
	}

	private static StringBuilder generateDailyOutgoingRanking(Set<Instruction> instructions,
			StringBuilder stringBuilder) {
		final Map<LocalDate, LinkedList<FinancialEntity>> dailyOutgoingRanking = FinancialEntityRankCalculator
				.calculateRanking(instructions,i -> i.getTradeType().equals(TradeType.BUY));

		stringBuilder.append("\n\n")
				.append("Daily Ranking(Outgoing)\n\n")
				.append("     Date    :     Rank   :   Entity        :   Trade Amount in USD     \n\n");

		for (LocalDate date : dailyOutgoingRanking.keySet()) {
			for (FinancialEntity rank : dailyOutgoingRanking.get(date)) {
				stringBuilder.append(date).append("   :      ").append(rank.getRank()).append("     :    ")
						.append(rank.getEntity()).append("     ->    ")
						.append(rank.getTradeAmount()).append("\n");
			}
		}
		stringBuilder.append("\n\n");
		return stringBuilder;
	}

	private static StringBuilder generateDailyIncomingRanking(Set<Instruction> instructions,
			StringBuilder stringBuilder) {
		final Map<LocalDate, LinkedList<FinancialEntity>> dailyIncomingRanking = FinancialEntityRankCalculator
				.calculateRanking(instructions,i -> i.getTradeType().equals(TradeType.SELL));

		stringBuilder.append("\n\n")
				.append("Daily Ranking(Incoming)\n\n")
				.append("     Date    :     Rank   :   Entity        :   Trade Amount in USD     \n\n");

		for (LocalDate date : dailyIncomingRanking.keySet()) {
			for (FinancialEntity rank : dailyIncomingRanking.get(date)) {
				stringBuilder.append(date).append("   :      ").append(rank.getRank()).append("     :    ")
						.append(rank.getEntity()).append("     ->    ")
						.append(rank.getTradeAmount()).append("\n");
			}
		}
		stringBuilder.append("\n\n");
		return stringBuilder;
	}

}
