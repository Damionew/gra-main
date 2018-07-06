package com.damionew.controller.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

/**
 * 通过前台获取经纬度转换为地址信息
 * 通过地址信息向天气接口查询天气
 * @author yinyunqi
 *
 */
@Controller
public class WeatherController {
	
	@ResponseBody
	@RequestMapping("weather/uploadLocation")
	public String locationTransform(HttpServletRequest request) throws IOException {
		// 经度
		String lng = request.getParameter("lng");
		// 纬度
		String lat = request.getParameter("lat");
		//调用百度地图转换接口-逆地址编码
		String url = "http://api.map.baidu.com/geocoder/v2/?location="+
		lat+","+lng+"&output=json&pois=1&ak=3ZxdK3XO9S51XPPOgTujzau5LhoTECll";
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		StringBuilder stringBuilder = new StringBuilder();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(5000);
		if (conn.getResponseCode() == 200) {
			InputStream inputStream = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String str;
			while((str = reader.readLine())!=null) {
				stringBuilder.append(str);
				System.out.println(str);
			}
		}
		/*
		 * renderReverse&&renderReverse({"status":0,"result":{"location":{"lng":118.77807440999993,"lat":32.05723552100348},
		 * "formatted_address":"江苏省南京市鼓楼区广州路189","business":"南师大,南大,上海路","addressComponent":
		 * {"country":"中国","country_code":0,"country_code_iso":"CHN","country_code_iso2":"CN","province":"江苏省","city":"南京市",
		 * "city_level":2,"district":"鼓楼区","town":"","adcode":"320106","street":"广州路","street_number":"189","direction":"附近",
		 * "distance":"5"}...................
		 * http://lbsyun.baidu.com/index.php?title=webapi/guide/webservice-geocoding-abroad
		 */
		String addString =  stringBuilder.toString();
//		JSONObject  jsonObject = JSONObject.parseObject(addString);
//		String[] address =addString.split("\\(");
//		String[] addressjson = address[1].split("\\)");
		JSONObject parseObject = JSONObject.parseObject(addString);
		JSONObject jsonResult = parseObject.getJSONObject("result");
		JSONObject addressComponentResult = jsonResult.getJSONObject("addressComponent");
		String city = addressComponentResult.getString("city");
//		JSON parseObject =  JSON.parse(addressJson);
//		String addressComponent = parseObject.getString("addressComponent");
		System.out.println(city);
		return "";
	}
}
