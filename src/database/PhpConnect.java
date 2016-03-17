package database;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class PhpConnect {
	
	String un = "detsaasta";
    String pw = "gar";
    String email = "gryasadsamt";
    
    URL url;
    HttpURLConnection conn;
    
	public PhpConnect() throws Exception {
	
		urlPost();
		
	}
		
	public void urlPost () throws Exception {
		//URL url = new URL("http://ec2-54-191-47-17.us-west-2.compute.amazonaws.com/test_login/bla.php");
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

		// Get response
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        for (int c; (c = in.read()) >= 0;)
            System.out.print((char)c);
		}
	}
