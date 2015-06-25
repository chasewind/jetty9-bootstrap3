package com.belief.test;

public class ForwardUser extends AbstractActiveUserHandler {

	@Override
	public void saveInRecord() {
		records.add("ForwardUser");
	}

}
