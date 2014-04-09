package net.simpleframework.app.developer;

import net.simpleframework.ctx.IModuleContext;
import net.simpleframework.mvc.IMVCContextVar;

public interface IDeveloperContext extends IModuleContext, IMVCContextVar {

	static String MODULE_NAME = "simple-module-developer";
}
