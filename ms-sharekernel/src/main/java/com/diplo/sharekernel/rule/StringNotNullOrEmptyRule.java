package com.diplo.sharekernel.rule;

import com.diplo.sharekernel.core.IBussinesRule.IBussinessRule;

public class StringNotNullOrEmptyRule implements IBussinessRule {

	private String _value;
	public String Message;

	public StringNotNullOrEmptyRule(String _value) {
		this._value = _value;
		Message = "string cannot be null";
	}

	public Object get_value() {
		return _value;
	}

	@Override
	public boolean IsValid() {
		return (_value.isEmpty() || _value == null);
	}

	@Override
	public String GetMessage() {
		return Message;
	}
}
