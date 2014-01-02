package net.simpleframework.app.developer;

import net.simpleframework.ctx.AbstractModuleContext;
import net.simpleframework.ctx.Module;
import net.simpleframework.ctx.ModuleFunction;
import net.simpleframework.ctx.ModuleFunctions;
import net.simpleframework.mvc.ctx.WebModuleFunction;

public class DeveloperWebContext extends AbstractModuleContext implements IDeveloperContext {

	@Override
	protected Module createModule() {
		return new Module().setName(MODULE_NAME).setText("developer").setOrder(34)
				.setDefaultFunction(FUNC_DEVELOPER);
	}

	@Override
	protected ModuleFunctions getFunctions() {
		return ModuleFunctions.of(FUNC_DEVELOPER);
	}

	public static final ModuleFunction FUNC_DEVELOPER = new WebModuleFunction(DeveloperPage.class)
			.setName(MODULE_NAME + "-DeveloperPage").setText("开发者");
}
