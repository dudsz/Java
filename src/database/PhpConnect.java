package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class PhpConnect {
	
	String username = "Sott";
	String username2 = "Sott";
    String pass = "salt";
    String email = "surt";
    String del = "del";
    
    URL url;
    HttpURLConnection conn;
    
	public PhpConnect() throws Exception {
	
		urlPost(username, pass, email);
		//urlLoginPost(username, pass);
		//urlDeletePost(del, username2);
		
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
}

/*Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

//for (int c; (c = in.read()) >= 0;)
String s = "";
	while ((s = in.readLine()) != null) {
        response += s;
      }
   // System.out.print((char)c);
} catch (Exception e) {
	System.out.println("Error: " + e);
}*/
