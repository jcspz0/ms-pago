package com.diplo.sharekernel.rule;

import com.diplo.sharekernel.core.IBussinesRule.IBussinessRule;

public class NotNullRule implements IBussinessRule {

	private Object _value;
	public String Message;

	public NotNullRule(Object _value) {
		this._value = _value;
		Message = "Object cannot be null";
	}

	public Object get_value() {
		return _value;
	}

	@Override
	public boolean IsValid() {
		return _value != null;
	}

	@Override
	public String GetMessage() {
		// TODO Auto-generated method stub
		return Message;
	}
}
