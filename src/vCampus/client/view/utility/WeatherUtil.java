package vCampus.client.view.utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class WeatherUtil {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String cityCode = "101190101";
		ArrayList<String> soup = getWeatherInfo("http://www.weather.com.cn/weather/" + cityCode + ".shtml");
		System.out.println(soup);    
	}
 
	public static ArrayList<String> getWeatherInfo(String urlInfo) {
		URL url;
		try {
			url = new URL(urlInfo);
			HttpURLConnection httpUrl;
			try {
				httpUrl = (HttpURLConnection) url.openConnection();
				InputStream is = httpUrl.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
				String line;
				ArrayList<String> res = new ArrayList<String>();
				
				while ((line = br.readLine()) != null) {
					if(line.contains("class=\"wea\"")){
						if(Pattern.matches(".*[\u4E00-\u9FA5]+.*", line)){
							//Pattern pat = Pattern.compile("<p.*>(.*)</p>");
							Pattern pat = Pattern.compile("<p.*>([\u4E00-\u9FA5]+)</p>");
							Matcher mat = pat.matcher(line);
							if(mat.find())res.add(mat.group(1));							
						}
					}
				}

				is.close();
				br.close();
				return res;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}