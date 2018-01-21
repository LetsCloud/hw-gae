/**
 * 
 */
package hu.hw.cloud.server.service.impl.fcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Logger;

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

	public void sendMessage2(String json) throws IOException {
		log.info("sendMessage2()-START-json=" + json);

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

				StringBuffer res = new StringBuffer();
				String line;

				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					res.append(line);
				}
				reader.close();

				// JSONObject jsonObj = new JSONObject(res);

				log.info("sendMessage2()-> " + res);
			} else {
				log.info("sendMessage2()-" + connection.getResponseCode());
			}
		} catch (IOException e) {
			log.info("sendMessage2()-IOException-" + e.toString());
		}
	}
}
