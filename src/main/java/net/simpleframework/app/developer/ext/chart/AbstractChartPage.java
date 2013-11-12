package net.simpleframework.app.developer.ext.chart;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.app.developer.AbstractComponentPage;
import net.simpleframework.app.developer.DeveloperPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.InputElement;
import net.simpleframework.mvc.common.element.Option;
import net.simpleframework.mvc.component.ext.highchart.HighchartBean;

public abstract class AbstractChartPage extends AbstractComponentPage {

	protected HighchartBean addHighchart(final PageParameter pp) {
		return (HighchartBean) addComponentBean(pp, "AbstractChartPage_chart", HighchartBean.class)
				.setContainerId("idAbstractChartPage_chart");
	}

	@Override
	protected String toHtml(final PageParameter pp, final Map<String, Object> variables,
			final String currentVariable) throws IOException {
		final StringBuilder sb = new StringBuilder();
		final String t = pp.getParameter("t");
		final InputElement iEle = InputElement
				.select()
				.setOnchange("$Actions.loc('" + url(DeveloperPage.class) + "?t=' + $F(this));")
				.addElements(new Option("chart_BasicLine", "Basic line"))
				.addElements(
						new Option("chart_PieChart", "Pie chart").setSelected("chart_PieChart".equals(t)))
				.addElements(
						new Option("chart_BasicColumn", "Basic column").setSelected("chart_BasicColumn"
								.equals(t)));
		sb.append(iEle);
		sb.append("<div id='idAbstractChartPage_chart'></div>");
		return sb.toString();
	}
}
