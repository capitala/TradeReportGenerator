package trade.report.generator.main;

import trade.report.generator.ReportGeneratorImpl;
import trade.report.generator.sample.data.SampleDataProvider;

/**
 *  Main class for the trade report generation
 *  makes use of data from the SampleDataProvider
 */
public class TradeReportGeneratorApplication 
{
	 public static void main(String[] args) {
	        System.out.println(new ReportGeneratorImpl().generateReportForInstructions(SampleDataProvider.getSampleInstructions()));
	 }
}
