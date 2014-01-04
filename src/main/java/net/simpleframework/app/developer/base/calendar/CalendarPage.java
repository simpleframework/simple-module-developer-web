package net.simpleframework.app.developer.base.calendar;

import net.simpleframework.app.developer.AbstractComponentPage;
import net.simpleframework.mvc.PageParameter;
import net.simpleframework.mvc.common.element.CalendarInput;
import net.simpleframework.mvc.common.element.ElementList;
import net.simpleframework.mvc.common.element.ParagraphElement;
import net.simpleframework.mvc.component.base.validation.EValidatorMethod;
import net.simpleframework.mvc.component.base.validation.Validator;
import net.simpleframework.mvc.component.ui.calendar.CalendarBean;

public class CalendarPage extends AbstractComponentPage {

	@Override
	protected void onForward(final PageParameter pp) {
		super.onForward(pp);

		addComponentBean(pp, "testCalendar", CalendarBean.class);

		addValidationBean(pp, "CalendarPage_validation").setTriggerSelector(
				".ComponentPage input[type=button]").addValidators(
				new Validator(EValidatorMethod.date_less_than, "#idCalendarField")
						.setArgs("#idCalendarField2, yyyy/MM/dd"));
	}

	public String calInput(final PageParameter pp) {
		return ElementList.of(
				new CalendarInput("idCalendarField").setCalendarComponent("testCalendar")
						.setDateFormat("yyyy/MM/dd"),
				new ParagraphElement(),
				new CalendarInput("idCalendarField2").setCalendarComponent("testCalendar")
						.setDateFormat("yyyy/MM/dd")).toString();
	}
}
