package com.belief.test;

public class OnDutyUser extends AbstractActiveUserHandler {

	@Override
	public void saveInRecord() {
		records.add("OnDutyUser");
	}

}
