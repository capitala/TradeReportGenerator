package trade.report.settlement.calculator;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import trade.report.generator.model.Instruction;
import trade.report.settlement.util.ArabSettlementDateCalculator;
import trade.report.settlement.util.DefaultSettlementDateCalculator;
import trade.report.settlement.util.SettlementDateCalculator;

public class InstructionSettlementDateCalculator {

	/**
	 *function to iterate over list of instructions and calculate
	 * settlement date for every given instruction
	 * 
	 * @param instructions
	 *  Set of instructions for which the settlement date will be
	 *  calculated
	 */
	public static void calculateSettlementDates(Set<Instruction> instructions) {
		instructions.forEach(InstructionSettlementDateCalculator::calculateSettlementDate);
	}

	/**
	 * Calculate the settlementDate
	 * 
	 * @param instruction
	 *  Instruction for which the settlement date will be calculated
	 */
	public static void calculateSettlementDate(Instruction instruction) {
		// Select the calculator to be used for calculating settlement date
		final SettlementDateCalculator settlementDateCalculator = getSettlementDateCalculator(instruction.getCurrency());

		// find the settlement date for given instruction
		final LocalDate settlementDate = settlementDateCalculator.findSettlementDate(instruction.getSettlementDate());

		if (settlementDate != null) {
			// set the settlement date
			instruction.setSettlementDate(settlementDate);
		}
	}

	/**
	 * Select the settlement date calculator as per the currency type
	 * @param currency
	 * @return the appropriate SettlementDateCalculator
	 */
	private static SettlementDateCalculator getSettlementDateCalculator(Currency currency) {
		String currencyType=currency.getCurrencyCode();
		if (currencyType.equals("AED")|| currencyType.equals("SAR")) {
			return new ArabSettlementDateCalculator();
		}
		return new DefaultSettlementDateCalculator();
	}
}
