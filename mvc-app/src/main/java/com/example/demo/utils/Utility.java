package com.example.demo.utils;

import com.example.demo.entity.Order;

public class Utility {
    public static String pageQuery(int page, int pageSize) {
    	 return "?" + ("page=" + page) + "&" + ("pageSize=" + pageSize) ;
	}
}
