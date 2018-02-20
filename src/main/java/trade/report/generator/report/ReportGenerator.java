package trade.report.generator.report;

import java.util.Set;

import trade.report.generator.model.Instruction;


public interface ReportGenerator {

	String generateReportForInstructions(Set<Instruction> instructions);
	}

