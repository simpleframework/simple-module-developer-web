package net.simpleframework.app.developer.ext.chart;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.ELayout;
import net.simpleframework.mvc.common.element.ETextAlign;
import net.simpleframework.mvc.common.element.EVerticalAlign;
import net.simpleframework.mvc.component.ext.highchart.DataObj;
import net.simpleframework.mvc.component.ext.highchart.HcChart;
import net.simpleframework.mvc.component.ext.highchart.HcLabels;
import net.simpleframework.mvc.component.ext.highchart.HcLegend;
import net.simpleframework.mvc.component.ext.highchart.HcPlotOptions;
import net.simpleframework.mvc.component.ext.highchart.HcPlotOptions.HcOptMarker;
import net.simpleframework.mvc.component.ext.highchart.HcPlotOptions.HcOptSeries;
import net.simpleframework.mvc.component.ext.highchart.HcSeries;
import net.simpleframework.mvc.component.ext.highchart.HcSubtitle;
import net.simpleframework.mvc.component.ext.highchart.HcTitle;
import net.simpleframework.mvc.component.ext.highchart.HcXAxis;
import net.simpleframework.mvc.component.ext.highchart.HcYAxis;
import net.simpleframework.mvc.component.ext.highchart.HighchartBean;

public class BasicLinePage extends AbstractChartPage {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);

		final HighchartBean hc = addHighchart(pp);

		hc.setChart(new HcChart().setMarginRight(130));
		hc.setTitle(new HcTitle().setText("Monthly Average Temperature").setX(-20));
		hc.setSubtitle(new HcSubtitle().setText("Source: WorldClimate.com"));
		hc.setLegend(new HcLegend().setLayout(ELayout.vertical).setBorderWidth(0)
				.setAlign(ETextAlign.right).setVerticalAlign(EVerticalAlign.top));

		final HcXAxis xAxis = new HcXAxis().setCategories(new String[] { "一月", "二月", "三月", "四月",
				"五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" });
		hc.setxAxis(xAxis.setLabels(new HcLabels().setRotation(315)));
		hc.setyAxis(new HcYAxis().setTitle("Temperature (°C)").setTickInterval(2));

		final HcPlotOptions opt = new HcPlotOptions();
		hc.setPlotOptions(opt);

		opt.setSeries(new HcOptSeries()
				.setCursor("pointer")
				.setMarker(new HcOptMarker().setRadius(6))
				.setPoint_onClick("function() { alert('Category: '+ this.name +', value: '+ this.y); }"));
		//
		// hc.addSeries(new HcOptSeries(hc).setName("Tokyo").addData(7.0, 6.9,
		// 9.5,
		// 14.5, 18.2, 21.5, 25.2,
		// 26.5, 23.3, 18.3, 13.9, 9.6));
		// hc.addSeries(new HcOptSeries(hc).setName("New York").addData(-0.2, 0.8,
		// 5.7, 11.3, 17.0, 22.0,
		// 24.8, 24.1, 20.1, 14.1, 8.6, 2.5));
		// hc.addSeries(new HcOptSeries(hc).setName("Berlin").addData(-0.9, 0.6,
		// 3.5,
		// 8.4, 13.5, 17.0,
		// 18.6, 17.9, 14.3, 9.0, 3.9, 1.0));
		// hc.addSeries(new HcOptSeries(hc).setName("London").addData(3.9, 4.2,
		// 5.7,
		// 8.5, 11.9, 15.2, 17.0,
		// 16.6, 14.2, 10.3, 6.6, 4.8));

		hc.addSeries(new HcSeries().setName("Tokyo").addData(new DataObj("Jan", 7.0),
				new DataObj("Feb", 6.9), new DataObj("Mar", 9.5), new DataObj("Apr", 14.5),
				new DataObj("May", 18.2), new DataObj("Jun", 21.5), new DataObj("Aug", 25.2),
				new DataObj("Jul", 26.5), new DataObj("Sep", 23.3), new DataObj("Oct", 18.3),
				new DataObj("Nov", 13.9), new DataObj("Dec", 9.6)));
	}
}
