package i.valeriytimakov.usersegmentation.web;

import java.beans.PropertyEditorSupport;

import i.valeriytimakov.usersegmentation.model.UserType;

public class UserTypeEnumConverter extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		UserType userType = UserType.valueOf(text.toUpperCase());
		setValue(userType);
	}

}
