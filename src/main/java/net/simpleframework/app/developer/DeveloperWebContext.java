package net.simpleframework.app.developer;

import net.simpleframework.ctx.AbstractModuleContext;
import net.simpleframework.ctx.Module;
import net.simpleframework.mvc.ctx.WebModuleFunction;

public class DeveloperWebContext extends AbstractModuleContext {

	@Override
	protected Module createModule() {
		return new Module()
				.setName("simple-module-developer")
				.setText("developer")
				.setOrder(10)
				.setDefaultFunction(
						new WebModuleFunction(DeveloperPage.class).setName(
								"simple-module-developer-DeveloperPage").setText("开发者")).setOrder(34);
	}
}
