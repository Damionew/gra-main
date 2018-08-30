package com.damionew.controller.weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.damionew.service.LoginHistoryService;

import io.swagger.annotations.ApiOperation;

/**
 * 通过前台获取经纬度转换为地址信息
 * 通过地址信息向天气接口查询天气
 * @author yinyunqi
 *
 */
@Controller
public class WeatherController {
	@Autowired
	LoginHistoryService loginHistoryService;
	
	@ApiOperation(value = "获取天气信息",notes ="通过和风天气接口获取并处理天气信息")
	@ResponseBody
	@RequestMapping(value = "weather/uploadLocation",method = RequestMethod.GET)
	public String locationTransform(HttpServletRequest request) throws IOException {
		// 经度
		String lng = request.getParameter("lng");
		// 纬度
		String lat = request.getParameter("lat");
		// 登录成功，将用户登录信息插入历史表中
		Map<String, String> loginHistory = new HashMap<String, String>();
		loginHistory.put("lng", lng);
		loginHistory.put("lat", lat);
		loginHistoryService.insertLoginHistory(loginHistory);
		// 调用百度地图转换接口-逆地址编码
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
//				System.out.println(str);
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
		JSONObject parseObject = JSONObject.parseObject(addString);
		JSONObject jsonResult = parseObject.getJSONObject("result");
		// 获取城市信息
		JSONObject addressComponentResult = jsonResult.getJSONObject("addressComponent");
		String city = addressComponentResult.getString("city");
		// 根据城市调用天气接口获取天气数据
		String weatherUrl = "https://free-api.heweather.com/s6/weather/now?location="+city+
		"&key=61b63321f9fa455fa04cc92881cc0a52";
		StringBuilder weatherBuilder = new StringBuilder();
		URL weatherU = new URL(weatherUrl);
		HttpURLConnection weatherCon = (HttpURLConnection) weatherU.openConnection();
		weatherCon.setRequestMethod("GET");
		weatherCon.setReadTimeout(5000);
		weatherCon.setConnectTimeout(5000);
		if (weatherCon.getResponseCode() == 200) {
			InputStream weatherIn = weatherCon.getInputStream();
			BufferedReader weatherReader = new BufferedReader(new InputStreamReader(weatherIn));
			String weatherStr = "";
			while ((weatherStr = weatherReader.readLine()) != null) {
				weatherBuilder.append(weatherStr);
//				System.out.println(weatherStr);
			}
		}
		String weather = weatherBuilder.toString();
		weather = weather.substring(15,weather.length()-2);
		JSONObject weatherObject = JSONObject.parseObject(weather);
		// 实时天气
		JSONObject now = weatherObject.getJSONObject("now");
		// 温度
		String tmp = now.getString("tmp");
		// 风力
		String wind_spd = now.getString("wind_spd");
		// 云气
		String cond_txt = now.getString("cond_txt");
		JSONObject weatherResult = new JSONObject();
		weatherResult.put("tmp",tmp);
		weatherResult.put("wind_spd",wind_spd);
		weatherResult.put("cond_txt",cond_txt);
		weatherResult.put("city", city);
		return weatherResult.toJSONString();
	}
}
