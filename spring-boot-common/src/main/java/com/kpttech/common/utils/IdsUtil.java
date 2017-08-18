package com.kpttech.common.utils;

import java.util.ArrayList;
import java.util.List;

public class IdsUtil {
	public static List<Object> getListData(String ids)//将ids中的字符串转为List<object>
	{
		List<Object> list=new ArrayList<Object>();
		if(!ids.equals(""))
		{
			String[] sz=ids.split(",");
			for(String data:sz)
			{
				list.add(data);
			}
		}
		return list;
	}
}
