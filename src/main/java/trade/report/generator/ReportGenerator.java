package trade.report.generator;

import java.util.Set;

import trade.report.generator.model.Instruction;


public interface ReportGenerator {

	String generateReportForInstructions(Set<Instruction> instructions);
	}

