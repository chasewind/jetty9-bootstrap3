package com.belief.test;

import java.util.ArrayList;
import java.util.List;

public interface ActiveUserListener {
	List<String> records = new ArrayList<String>();;
	public void saveInRecord();
}