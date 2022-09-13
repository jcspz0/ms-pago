package com.diplo.sharedkernel.core;

import com.diplo.sharedkernel.core.IBussinesRule.IBussinessRule;

public class BussinessRuleValidationException extends Exception {

	private IBussinessRule BrokenRule;
	private String Details;

	public BussinessRuleValidationException(IBussinessRule brokenRule) {
		BrokenRule = brokenRule;
		Details = brokenRule.GetMessage();
	}

	public IBussinessRule GetBrokenRule() {
		return null;
	}

	private void SetBrokenRule(IBussinessRule bussinessRule) {
		this.BrokenRule = bussinessRule;
	}

	public String GetDetails() {
		return null;
	}

	private void SetDetails(String details) {
		this.Details = details;
	}

	@Override
	public String toString() {
		String name = BrokenRule == null
			? "BussinessRule"
			: BrokenRule.getClass().getName();
		return (
			"BussinessRuleValidationException [BrokenRule=" +
			BrokenRule +
			", Details=" +
			Details +
			"]"
		);
	}
}
