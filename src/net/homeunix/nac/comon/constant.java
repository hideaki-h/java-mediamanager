package net.homeunix.nac.common;

import java.text.SimpleDateFormat;
import java.util.*;

public final class constant {
	public final static Map<String, Integer> day = new HashMap<String, Integer>();
	public static constant c = new constant();

	private constant() {
		constant.day.put("1年後",	365);
		constant.day.put("6ヶ月後",	183);
		constant.day.put("3ヶ月後",	93);
		constant.day.put("2ヶ月後",	62);
		constant.day.put("1ヶ月後",	31);
		constant.day.put("3週間後",	21);
		constant.day.put("2週間後",	14);
		constant.day.put("1週間後",	7);
		constant.day.put("6日後",	6);
		constant.day.put("5日後",	5);
		constant.day.put("4日後",	4);
		constant.day.put("3日後",	3);
		constant.day.put("明後日",	2);
		constant.day.put("明日",	1);
		constant.day.put("今日",	0);
		constant.day.put("昨日",	-1);
		constant.day.put("一昨日",	-2);
		constant.day.put("3日前",	-3);
		constant.day.put("4日前",	-4);
		constant.day.put("5日前",	-5);
		constant.day.put("6日前",	-6);
		constant.day.put("1週間前",	-7);
		constant.day.put("2週間前",	-14);
		constant.day.put("3週間前",	-21);
		constant.day.put("1ヶ月前",	-31);
		constant.day.put("2ヶ月前",	-62);
		constant.day.put("3ヶ月前",	-93);
		constant.day.put("6ヶ月前",	-183);
		constant.day.put("1年前",	-365);
	}

	public final static SimpleDateFormat mmdd = new SimpleDateFormat("MM/dd");
	public final static SimpleDateFormat mmddyyyy = new SimpleDateFormat("MM/dd/yyyy");
	public final static SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
	public final static SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat("yyyyMMddhhmmss");
}
