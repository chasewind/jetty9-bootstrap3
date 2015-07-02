package com.belief;

public class Singlton {
	private static class SingletonHolder {
		private static final Singlton INSTANCE = new Singlton();
	}

	private Singlton() {
	}

	public static final Singlton getInstance() {
		return SingletonHolder.INSTANCE;
	}

}
