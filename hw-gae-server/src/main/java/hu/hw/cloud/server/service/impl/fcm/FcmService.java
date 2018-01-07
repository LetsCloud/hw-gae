/**
 * 
 */
package hu.hw.cloud.server.service.impl.fcm;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author CR
 *
 */
public class FcmService {
	private static final Logger log = Logger.getLogger(FcmService.class.getName());

	private static final String HOST_URL = "https://fcm.googleapis.com/fcm/send";
	private static final String HEAD_AUTH = "Authorization";
	private static final String SERVER_KEY = "AAAAcfahceM:APA91bHfSngEdCERlvp6BYLxbMOuEmxj24KLpgKPuI0JohSme7UFhoZvkC9lQK5Js_isouGNvcvomwnHpH5H3uhu-95F9-4eY_W7Vk2iCU69MbfD-DA7K2as_69_TcpPv7zi2HTeXPVJ";
	// private static final String SERVER_KEY =
	// "AIzaSyCXBYYVwGlX49iDmE-DSXzJn4Mu6uYnEO0";
	private static final String HEAD_TYPE = "Content-Type";
	private static final String APP_JSON = "application/json";

	public void sendMessage(String json) throws ClientProtocolException, IOException {
		log.info("sendMessage()-START-json=" + json);
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(HOST_URL);

		StringEntity entity = new StringEntity(json);
		httpPost.setEntity(entity);
		httpPost.setHeader(HEAD_AUTH, "key=" + SERVER_KEY);
		httpPost.setHeader(HEAD_TYPE, APP_JSON);

		CloseableHttpResponse response = client.execute(httpPost);
		log.info("sendMessage()-END->" + response.getStatusLine());
		client.close();
	}

	public void sendMessage2(String json) throws ClientProtocolException, IOException {
		log.info("sendMessage2()-START-json=" + json);

		String message = URLEncoder.encode(json, "UTF-8");

		try {
			URL url = new URL(HOST_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty(HEAD_AUTH, "key=" + SERVER_KEY);
			connection.setRequestProperty(HEAD_TYPE, APP_JSON);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");

			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(json);
			writer.close();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				log.info("sendMessage2()-OK");
			} else {
				log.info("sendMessage2()-" + connection.getResponseCode());
				log.info("sendMessage2()-" + connection.getResponseMessage());
			}
		} catch (MalformedURLException e) {
			log.info("sendMessage2()-MalformedURLException-" + e.toString());
		} catch (IOException e) {
			log.info("sendMessage2()-IOException-" + e.toString());
		}
	}
}
