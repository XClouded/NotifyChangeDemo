package com.wpy.notifychangedemo;

import android.os.Handler;
import android.os.Message;

public class Downloader {

	private String fileName;
	private Handler handler;

	public Downloader(String fileName, Handler handler) {
		this.fileName = fileName;
		this.handler = handler;
	}

	public void download() {
		new MyThread().start();
	}

	private class MyThread extends Thread {
		@Override
		public void run() {
			super.run();
			for (int i = 0; i <= 100; i++) {
				System.out.println("i:" + i);
				try {
					this.currentThread().sleep(1 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message msg = Message.obtain();
				msg.what = 1;
				msg.obj = fileName;
				msg.arg1 = i;
				handler.sendMessage(msg);
			}
		}
	}
}
