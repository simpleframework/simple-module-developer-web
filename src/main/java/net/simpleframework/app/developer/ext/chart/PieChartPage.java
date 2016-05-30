package net.simpleframework.app.developer.ext.chart;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.ext.highchart.DataKV;
import net.simpleframework.mvc.component.ext.highchart.DataObj;
import net.simpleframework.mvc.component.ext.highchart.EChart.EHcType;
import net.simpleframework.mvc.component.ext.highchart.HcPlotOptions;
import net.simpleframework.mvc.component.ext.highchart.HcPlotOptions.HcOptPie;
import net.simpleframework.mvc.component.ext.highchart.HcSeries;
import net.simpleframework.mvc.component.ext.highchart.HighchartBean;

public class PieChartPage extends AbstractChartPage {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);

		final HighchartBean hc = addHighchart(pp);

		hc.setTitle("Browser market shares at a specific website, 2010");

		final HcPlotOptions opt = new HcPlotOptions();
		hc.setPlotOptions(opt);
		opt.setPie(new HcOptPie().setAllowPointSelect(true).setCursor("pointer"));

		hc.addSeries(new HcSeries().setName("Browser share").setType(EHcType.pie).addData(
				new DataKV("Firefox", 45.0), new DataKV("IE", 26.8),
				new DataObj("Chrome", 12.8).setSliced(true).setSelected(true),
				new DataKV("Safari", 8.5), new DataKV("Opera", 6.2), new DataKV("Others", 0.7)));
	}
}
