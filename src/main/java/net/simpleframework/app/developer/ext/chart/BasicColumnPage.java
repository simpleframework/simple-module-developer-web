package net.simpleframework.app.developer.ext.chart;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.ext.highchart.EChart.EHcType;
import net.simpleframework.mvc.component.ext.highchart.HcChart;
import net.simpleframework.mvc.component.ext.highchart.HcDataLabels;
import net.simpleframework.mvc.component.ext.highchart.HcPlotOptions;
import net.simpleframework.mvc.component.ext.highchart.HcPlotOptions.HcOptColumn;
import net.simpleframework.mvc.component.ext.highchart.HcSeries;
import net.simpleframework.mvc.component.ext.highchart.HcTooltip;
import net.simpleframework.mvc.component.ext.highchart.HcXAxis;
import net.simpleframework.mvc.component.ext.highchart.HcYAxis;
import net.simpleframework.mvc.component.ext.highchart.HighchartBean;

public class BasicColumnPage extends AbstractChartPage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		final HighchartBean hc = addHighchart(pp);
		hc.setChart(new HcChart().setType(EHcType.column));

		hc.setTitle("Monthly Average Rainfall").setSubtitle("Source: WorldClimate.com");

		final HcPlotOptions opt = new HcPlotOptions();
		final HcDataLabels dataLabels = new HcDataLabels().setEnabled(true).setRotation(40);
		opt.setColumn(new HcOptColumn().setBorderWidth(3).setDataLabels(dataLabels));
		hc.setPlotOptions(opt);

		hc.setTooltip(new HcTooltip()
				.setShared(true)
				.setUseHTML(true)
				.setValueDecimals(2)
				.setHeaderFormat("<span style='font-size:10px'>{point.key}</span><table>")
				.setPointFormat(
						"<tr><td style='color:{series.color}; padding:0'>{series.name}: </td>"
								+ "<td style='padding:0'><b>{point.y} mm</b></td></tr>")
				.setFooterFormat("</table>"));

		hc.setxAxis(new HcXAxis().setCategories(new String[] { "Jan", "Feb", "Mar", "Apr", "May",
				"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
		hc.setyAxis(new HcYAxis().setTitle("Rainfall (mm)"));

		hc.addSeries(new HcSeries().setName("Tokyo").addData(49.9, 71.5, 106.4, 129.2, 144.0, 176.0,
				135.6, 148.5, 216.4, 194.1, 95.6, 54.4));
		hc.addSeries(new HcSeries().setName("New York").addData(83.6, 78.8, 98.5, 93.4, 106.0, 84.5,
				105.0, 104.3, 91.2, 83.5, 106.6, 92.3));
		hc.addSeries(new HcSeries().setName("London").addData(48.9, 38.8, 39.3, 41.4, 47.0, 48.3,
				59.0, 59.6, 52.4, 65.2, 59.3, 51.2));
		hc.addSeries(new HcSeries().setName("Berlin").addData(42.4, 33.2, 34.5, 39.7, 52.6, 75.5,
				57.4, 60.4, 47.6, 39.1, 46.8, 51.1));
	}
}
