package com.hampushallkvist.biometunes;

public class BiomeTunesLogger {
	private StringReceiver logReceiver;
	private StringReceiver errorReceiver;


	public BiomeTunesLogger(StringReceiver logReceiver, StringReceiver errorReceiver) {
		this.logReceiver = logReceiver;
		this.errorReceiver = errorReceiver;
	}

	public void log(String info) {
		logReceiver.receiveString(info);
	}

	public void error(String error) {
		errorReceiver.receiveString(error);
	}

	public static interface StringReceiver {
		public void receiveString(String string);
	}
}
