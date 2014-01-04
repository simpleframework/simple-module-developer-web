package net.simpleframework.app.developer;

import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.template.lets.FormTemplatePage;

public abstract class AbstractComponentPage extends FormTemplatePage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		pp.addImportCSS(AbstractComponentPage.class, "/component_utils.css");

		addHtmlViewVariable(pp, getClass(), "comp");
	}
}
