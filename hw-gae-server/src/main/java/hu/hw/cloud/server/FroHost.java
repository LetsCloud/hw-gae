/**
 * 
 */
package hu.hw.cloud.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author CR
 *
 */
public class FroHost extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static final String APP_NAME = "HostWare FRO";

	static final String GWT_MODULE_NAME = "fro_module";

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer = response.getWriter();

		// writer.println("<!doctype html><html
		// manifest=\"/itbisman.manifest\"><head>");
		writer.println("<!doctype html><html><head>");

		writer.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
		writer.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");

		writer.println("<meta name=\"gwt:property\" content=\"locale=" + request.getLocale() + "\" />");

		writer.println("<style type=\"text/css\"></style>");

		writer.println("<title>" + APP_NAME + "</title>");

		writer.println("<script language=\"javascript\" " + "src=\"" + GWT_MODULE_NAME + "/" + GWT_MODULE_NAME
				+ ".nocache.js\"></script>");

		writer.println("</head><body>");

		writer.println("<iframe src=\"javascript:''\" id=\"__gwt_historyFrame\" "
				+ "style=\"position:absolute;width:0;height:0;border:0\"></iframe>");

		writer.println(
				"<noscript><div style=\"width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red;"
						+ " background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif\">"
						+ "Your web browser must have JavaScript enabled"
						+ "in order for this application to display correctly." + "</div></noscript>");

		writer.println("</body></html>");
	}
}