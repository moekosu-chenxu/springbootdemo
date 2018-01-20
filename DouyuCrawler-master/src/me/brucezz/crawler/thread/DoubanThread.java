package me.brucezz.crawler.thread;

import java.util.List;

import me.brucezz.crawler.handler.ResponseParser;
import me.brucezz.crawler.util.HttpUtil;

public class DoubanThread implements Runnable{

	private final String REQ_URL = "https://www.douban.com/group/106955/discussion?start=50";
	
	public DoubanThread()
	{
		
	}
	
	@Override
	public void run()
	{
		System.out.println("抓取页面...");
		String page = HttpUtil.get(REQ_URL);
		
//		System.out.println(page);
		List<String> list = ResponseParser.parseDouban(page);
		
		for(String s : list)
			System.out.println(s);
	}
	
}
