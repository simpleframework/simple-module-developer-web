package net.simpleframework.app.developer.base.htmlEditor;

import net.simpleframework.app.developer.AbstractComponentPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.component.ext.ckeditor.HtmlEditorBean;

public class HtmlEditorPage extends AbstractComponentPage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		addComponentBean(pp, "HtmlEditorPage_editor", HtmlEditorBean.class).setStartupFocus(false)
				.setResizeEnabled(true).setContainerId("HtmlEditorPage_editor").setHeight("400px");
	}
}
