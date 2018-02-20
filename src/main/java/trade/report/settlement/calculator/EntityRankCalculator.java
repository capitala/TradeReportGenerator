package trade.report.settlement.calculator;

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
 * Describes a mapping between dates and the trade amount of those dates, based
 * on instructions
 */
public class EntityRankCalculator {

	/**
	 * @param instructions
	 * @param predicate
	 * @return a map object containing the total daily amount grouped by the
	 *         settlement date as per the predicate provided
	 */
	public static Map<LocalDate, BigDecimal> calculateDailyAmount(Set<Instruction> instructions,
			Predicate<Instruction> predicate) {
		return instructions.stream().filter(predicate).collect(groupingBy(Instruction::getSettlementDate,
				mapping(Instruction::getTradeAmount, reducing(BigDecimal.ZERO, BigDecimal::add))));
	}

	/**
	 * @param instructions
	 * @param predicate
	 * @return
	 */
	public static Map<LocalDate, LinkedList<FinancialEntity>> calculateRanking(Set<Instruction> instructions,
			Predicate<Instruction> predicate) {
		final Map<LocalDate, LinkedList<FinancialEntity>> ranking = new HashMap<>();

		instructions.stream().filter(predicate).collect(groupingBy(Instruction::getSettlementDate, toSet()))
				.forEach((date, dailyInstructionSet) -> {
					final AtomicInteger rank = new AtomicInteger(1);

					final LinkedList<FinancialEntity> ranks = dailyInstructionSet.stream()
							.sorted((a, b) -> b.getTradeAmount().compareTo(a.getTradeAmount()))
							.map(instruction -> new FinancialEntity(rank.getAndIncrement(), instruction.getEntity(),
									date))
							.collect(toCollection(LinkedList::new));
					ranking.put(date, ranks);
				});
		return ranking;
	}
}