package trade.report.generator.entity;

import java.math.BigDecimal;
import java.time.LocalDate;


public class FinancialEntity {
	// A financial entity whose shares are to be bought or sold
	private final String entity;
	//Settlement date
	private final LocalDate date;
	//Rank for the date
	private final int rank;
	//Trade amount for the date
	private final BigDecimal tradeAmount;
	
	public FinancialEntity(int rank, String entity, LocalDate date,BigDecimal tradeAmount) {
		this.rank = rank;
		this.entity = entity;
		this.date = date;
		this.tradeAmount=tradeAmount;
	}


	public BigDecimal getTradeAmount() {
		return tradeAmount;
	}


	public String getEntity() {
		return entity;
	}


	public LocalDate getDate() {
		return date;
	}


	public int getRank() {
		return rank;
	}

	
	@Override
	public boolean equals(Object obj) {
		final FinancialEntity other = (FinancialEntity) obj;

		return other.getRank() == this.getRank() && other.getEntity().equals(this.getEntity())
				&& other.getDate().equals(this.getDate());
	}

	@Override
	public String toString() {
		return "(" + getRank() + ") - " + getEntity();
	}
}
