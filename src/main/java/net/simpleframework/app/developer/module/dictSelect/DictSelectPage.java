package net.simpleframework.app.developer.module.dictSelect;

import net.simpleframework.app.developer.AbstractComponentPage;
import net.simpleframework.module.dict.web.component.dictSelect.DictListSelectBean;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.DictMultiSelectInput;

public class DictSelectPage extends AbstractComponentPage {

	@Override
	protected void addComponents(final PageParameter pp) {
		super.addComponents(pp);

		//
		//
		addComponentBean(pp, "dictSelect", DictListSelectBean.class).setDictName("common.sex")
				.setBindingText("idDictSelectText").setBindingId("idDictSelectId")
				.setDestroyOnClose(true);

		// addComponentBean(pp, "listbox",
		// ListboxBean.class).setCheckbox(true).addListItem(
		// new ListItem(null, "aaa"));
		// addComponentBean(pp, "dictSelect",
		// DictionaryBean.class).addListboxRef(pp,
		// "listbox").setDestroyOnClose(true);
	}

	public String dictInput(final PageParameter pp) {
		// return new DictInput("idDictField").setDictComponent("dictSelect")
		// .addDictNameParameter("common.china.province").setHiddenField("idHiddenField")
		// .toString();
		// return new TextButton().setOnclick("alert('1');").toString();
		return new DictMultiSelectInput("idDictField").addValue("650000", "搜索")
				.addValue("540000", "bbb").setDictComponent("dictSelect")
				.addParameter("dictName", "common.china.province").toString();
	}
}
