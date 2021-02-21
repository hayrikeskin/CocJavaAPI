import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClanServlet extends HttpServlet {	
	String bearer;
	
	/*
	 * @Override public void init(ServletConfig servletConfig) throws
	 * ServletException{ super(servletConfig); this.bearer =
	 * servletConfig.getInitParameter("CoCBearer"); }
	 */	
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		String pathInfo = request.getPathInfo().substring(1);
	    URL url = new URL("https://api.clashofclans.com/v1/clans/" + URLEncoder.encode(pathInfo, StandardCharsets.UTF_8.toString()));	    
        URLConnection urlc = url.openConnection();
        this.bearer = getServletContext().getInitParameter("CoCBearer");
        String bearerAuth = "Bearer " + bearer;
        urlc.setRequestProperty ("Authorization", bearerAuth);
        urlc.setRequestProperty("Content-Type","application/json");

        BufferedReader in = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
 		 
        out.print(in.readLine());
        out.flush();
        in.close();
	}	
}