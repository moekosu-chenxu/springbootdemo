package com.moekosu.constant;

import java.util.List;

public class DouyuResp {

	private String error;

	private List<DouyuRoom> data;

	private List<DouyuBarrage> danmu;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<DouyuRoom> getData() {
		return data;
	}

	public void setData(List<DouyuRoom> data) {
		this.data = data;
	}

	public List<DouyuBarrage> getDanmu() {
		return danmu;
	}

	public void setDanmu(List<DouyuBarrage> danmu) {
		this.danmu = danmu;
	}

	public static DouyuResp parseResp(Object object)
	{
		DouyuResp resp = new DouyuResp();
		return resp;
	}

	@Override
	public String toString()
	{
		return "error="+ this.error + "data length="+ data.size()+ "danmu length="+ danmu.size();
	}
}
