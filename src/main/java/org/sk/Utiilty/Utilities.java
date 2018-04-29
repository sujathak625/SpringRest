package org.sk.Utiilty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

public class Utilities {
	public String getFormattedJSON(String inputJSONString) {
		JsonParser parser = new JsonParser();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = null;
		JsonElement el = parser.parse(inputJSONString);
		jsonString = gson.toJson(el);
		return jsonString;
	}
	
	public TreeMap jsonToMap(String inputJSON) {
		TreeMap resultMap = new TreeMap();
		Gson gson = new Gson(); 
		resultMap = gson.fromJson(inputJSON, resultMap.getClass());
		return resultMap;
	}
	
	public LinkedTreeMap<String,String> getCurrencyMap(TreeMap<String,String> currencyDetailsMap) {
		LinkedTreeMap<String, String> currency = new LinkedTreeMap<String, String>();
		for (Entry<String, String> entry : currencyDetailsMap.entrySet()) {
			Object key = entry.getKey();
			Object value = currencyDetailsMap.get(key);
			if (value instanceof Map) {
				currency= (LinkedTreeMap<String, String>) value;
				/*
				 * for (Entry<String,String> entry1 : currency.entrySet()) { Object key1 =
				 * entry1.getKey(); Object value1 = currency.get(key1);
				 * //System.out.println(" Key1: "+key1+" Value1 : "+value1); }
				 */
			}
		}
        return currency;
	}
	
	public LinkedTreeMap<String,Double> getCurrentCurrenyRates(){
		LinkedTreeMap<String, Double> currencyMap = new LinkedTreeMap<String, Double>();		
	//	String outputJSON = getJSONOutputFromURL(currencyURL);
		String outputJSON = currencyExchange2();
		TreeMap jsonTreeMap  = jsonToMap(outputJSON);
		currencyMap = getCurrencyMap(jsonTreeMap);
		return currencyMap;
	}
	
	private String currencyExchange2() {
		return "{\r\n" + 
				"  \"result\": \"success\",\r\n" + 
				"  \"timestamp\": 1523877851,\r\n" + 
				"  \"from\": \"EUR\",\r\n" + 
				"  \"rates\": {\r\n" + 
				"    \"EUR\": 1,\r\n" + 
				"    \"AED\": 4.5367623,\r\n" + 
				"    \"ALL\": 128.88063946,\r\n" + 
				"    \"AMD\": 596.68523562,\r\n" + 
				"    \"ANG\": 2.20160792,\r\n" + 
				"    \"AOA\": 268.67409648,\r\n" + 
				"    \"ARS\": 24.95521814,\r\n" + 
				"    \"AUD\": 1.58718746,\r\n" + 
				"    \"AZN\": 2.09418061,\r\n" + 
				"    \"BBD\": 2.48737771,\r\n" + 
				"    \"BDT\": 103.12851829,\r\n" + 
				"    \"BGN\": 1.95883463,\r\n" + 
				"    \"BHD\": 0.46562037,\r\n" + 
				"    \"BRL\": 4.21219095,\r\n" + 
				"    \"BSD\": 1.23525319,\r\n" + 
				"    \"BWP\": 11.89165186,\r\n" + 
				"    \"BYN\": 2.46932837,\r\n" + 
				"    \"CAD\": 1.5550057,\r\n" + 
				"    \"CHF\": 1.18692911,\r\n" + 
				"    \"CLP\": 735.29154057,\r\n" + 
				"    \"CNY\": 7.74806343,\r\n" + 
				"    \"COP\": 3349.85505675,\r\n" + 
				"    \"CZK\": 25.29719633,\r\n" + 
				"    \"DKK\": 7.44815848,\r\n" + 
				"    \"DOP\": 60.96251835,\r\n" + 
				"    \"EGP\": 21.77714782,\r\n" + 
				"    \"ETB\": 33.82221273,\r\n" + 
				"    \"FJD\": 2.48608534,\r\n" + 
				"    \"GBP\": 0.86469378,\r\n" + 
				"    \"GEL\": 2.97450788,\r\n" + 
				"    \"GHS\": 5.47005931,\r\n" + 
				"    \"GTQ\": 9.09945345,\r\n" + 
				"    \"HKD\": 9.68787822,\r\n" + 
				"    \"HNL\": 29.16284654,\r\n" + 
				"    \"HRK\": 7.42607572,\r\n" + 
				"    \"HUF\": 310.73144326,\r\n" + 
				"    \"IDR\": 16971.52793811,\r\n" + 
				"    \"ILS\": 4.33412023,\r\n" + 
				"    \"INR\": 80.61615197,\r\n" + 
				"    \"IQD\": 1464.44032158,\r\n" + 
				"    \"IRR\": 51948.05194805,\r\n" + 
				"    \"ISK\": 121.88389053,\r\n" + 
				"    \"JMD\": 153.40500148,\r\n" + 
				"    \"JOD\": 0.87553637,\r\n" + 
				"    \"JPY\": 132.53897579,\r\n" + 
				"    \"KES\": 124.16689839,\r\n" + 
				"    \"KHR\": 4952.87557452,\r\n" + 
				"    \"KRW\": 1321.95433513,\r\n" + 
				"    \"KWD\": 0.3703879,\r\n" + 
				"    \"KZT\": 407.41591324,\r\n" + 
				"    \"LAK\": 10236.23995053,\r\n" + 
				"    \"LBP\": 1864.29442317,\r\n" + 
				"    \"LKR\": 192.45889952,\r\n" + 
				"    \"MAD\": 11.33248269,\r\n" + 
				"    \"MDL\": 20.19878109,\r\n" + 
				"    \"MKD\": 61.41229622,\r\n" + 
				"    \"MMK\": 1631.41620284,\r\n" + 
				"    \"MUR\": 40.0989462,\r\n" + 
				"    \"MXN\": 22.30352057,\r\n" + 
				"    \"MYR\": 4.78981115,\r\n" + 
				"    \"NAD\": 14.89902836,\r\n" + 
				"    \"NGN\": 443.89832581,\r\n" + 
				"    \"NOK\": 9.58565239,\r\n" + 
				"    \"NZD\": 1.67838164,\r\n" + 
				"    \"OMR\": 0.47538996,\r\n" + 
				"    \"PAB\": 1.23525319,\r\n" + 
				"    \"PEN\": 3.97786431,\r\n" + 
				"    \"PGK\": 4.0191713,\r\n" + 
				"    \"PHP\": 64.19928901,\r\n" + 
				"    \"PKR\": 142.89204659,\r\n" + 
				"    \"PLN\": 4.16784567,\r\n" + 
				"    \"PYG\": 6832.10292747,\r\n" + 
				"    \"QAR\": 4.50683859,\r\n" + 
				"    \"RON\": 4.65681258,\r\n" + 
				"    \"RSD\": 118.12599644,\r\n" + 
				"    \"RUB\": 76.62582682,\r\n" + 
				"    \"SAR\": 4.62844866,\r\n" + 
				"    \"SCR\": 16.73692697,\r\n" + 
				"    \"SEK\": 10.4076094,\r\n" + 
				"    \"SGD\": 1.61844795,\r\n" + 
				"    \"THB\": 38.4779092,\r\n" + 
				"    \"TJS\": 10.94038343,\r\n" + 
				"    \"TND\": 2.96430342,\r\n" + 
				"    \"TRY\": 5.05590918,\r\n" + 
				"    \"TTD\": 8.27452949,\r\n" + 
				"    \"TWD\": 36.23960391,\r\n" + 
				"    \"TZS\": 2790.78385865,\r\n" + 
				"    \"UAH\": 32.24823755,\r\n" + 
				"    \"USD\": 1.23414239,\r\n" + 
				"    \"UYU\": 34.95391397,\r\n" + 
				"    \"UZS\": 9987.6314162,\r\n" + 
				"    \"VEF\": 61028.50955432,\r\n" + 
				"    \"VND\": 28162.05956628,\r\n" + 
				"    \"XAF\": 666.54751852,\r\n" + 
				"    \"XCD\": 3.34108268,\r\n" + 
				"    \"XOF\": 662.07461219,\r\n" + 
				"    \"XPF\": 118.54050216,\r\n" + 
				"    \"ZAR\": 14.89023527,\r\n" + 
				"    \"ZMW\": 11.73822082\r\n" + 
				"  }\r\n" + 
				"}\r\n" + 
				"";
	}
	
	private String currencyExchange1() {
		return  "{\r\n" + 
				"  \"success\":true,\r\n" + 
				"  \"terms\":\"https:\\/\\/currencylayer.com\\/terms\",\r\n" + 
				"  \"privacy\":\"https:\\/\\/currencylayer.com\\/privacy\",\r\n" + 
				"  \"timestamp\":1512140354,\r\n" + 
				"  \"source\":\"USD\",\r\n" + 
				"  \"quotes\":{\r\n" + 
				"    \"USDAED\":3.672496,\r\n" + 
				"    \"USDAFN\":68.599998,\r\n" + 
				"    \"USDALL\":112.269997,\r\n" + 
				"    \"USDAMD\":483.709991,\r\n" + 
				"    \"USDANG\":1.780173,\r\n" + 
				"    \"USDAOA\":165.098007,\r\n" + 
				"    \"USDARS\":17.239857,\r\n" + 
				"    \"USDAUD\":1.316975,\r\n" + 
				"    \"USDAWG\":1.78,\r\n" + 
				"    \"USDAZN\":1.699602,\r\n" + 
				"    \"USDBAM\":1.6501,\r\n" + 
				"    \"USDBBD\":2,\r\n" + 
				"    \"USDBDT\":82.239998,\r\n" + 
				"    \"USDBGN\":1.650603,\r\n" + 
				"    \"USDBHD\":0.37725,\r\n" + 
				"    \"USDBIF\":1749.060059,\r\n" + 
				"    \"USDBMD\":1,\r\n" + 
				"    \"USDBND\":1.346019,\r\n" + 
				"    \"USDBOB\":6.859724,\r\n" + 
				"    \"USDBRL\":3.261901,\r\n" + 
				"    \"USDBSD\":1,\r\n" + 
				"    \"USDBTC\":9.5e-5,\r\n" + 
				"    \"USDBTN\":64.503463,\r\n" + 
				"    \"USDBWP\":10.336298,\r\n" + 
				"    \"USDBYN\":2.009774,\r\n" + 
				"    \"USDBYR\":19600,\r\n" + 
				"    \"USDBZD\":1.997798,\r\n" + 
				"    \"USDCAD\":1.27461,\r\n" + 
				"    \"USDCDF\":1565.501257,\r\n" + 
				"    \"USDCHF\":0.98579,\r\n" + 
				"    \"USDCLF\":0.02414,\r\n" + 
				"    \"USDCLP\":651.72998,\r\n" + 
				"    \"USDCNY\":6.6103,\r\n" + 
				"    \"USDCOP\":3008.699951,\r\n" + 
				"    \"USDCRC\":561.999862,\r\n" + 
				"    \"USDCUC\":1,\r\n" + 
				"    \"USDCUP\":26.5,\r\n" + 
				"    \"USDCVE\":92.929931,\r\n" + 
				"    \"USDCZK\":21.525204,\r\n" + 
				"    \"USDDJF\":176.830002,\r\n" + 
				"    \"USDDKK\":6.271471,\r\n" + 
				"    \"USDDOP\":48.179658,\r\n" + 
				"    \"USDDZD\":114.736988,\r\n" + 
				"    \"USDEGP\":17.650478,\r\n" + 
				"    \"USDERN\":14.989809,\r\n" + 
				"    \"USDETB\":27.049999,\r\n" + 
				"    \"USDEUR\":0.842499,\r\n" + 
				"    \"USDFJD\":2.092496,\r\n" + 
				"    \"USDFKP\":0.740305,\r\n" + 
				"    \"USDGBP\":0.74275,\r\n" + 
				"    \"USDGEL\":2.709302,\r\n" + 
				"    \"USDGGP\":0.742772,\r\n" + 
				"    \"USDGHS\":4.463501,\r\n" + 
				"    \"USDGIP\":0.740497,\r\n" + 
				"    \"USDGMD\":47.049999,\r\n" + 
				"    \"USDGNF\":9005.000256,\r\n" + 
				"    \"USDGTQ\":7.339504,\r\n" + 
				"    \"USDGYD\":202.710007,\r\n" + 
				"    \"USDHKD\":7.809301,\r\n" + 
				"    \"USDHNL\":23.485001,\r\n" + 
				"    \"USDHRK\":6.360266,\r\n" + 
				"    \"USDHTG\":62.529999,\r\n" + 
				"    \"USDHUF\":264.070007,\r\n" + 
				"    \"USDIDR\":13524,\r\n" + 
				"    \"USDILS\":3.484299,\r\n" + 
				"    \"USDIMP\":0.742772,\r\n" + 
				"    \"USDINR\":64.539803,\r\n" + 
				"    \"USDIQD\":1183,\r\n" + 
				"    \"USDIRR\":35265.000208,\r\n" + 
				"    \"USDISK\":102.949997,\r\n" + 
				"    \"USDJEP\":0.742772,\r\n" + 
				"    \"USDJMD\":124.800003,\r\n" + 
				"    \"USDJOD\":0.707497,\r\n" + 
				"    \"USDJPY\":112.662003,\r\n" + 
				"    \"USDKES\":102.800003,\r\n" + 
				"    \"USDKGS\":69.716003,\r\n" + 
				"    \"USDKHR\":4027.000238,\r\n" + 
				"    \"USDKMF\":411.149994,\r\n" + 
				"    \"USDKPW\":899.999972,\r\n" + 
				"    \"USDKRW\":1083.25013,\r\n" + 
				"    \"USDKWD\":0.301495,\r\n" + 
				"    \"USDKYD\":0.820265,\r\n" + 
				"    \"USDKZT\":331.149994,\r\n" + 
				"    \"USDLAK\":8295.999889,\r\n" + 
				"    \"USDLBP\":1510.999819,\r\n" + 
				"    \"USDLKR\":153.550003,\r\n" + 
				"    \"USDLRD\":125.160004,\r\n" + 
				"    \"USDLSL\":13.700987,\r\n" + 
				"    \"USDLTL\":3.048697,\r\n" + 
				"    \"USDLVL\":0.62055,\r\n" + 
				"    \"USDLYD\":1.360299,\r\n" + 
				"    \"USDMAD\":9.403801,\r\n" + 
				"    \"USDMDL\":17.135004,\r\n" + 
				"    \"USDMGA\":3185.000257,\r\n" + 
				"    \"USDMKD\":51.700001,\r\n" + 
				"    \"USDMMK\":1361.000388,\r\n" + 
				"    \"USDMNT\":2437.999677,\r\n" + 
				"    \"USDMOP\":8.0448,\r\n" + 
				"    \"USDMRO\":351.410004,\r\n" + 
				"    \"USDMUR\":33.400002,\r\n" + 
				"    \"USDMVR\":15.569724,\r\n" + 
				"    \"USDMWK\":713.450012,\r\n" + 
				"    \"USDMXN\":18.7171,\r\n" + 
				"    \"USDMYR\":4.087501,\r\n" + 
				"    \"USDMZN\":60.299999,\r\n" + 
				"    \"USDNAD\":13.694034,\r\n" + 
				"    \"USDNGN\":357.000406,\r\n" + 
				"    \"USDNIO\":30.667199,\r\n" + 
				"    \"USDNOK\":8.27929,\r\n" + 
				"    \"USDNPR\":103.550003,\r\n" + 
				"    \"USDNZD\":1.455602,\r\n" + 
				"    \"USDOMR\":0.3847,\r\n" + 
				"    \"USDPAB\":1,\r\n" + 
				"    \"USDPEN\":3.231013,\r\n" + 
				"    \"USDPGK\":3.207799,\r\n" + 
				"    \"USDPHP\":50.319768,\r\n" + 
				"    \"USDPKR\":105.179952,\r\n" + 
				"    \"USDPLN\":3.5428,\r\n" + 
				"    \"USDPYG\":5652.799805,\r\n" + 
				"    \"USDQAR\":3.640301,\r\n" + 
				"    \"USDRON\":3.897896,\r\n" + 
				"    \"USDRSD\":99.676302,\r\n" + 
				"    \"USDRUB\":58.553299,\r\n" + 
				"    \"USDRWF\":834.919983,\r\n" + 
				"    \"USDSAR\":3.750255,\r\n" + 
				"    \"USDSBD\":7.855982,\r\n" + 
				"    \"USDSCR\":13.350095,\r\n" + 
				"    \"USDSDG\":6.659701,\r\n" + 
				"    \"USDSEK\":8.36678,\r\n" + 
				"    \"USDSGD\":1.34669,\r\n" + 
				"    \"USDSHP\":0.740497,\r\n" + 
				"    \"USDSLL\":7629.999939,\r\n" + 
				"    \"USDSOS\":559.000032,\r\n" + 
				"    \"USDSRD\":7.410632,\r\n" + 
				"    \"USDSTD\":20648.900391,\r\n" + 
				"    \"USDSVC\":8.750138,\r\n" + 
				"    \"USDSYP\":514.97998,\r\n" + 
				"    \"USDSZL\":13.693952,\r\n" + 
				"    \"USDTHB\":32.630001,\r\n" + 
				"    \"USDTJS\":8.819102,\r\n" + 
				"    \"USDTMT\":3.4,\r\n" + 
				"    \"USDTND\":2.472696,\r\n" + 
				"    \"USDTOP\":2.289002,\r\n" + 
				"    \"USDTRY\":3.929802,\r\n" + 
				"    \"USDTTD\":6.629499,\r\n" + 
				"    \"USDTWD\":30.021999,\r\n" + 
				"    \"USDTZS\":2234.000164,\r\n" + 
				"    \"USDUAH\":27.070313,\r\n" + 
				"    \"USDUGX\":3626.99992,\r\n" + 
				"    \"USDUSD\":1,\r\n" + 
				"    \"USDUYU\":28.959999,\r\n" + 
				"    \"USDUZS\":8090.000161,\r\n" + 
				"    \"USDVEF\":9.975028,\r\n" + 
				"    \"USDVND\":22709,\r\n" + 
				"    \"USDVUV\":106.480003,\r\n" + 
				"    \"USDWST\":2.577499,\r\n" + 
				"    \"USDXAF\":552.150024,\r\n" + 
				"    \"USDXAG\":0.061108,\r\n" + 
				"    \"USDXAU\":0.000784,\r\n" + 
				"    \"USDXCD\":2.704108,\r\n" + 
				"    \"USDXDR\":0.70491,\r\n" + 
				"    \"USDXOF\":575.609985,\r\n" + 
				"    \"USDXPF\":100.750237,\r\n" + 
				"    \"USDYER\":249.929993,\r\n" + 
				"    \"USDZAR\":13.6964,\r\n" + 
				"    \"USDZMK\":9001.197853,\r\n" + 
				"    \"USDZMW\":10.1099,\r\n" + 
				"    \"USDZWL\":322.355011\r\n" + 
				"  }\r\n" + 
				"}";
	}

}
