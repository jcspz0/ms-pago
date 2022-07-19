package com.diplo.sharekernel.core;

import com.diplo.sharekernel.core.IBussinesRule.IBussinessRule;

public abstract class ValueObject {

	protected void CheckRule(IBussinessRule rule)
		throws BussinessRuleValidationException {
		if (rule == null) {
			throw new IllegalArgumentException("Rule cannot be null");
		}
		if (!rule.IsValid()) {
			throw new BussinessRuleValidationException(rule);
		}
	}
}
