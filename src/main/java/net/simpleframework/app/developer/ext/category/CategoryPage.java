package net.simpleframework.app.developer.ext.category;

import net.simpleframework.app.developer.AbstractComponentPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.ComponentParameter;
import net.simpleframework.mvc.component.ext.category.CategoryBean;
import net.simpleframework.mvc.component.ext.category.DefaultCategoryHandler;
import net.simpleframework.mvc.component.ui.tree.TreeBean;
import net.simpleframework.mvc.component.ui.tree.TreeNode;
import net.simpleframework.mvc.component.ui.tree.TreeNodes;

public class CategoryPage extends AbstractComponentPage {

	@Override
	protected void onForward(final PageParameter pp) throws Exception {
		super.onForward(pp);

		addComponentBean(pp, "DemoCategory", CategoryBean.class).setContainerId("id_CategoryPage")
				.setHandlerClass(DemoCategory.class);
	}

	public static class DemoCategory extends DefaultCategoryHandler {
		@Override
		public TreeNodes getCategoryTreenodes(final ComponentParameter cp, final TreeBean treeBean,
				final TreeNode treeNode) {
			if (treeNode == null) {
				final TreeNodes nodes = TreeNodes.of();
				final TreeNode node = new TreeNode(treeBean, "测试节点-1");
				nodes.append(node);
				node.children().append(new TreeNode(treeBean, "测试节点-1-1"))
						.append(new TreeNode(treeBean, "测试节点-1-2"));
				return nodes.append(new TreeNode(treeBean, "测试节点-2"));
			}
			return null;
		}
	}
}
