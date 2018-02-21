package trade.report.generator.util;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import trade.report.generator.entity.FinancialEntity;
import trade.report.generator.model.Instruction;

/**
 * For calculating  daily trade amounts and rankings for the financial
 * entities
 */
public class FinancialEntityRankCalculator {

	/**
	 * @param instructions
	 * @param predicate
	 * @return a map containing the total daily amount grouped by the settlement
	 *         date as per the predicate provided
	 */
	public static Map<LocalDate, BigDecimal> calculateDailyAmount(Set<Instruction> instructions,
			Predicate<Instruction> predicate) {
		return instructions.stream().filter(predicate).collect(groupingBy(Instruction::getSettlementDate,
				mapping(Instruction::getTradeAmount, reducing(BigDecimal.ZERO, BigDecimal::add))));
	}

	/**
	 * @param instructions
	 * @param predicate
	 * @return a map containing the date and corresponding list of financial
	 *         entities along with the rankings as per the predicate provided
	 */
	public static Map<LocalDate, LinkedList<FinancialEntity>> calculateRanking(Set<Instruction> instructions,
			Predicate<Instruction> predicate) {
		final Map<LocalDate, LinkedList<FinancialEntity>> ranking = new HashMap<>();
		instructions.stream().filter(predicate).collect(groupingBy(Instruction::getSettlementDate, toSet()))
				.forEach((date, dailyInstructionSet) -> {
					final AtomicInteger rank = new AtomicInteger(1);
					BigDecimal[] max = { new BigDecimal(-1) };
					final LinkedList<FinancialEntity> ranks = dailyInstructionSet.stream()
							.sorted((a, b) -> b.getTradeAmount().compareTo(a.getTradeAmount())).map(instruction -> {
								int r;
								if (max[0].equals(new BigDecimal(-1))) {
									max[0] = instruction.getTradeAmount();
									r = rank.get();
								} else
									r = instruction.getTradeAmount().compareTo(max[0]) < 0 ? rank.incrementAndGet()
											: rank.get();
								FinancialEntity f = new FinancialEntity(r, instruction.getEntity(), date,
										instruction.getTradeAmount());
								max[0] = instruction.getTradeAmount();
								return f;
							}).collect(toCollection(LinkedList::new));
					ranking.put(date, ranks);
				});
		return ranking;
	}

}