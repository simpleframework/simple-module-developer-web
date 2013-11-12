package net.simpleframework.app.developer;

import net.simpleframework.app.developer.DeveloperPage.PageTreeItem;
import net.simpleframework.common.ClassUtils;
import net.simpleframework.mvc.IForward;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.UrlForward;
import net.simpleframework.mvc.component.ui.tabs.TabItem;
import net.simpleframework.mvc.component.ui.tabs.TabItems;
import net.simpleframework.mvc.template.TabsTemplatePage;

public class DeveloperComponentTabsPage extends TabsTemplatePage {

	@SuppressWarnings("unchecked")
	@Override
	public IForward forward(final PageParameter pp) {
		final PageTreeItem item = DeveloperPage.getTreeItem(pp.getParameter("t"));
		if (item != null && item.pageClass != null) {
			final TabItems tabItems = TabItems.of();
			pp.setRequestAttr("tabItems", tabItems);
			addAjaxRequest(pp, "ajax_demo", item.pageClass);
			tabItems.append(new TabItem().setTitle("演示").setContentRef("ajax_demo"));
			try {
				addAjaxRequest(
						pp,
						"ajax_demo_doc",
						(Class<? extends AbstractComponentPage>) ClassUtils.forName(item.pageClass
								.getName() + "_Doc"));
				tabItems.append(new TabItem().setTitle("文档").setContentRef("ajax_demo_doc"));
			} catch (final ClassNotFoundException e) {
			}
			try {
				addAjaxRequest(
						pp,
						"ajax_demo_source",
						(Class<? extends AbstractComponentPage>) ClassUtils.forName(item.pageClass
								.getName() + "_Source"));
				tabItems.append(new TabItem().setTitle("代码").setContentRef("ajax_demo_source"));
			} catch (final ClassNotFoundException e) {
			}
			return super.forward(pp);
		}
		return new UrlForward(url(DeveloperNullPage.class));
	}

	@Override
	protected TabItems tabItems(final PageParameter pp) {
		return (TabItems) pp.getRequestAttr("tabItems");
	}
}
