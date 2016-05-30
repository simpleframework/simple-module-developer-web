package net.simpleframework.app.developer;

import net.simpleframework.ctx.AbstractModuleContext;
import net.simpleframework.ctx.Module;
import net.simpleframework.ctx.ModuleFunctions;
import net.simpleframework.mvc.ctx.WebModuleFunction;

public class DeveloperWebContext extends AbstractModuleContext implements IDeveloperContext {

	@Override
	protected Module createModule() {
		return super.createModule().setName(MODULE_NAME).setText("developer").setOrder(34);
	}

	@Override
	protected ModuleFunctions getFunctions() {
		return ModuleFunctions.of(new WebModuleFunction(this, DeveloperPage.class).setName(
				MODULE_NAME + "-DeveloperPage").setText("开发者"));
	}
}
