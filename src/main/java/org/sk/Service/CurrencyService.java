package org.sk.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.sk.Utiilty.Utilities;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

@SuppressWarnings("deprecation")
@Service
public class CurrencyService {
	
	private Utilities utilities = new Utilities();
	
	private String sourceCurrency = "USD";
	private String currencyExchangeJSON = null;
	private final int formatId = 1;
	private final String USER_AGENT = "Mozilla/5.0";
	private final String access_key="2942b2f7aca6b4d0aa7ffee6";
	String baseCurrency = "EUR";
	//String currencyURL = "http://www.apilayer.net/api/live?access_key=13c82e032c3d03cd97ab12d35c467d42&format=1";
	String currencyURL = "https://v3.exchangerate-api.com/bulk/"+access_key+"/";
	
	public String getCurrencyURL() {
		return currencyURL;
	}
	Utilities util = new Utilities();
	public void setCurrencyURL(String currencyURL) {
		this.currencyURL = currencyURL;
	}

	public static void main(String args[]) {
		CurrencyService cs = new CurrencyService();
	//	cs.getJSONOutputFromURL(cs.getCurrencyURL());
		cs.getCurrentCurrenyRates("EUR");
	}
			
		@RequestMapping("currency")
		private String getJSONOutputFromURL(String url){
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(url);
			StringBuffer result = new StringBuffer();
			// add request header
			request.addHeader("User-Agent", USER_AGENT);

			HttpResponse response;
			try {
				response = client.execute(request);
			
		//	System.out.println("\nSending 'GET' request to URL : " + url);
		//	System.out.println("Response Code : " +
	               //        response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(
	                       new InputStreamReader(response.getEntity().getContent()));

			
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}

	//		System.out.println(result.toString());
			
			} catch (IOException e) {				
				e.printStackTrace();
			}		
		//	System.out.println(utilities.getFormattedJSON(result.toString()));
			return result.toString();
		}
		
		public String getCurrencyResult(String baseCurrency) {
		//	String currencyURL = "http://www.apilayer.net/api/live?access_key=13c82e032c3d03cd97ab12d35c467d42&format=1";
			
			String currencyConversionJSON = getJSONOutputFromURL(currencyURL+baseCurrency);
			return currencyConversionJSON;
		}
		
		public LinkedTreeMap<String,Double> getCurrentCurrenyRates(String baseCurrency){
			LinkedTreeMap<String, Double> currencyMap = new LinkedTreeMap<String, Double>();		
		//	String outputJSON = getJSONOutputFromURL(currencyURL);
			String outputJSON = getCurrencyResult(baseCurrency);
			TreeMap jsonTreeMap  = utilities.jsonToMap(outputJSON);
			currencyMap = utilities.getCurrencyMap(jsonTreeMap);
			System.out.println(currencyMap);
			return currencyMap;
		}
}