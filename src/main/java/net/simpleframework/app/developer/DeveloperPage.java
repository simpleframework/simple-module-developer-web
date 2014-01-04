package net.simpleframework.app.developer;

import java.io.IOException;
import java.util.Map;

import net.simpleframework.app.developer.base.ajaxRequest.AjaxRequestPage;
import net.simpleframework.app.developer.base.calendar.CalendarPage;
import net.simpleframework.app.developer.base.htmlEditor.HtmlEditorPage;
import net.simpleframework.app.developer.base.pager.MemoryTablePage;
import net.simpleframework.app.developer.ext.category.CategoryPage;
import net.simpleframework.app.developer.ext.chart.BasicColumnPage;
import net.simpleframework.app.developer.ext.chart.BasicLinePage;
import net.simpleframework.app.developer.ext.chart.PieChartPage;
import net.simpleframework.app.developer.module.dictSelect.DictSelectPage;
import net.simpleframework.common.StringUtils;
import net.simpleframework.common.coll.KVMap;
import net.simpleframework.mvc.PageMapping;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.LinkElement;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.base.include.PageIncludeBean;
import net.simpleframework.mvc.component.ui.tree.AbstractTreeHandler;
import net.simpleframework.mvc.component.ui.tree.TreeBean;
import net.simpleframework.mvc.component.ui.tree.TreeNode;
import net.simpleframework.mvc.component.ui.tree.TreeNodes;
import net.simpleframework.mvc.template.struct.NavigationButtons;
import net.simpleframework.mvc.template.t1.T1ResizedLCTemplatePage;

@PageMapping(url = "/developer")
public class DeveloperPage extends T1ResizedLCTemplatePage {

	static KVMap all = new KVMap();
	static {
		PageTreeItem g1, g2, g3;
		all.add("$_g1", g1 = new PageTreeItem("基础组件")).add("$_g2", g2 = new PageTreeItem("扩展"))
				.add("$_g3", g3 = new PageTreeItem("应用"));

		g1.children.add("ajaxRequest", new PageTreeItem(AjaxRequestPage.class, "AJAX"))
				.add("memoryTable", new PageTreeItem(MemoryTablePage.class, "表格"))
				.add("calendar", new PageTreeItem(CalendarPage.class, "日期选取"))
				.add("htmlEditor", new PageTreeItem(HtmlEditorPage.class, "HtmlEditor"));

		g2.children.add("category", new PageTreeItem(CategoryPage.class, "类目"));
		g2.children.add("chart_BasicLine", new PageTreeItem(BasicLinePage.class, "图表"));
		g2.children.add("chart_PieChart", new PageTreeItem(PieChartPage.class, true));
		g2.children.add("chart_BasicColumn", new PageTreeItem(BasicColumnPage.class, true));

		g3.children.add("dictSelect", new PageTreeItem(DictSelectPage.class, "字典选取"));
	}

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		addComponentBean(pp, "developerComponentPage", PageIncludeBean.class).setPageUrl(
				url(DeveloperComponentTabsPage.class)).setContainerId("developer_component_panel");

		addComponentBean(pp, TreeBean.class, DemoTree.class).setCookies(false).setContainerId(
				"developer_navtree");
	}

	@Override
	public NavigationButtons getNavigationBar(final PageParameter pp) {
		final NavigationButtons btns = super.getNavigationBar(pp).append(new LinkElement("开发者"));
		final PageTreeItem pm = getTreeItem(pp.getParameter("t"));
		if (pm != null && StringUtils.hasText(pm.title)) {
			btns.append(new LinkElement(pm.title));
		}
		return btns;
	}

	@Override
	protected String toHtml(final PageParameter pp, final Map<String, Object> variables,
			final String variable) throws IOException {
		if ("content_left".equals(variable)) {
			return "<div id='developer_navtree' style='padding: 6px;'></div>";
		} else if ("content_center".equals(variable)) {
			return "<div id='developer_component_panel'></div>";
		}
		return null;
	}

	static class PageTreeItem {
		KVMap children = new KVMap();

		Class<? extends AbstractComponentPage> pageClass;

		String title;

		boolean hidden;

		public PageTreeItem(final String title) {
			this(null, title);
		}

		public PageTreeItem(final Class<? extends AbstractComponentPage> pageClass, final String title) {
			this.pageClass = pageClass;
			this.title = title;
		}

		public PageTreeItem(final Class<? extends AbstractComponentPage> pageClass,
				final boolean hidden) {
			this.pageClass = pageClass;
			this.hidden = hidden;
		}
	}

	static PageTreeItem getTreeItem(final String name) {
		return getTreeItem(all, name);
	}

	private static PageTreeItem getTreeItem(final KVMap kv, final String name) {
		if (StringUtils.hasText(name)) {
			PageTreeItem item = (PageTreeItem) kv.get(name);
			if (item != null) {
				return item;
			}
			for (final Object o : kv.values()) {
				item = getTreeItem(((PageTreeItem) o).children, name);
				if (item != null) {
					return item;
				}
			}
		}
		return null;
	}

	public static class DemoTree extends AbstractTreeHandler {
		@Override
		public TreeNodes getTreenodes(final ComponentParameter cp, final TreeNode parent) {
			final TreeNodes nodes = TreeNodes.of();
			final KVMap data = parent == null ? all
					: (((PageTreeItem) parent.getDataObject())).children;
			for (final Map.Entry<String, Object> o : data.entrySet()) {
				final PageTreeItem item = (PageTreeItem) o.getValue();
				if (item.hidden) {
					continue;
				}
				final TreeNode node = new TreeNode((TreeBean) cp.componentBean, parent, item);
				node.setText(item.title);
				if (item.pageClass != null) {
					node.setJsClickCallback("$Actions.loc('" + url(DeveloperPage.class) + "?t="
							+ o.getKey() + "');");
				} else {
					node.setOpened(true);
				}
				nodes.add(node);
			}
			return nodes;
		}
	}
}
