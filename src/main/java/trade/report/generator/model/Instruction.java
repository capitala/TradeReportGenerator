package trade.report.generator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public class Instruction {

	// A financial entity whose shares are to be bought or sold
	private final String entity;

	// This represent the type of trade (Buy or Sell)
	private final TradeType tradeType;

	// Date on which the instruction was sent
	private final LocalDate instructionDate;

	// The Date on which the client wished for the instruction to be settled
	// with respect to Instruction LocalDate
	private LocalDate settlementDate;
	// Type of currency
	private final Currency currency;

	// Agreed foreign exchange rate with respect to USD
	private final BigDecimal agreedFx;

	// Number of shares
	private final int units;

	// Price/unit
	private final BigDecimal pricePerUnit;

	// Total trade amount(USD)
	private final BigDecimal tradeAmount;



	public String getEntity() {
		return entity;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public void setSettlementDate(LocalDate newDate) {
		settlementDate = newDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	public int getUnits() {
		return units;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}

	public BigDecimal getTradeAmount() {
		return tradeAmount.setScale(3, BigDecimal.ROUND_HALF_EVEN);
	}

	public enum TradeType {
		BUY, SELL
	}

	public Instruction(String entity, TradeType tradeType, LocalDate instructionDate, LocalDate settlementDate,
			Currency currency, BigDecimal agreedFx, int units, BigDecimal pricePerUnit) {
		this.entity = entity;
		this.tradeType = tradeType;
		this.instructionDate = instructionDate;
		this.settlementDate = settlementDate;
		this.currency = currency;
		this.agreedFx = agreedFx;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
		this.tradeAmount = calculateTradeAmount(this);
	}
	/**
	 * @param instruction
	 * @return The tradeAmount value for the instruction
	 */
	private static BigDecimal calculateTradeAmount(Instruction instruction) {
		return instruction.getPricePerUnit().multiply(BigDecimal.valueOf(instruction.getUnits()))
				.multiply(instruction.getAgreedFx());
	}
	@Override
	public String toString() {
		return entity;
	}

}
