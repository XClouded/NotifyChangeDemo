package com.wpy.notifychangedemo.entity;

/**
 * 
 * ��Ŀ���ƣ�NotifyChangeDemo �����ƣ�FileState �������� �ļ���ʵ���� �����ˣ�wpy ����ʱ�䣺2014-9-11
 * ����10:47:26
 * 
 */
public class FileState {
	String fileName;// �ļ�����
	int completeSize;// ������ɵĳ���
	boolean state;// �ļ�״̬��trueΪ������ɣ�falseΪδ���

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getCompleteSize() {
		return completeSize;
	}

	public void setCompleteSize(int completeSize) {
		this.completeSize = completeSize;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

}
