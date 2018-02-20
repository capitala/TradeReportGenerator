package trade.report.generator.entity;

import java.time.LocalDate;


public class FinancialEntity {

	private final String entity;
	private final LocalDate date;
	private final int rank;
	
	public FinancialEntity(int rank, String entity, LocalDate date) {
		this.rank = rank;
		this.entity = entity;
		this.date = date;
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
