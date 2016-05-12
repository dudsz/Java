package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PhpConnect {
	
	String username = "Sott";
	String username2 = "Sott";
    String pass = "salt";
    String email = "surt";
    String del = "del";
    
    URL url;
    HttpURLConnection conn;
    
    
	public PhpConnect() throws Exception {
	
		//urlPost(username, pass, email);
		//addWish("markus", "Fodelsedag", "Jobb", "Pengar", "Sverige");
		//addWish("markus", "Jul", "Spenat", "Mat", "Overallt");
		//delWish("markus", "Jul", "Spenat");
		//getWish("markus", "Jul", "Spenat");
		//getLists("markus");
		//getWishList("markus", "Jul");
		//urlLoginPost(username, pass);
		//urlDeletePost(del, username2);
		getStocks();
		getStocksYQL(); // Seems less accurate
		
	}
	public void addWish (String un, String wl, String wn, String wd, String wpl) throws Exception {
		URL url = new URL("http://ec2-54-191-47-17.us-west-2.compute.amazonaws.com/test_wishes/addWish.php");
		
		// Set parameters to be sent
		Map<String,Object> params = new LinkedHashMap<>();
        params.put("un", un);
        params.put("wl", wl);
        params.put("wn", wn);
        params.put("wd", wd);
        params.put("wpl", wpl);
        
        // Build and encode parameters
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
				
        // Set properties of request
		conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.getOutputStream().write(postDataBytes);

		try {
			// Get response
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;

		    while ((line = reader.readLine()) != null) {
			        sb.append(line);
			        }
		    System.out.println("Insert: " + sb.toString());
		    String response = sb.toString();
		   
		    JSONParser p = new JSONParser();
		    JSONObject obj = (JSONObject) p.parse(response);
		    long ln = (long) obj.get("success");
		    
		    System.out.println("Test: " + obj);
		    System.out.println("Test: " + ln);
		    System.out.println("Got: " + obj.get("success") + "\n");
		    
		    
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	public void delWish (String un, String wl, String wn) throws Exception {
		URL url = new URL("http://ec2-54-191-47-17.us-west-2.compute.amazonaws.com/test_wishes/delWish.php");
		
		// Set parameters to be sent
		Map<String,Object> params = new LinkedHashMap<>();
		params.put("delete", del);
        params.put("un", un);
        params.put("wl", wl);
        params.put("wn", wn);
        
        // Build and encode parameters
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
				
        // Set properties of request
		conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.getOutputStream().write(postDataBytes);

		try {
			// Get response
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;

		    while ((line = reader.readLine()) != null) {
			        sb.append(line);
			        }
		    System.out.println("Delete: " + sb.toString());
		    String response = sb.toString();
		   
		    JSONParser p = new JSONParser();
		    JSONObject obj = (JSONObject) p.parse(response);
		    long ln = (long) obj.get("success");
		    
		    System.out.println("OBJ: " + obj);
		    System.out.println("Success: " + obj.get("success") + "\n");
		    
		    
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	public void getWish (String un, String wl, String wn) throws Exception {
		URL url = new URL("http://ec2-54-191-47-17.us-west-2.compute.amazonaws.com/test_wishes/getWish.php");
		
		// Set parameters to be sent
		Map<String,Object> params = new LinkedHashMap<>();
        params.put("un", un);
        params.put("wl", wl);
        params.put("wn", wn);
        
        // Build and encode parameters
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
				
        // Set properties of request
		conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.getOutputStream().write(postDataBytes);

		try {
			// Get response
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;

		    while ((line = reader.readLine()) != null) {
			        sb.append(line);
			        }
		    System.out.println("Retrieval: " + sb.toString());
		    String response = sb.toString();
		   
		    JSONParser p = new JSONParser();
		    JSONObject obj = (JSONObject) p.parse(response);
		    long ln = (long) obj.get("success");
		    
		    System.out.println("Test: " + obj);
		    System.out.println("Success: " + ln);
		    System.out.println("Got: " + obj.get("success") + "\n");
		    
		    
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}

	public void getLists (String un) throws Exception {
		URL url = new URL("http://ec2-54-191-47-17.us-west-2.compute.amazonaws.com/test_wishes/getLists.php");
		
		// Set parameters to be sent
		Map<String,Object> params = new LinkedHashMap<>();
        params.put("un", un);
               
        // Build and encode parameters
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
				
        // Set properties of request
		conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.getOutputStream().write(postDataBytes);

		try {
			// Get response
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;

		    while ((line = reader.readLine()) != null) {
			        sb.append(line);
			        }
		    System.out.println("Lists: " + sb.toString());
		    String response = sb.toString();
		   
		    JSONParser p = new JSONParser();
		    JSONObject obj = (JSONObject) p.parse(response);
		    long ln = (long) obj.get("success");
		    
		    System.out.println("JOBJ: " + obj);
		    System.out.println("Success: " + obj.get("success") + "\n");
		    
		    
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}

	public void getWishList (String un, String wl) throws Exception {
		URL url = new URL("http://ec2-54-191-47-17.us-west-2.compute.amazonaws.com/test_wishes/getWl.php");
		
		// Set parameters to be sent
		Map<String,Object> params = new LinkedHashMap<>();
        params.put("un", un);
        params.put("wl", wl);
        
        // Build and encode parameters
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
				
        // Set properties of request
		conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.getOutputStream().write(postDataBytes);

		try {
			// Get response
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;

		    while ((line = reader.readLine()) != null) {
			        sb.append(line);
			        }
		    System.out.println("Retrieval: " + sb.toString());
		    String response = sb.toString();
		   
		    JSONParser p = new JSONParser();
		    JSONObject obj = (JSONObject) p.parse(response);
		    long ln = (long) obj.get("success");
		    
		    System.out.println("Test: " + obj);
		    System.out.println("Success: " + ln);
		    System.out.println("Got: " + obj.get("success") + "\n");
		    
		    
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
		
	public void urlPost (String un, String pw, String email) throws Exception {
		URL url = new URL("http://ec2-54-191-47-17.us-west-2.compute.amazonaws.com/test_login/insert.php");
		
		// Set parameters to be sent
		Map<String,Object> params = new LinkedHashMap<>();
        params.put("un", un);
        params.put("pw", pw);
        params.put("email", email);
        
        // Build and encode parameters
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
				
        // Set properties of request
		conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.getOutputStream().write(postDataBytes);

		try {
			// Get response
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;

		    while ((line = reader.readLine()) != null) {
			        sb.append(line);
			        }
		    System.out.println("Insert: " + sb.toString());

		    String response = sb.toString();
		   
		    JSONParser p = new JSONParser();
		    JSONObject obj = (JSONObject) p.parse(response);
		    long ln = (long) obj.get("success");
		    
		    System.out.println("Test: " + obj);
		    System.out.println("Got: " + obj.get("success") + "\n");

		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	public void urlLoginPost (String un, String pw) throws Exception {
		URL url = new URL("http://ec2-54-191-47-17.us-west-2.compute.amazonaws.com/test_login/login.php");
		
		// Set parameters to be sent
		Map<String,Object> params = new LinkedHashMap<>();
        params.put("un", un);
		params.put("pw", pw);
        
        // Build and encode parameters
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
				
        // Set properties of request
		conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.getOutputStream().write(postDataBytes);

		try {
			// Get response
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;

		    while ((line = reader.readLine()) != null) {
			        sb.append(line);
			        }
		    System.out.println("Insert: " + sb.toString());
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	public void urlDeletePost (String command, String un) throws Exception {
		URL url = new URL("http://ec2-54-191-47-17.us-west-2.compute.amazonaws.com/test_login/delete.php");
		
		// Set parameters to be sent
		Map<String,Object> params = new LinkedHashMap<>();
        params.put("delete", del);
		params.put("un", un);
        
        // Build and encode parameters
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
				
        // Set properties of request
		conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.getOutputStream().write(postDataBytes);

		try {
			// Get response
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;

		    while ((line = reader.readLine()) != null) {
			        sb.append(line);
			        }
		    System.out.println("Insert: " + sb.toString());
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}

	public void getStocks () throws Exception {
		URL url1 = new URL("http://finance.yahoo.com/webservice/v1/symbols/allcurrencies/quote?format=json");
		URL url = new URL("http://finance.yahoo.com/webservice/v1/symbols/AAPL,YHOO/quote?format=json&view=detail");
		URL url2 = new URL("https://www.quandl.com/api/v3/datasets/WIKI/FB/data.json");
		// Symbol, Name, Ask, DaysLow, DaysHigh, YearLow, YearHigh, Currency
				
        // Set properties of request
		conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
		try {
			// Get response
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;

		    while ((line = reader.readLine()) != null) {
			        sb.append(line);
			        }
		    System.out.println("Lists: " + sb.toString());
		    String response = sb.toString();
		    String[] parts = new String[5];
		    
		    JSONParser p = new JSONParser();
		    JSONObject obj = (JSONObject) p.parse(response);
		    JSONObject li = (JSONObject) obj.get("list");
		    JSONArray resources = (JSONArray) li.get("resources");
		    // Ta båda
		    JSONObject res = (JSONObject) resources.get(0);
		    JSONObject result = (JSONObject) res.get("resource");	
		    JSONObject stock = (JSONObject) result.get("fields");	
		    
		    String name = (String) stock.get("issuer_name");
		    String code = (String) stock.get("symbol");
		    String yHigh = (String) stock.get("year_high");
		    String yLow = (String) stock.get("year_low");
		    String price = (String) stock.get("price");
		    parts[0] = code;
		    parts[1] = name;
		    parts[2] = yHigh;
		    parts[3] = yLow;
		    parts[4] = price;
		    
		    System.out.println("Code: " + parts[0] + ", Name: " + parts[1] + ", Year high: " + parts[2] 
		    		+ ", Year low: " + parts[3] + ", Current price: " + parts[4] + "\n");
		    
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
	public void getStocksYQL () throws Exception {
		URL url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20Symbol%2C%20Name%2C%20YearHigh%2C%20YearLow%2C%20DaysLow%2C%20DaysHigh%2C%20Ask%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22AAPL%22%2C%20%22YHOO%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
		// Symbol, Name, Ask, DaysLow, DaysHigh, YearLow, YearHigh, Currency
				
        // Set properties of request
		conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
		try {
			// Get response
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line = null;

		    while ((line = reader.readLine()) != null) {
			        sb.append(line);
			        }
		    System.out.println("Lists: " + sb.toString() + "\n");
		    String response = sb.toString();
		    String[] parts = new String[7];
		    
		    JSONParser p = new JSONParser();
		    JSONObject obj = (JSONObject) p.parse(response);
		    JSONObject li = (JSONObject) obj.get("query");
		    JSONObject res = (JSONObject) li.get("results");
		    JSONArray quote = (JSONArray) res.get("quote");
		    // Ta Båda
		    JSONObject stock = (JSONObject) quote.get(0);
		            
		    String name = (String) stock.get("Name"); 
		    String code = (String) stock.get("Symbol");
		    String yHigh = (String) stock.get("YearHigh");
		    String yLow = (String) stock.get("YearLow");
		    String dHigh = (String) stock.get("DaysHigh");
		    String dLow = (String) stock.get("DaysLow");
		    String price = (String) stock.get("Ask");
		    parts[0] = code;
		    parts[1] = name;
		    parts[2] = yHigh;
		    parts[3] = yLow;
		    parts[4] = dHigh;
		    parts[5] = dLow;
		    parts[6] = price;
		    
		    System.out.println("Code: " + parts[0] + ", Name: " + parts[1] + ", Year high: " + parts[2] 
		    		+ ", Year low: " + parts[3] + " \nDays high: " + parts[4] + ", Days low: " + parts[5] 
		    		+ ", Current price: " + parts[6]);
		    
		} catch (IOException e) { 
			e.printStackTrace(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		}
	}
	
}

