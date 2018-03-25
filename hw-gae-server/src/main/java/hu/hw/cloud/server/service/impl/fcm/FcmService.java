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
import java.util.logging.Logger;

import com.google.gson.JsonObject;

/**
 * @author CR
 *
 */
public class FcmService {
	private static final Logger log = Logger.getLogger(FcmService.class.getName());

//	private static final String HOST_URL = "https://fcm.googleapis.com/fcm/send";
	private static final String HOST_URL = "https://fcm.googleapis.com/v1/projects/hw-cloud2/messages:send";
	private static final String HEAD_AUTH = "Authorization";
	private static final String SERVER_KEY = "AAAAy-ElnS8:APA91bHMc1W-Ka2d2PiQSyKskKby2wWFrNAY93CM5s2dq1rDd4f4pTNTQJ13tF4A2SZWSNXwzpDhrBER_akIcIJI6RhQRp_lroKFoAdcyV-hDT3uWLhS2BkrvYG51yaw3ynd8MmTBhh0";
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


	/**
	 * 
	 * @param notification
	 * @param to
	 * @return
	 */
	protected String generatePayload(String iconUrl, String title, String body, String action, String to) {

		JsonObject message = new JsonObject();
		message.addProperty("icon", iconUrl);
		message.addProperty("title", title);
		message.addProperty("body", body);
		message.addProperty("click_action", action);

		JsonObject object = new JsonObject();
		object.add("notification", message);
		object.addProperty("to", to);

		return object.getAsString();
	}
}
