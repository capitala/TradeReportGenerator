package trade.report.generator.main;

import trade.report.generator.report.ReportGeneratorImpl;
import trade.report.generator.util.SampleDataProvider;

/**
 *  Main class for the trade report generation
 *  It takes the data from the SampleDataProvider
 */
public class TradeReportGeneratorApplication 
{
	 public static void main(String[] args) {
	        System.out.println(new ReportGeneratorImpl().generateReportForInstructions(SampleDataProvider.getSampleInstructions()));
	 }
}
