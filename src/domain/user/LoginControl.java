package domain.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class LoginControl extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControl() {
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {      
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        JSONObject jsonObj = new JSONObject(new String(data));
        
        UserDao userDao = new UserDaoImpl();
        String type = jsonObj.getString("submit");
		if (type.equals("register")) {
			User c = new User();
			c.setUsername(jsonObj.getString("username"));
			c.setPassword(jsonObj.getString("password"));
			c.setFirstName(jsonObj.getString("first_name"));
			c.setLastName(jsonObj.getString("last_name"));
			c.setEmail(jsonObj.getString("email"));	
			c.setPriority(0);
			
			int status = userDao.register(c);
			
			JSONObject json = new JSONObject();
			if (0 == status) {
				HttpSession session = request.getSession();
				session.setAttribute("username", c.getUsername());
				session.setAttribute("password", c.getPassword());
				session.setAttribute("priority", c.getPriority());
				session.setAttribute("userid", c.getUserId());
				session.setAttribute("first_name", c.getFirstName());
				session.setAttribute("last_name", c.getLastName());
				
				Cookie cookie = new Cookie("username", c.getUsername());
		        Cookie cookie2 = new Cookie("password", c.getPassword());
		        Cookie cookie3 = new Cookie("priority", String.valueOf(c.getPriority()));
		        Cookie cookie4 = new Cookie("userid", String.valueOf((c.getUserId())));
		        response.addCookie(cookie);
		        response.addCookie(cookie2);
		        response.addCookie(cookie3);
		        response.addCookie(cookie4);
		        
				json.put("error", 0);
				json.put("msg", "success");
			} else if (1 == status) {
				json.put("error", 1);
				json.put("msg", "The username has been used");
			} else if (2 == status) {
				json.put("error", 2);
				json.put("msg", "The email has been used");
			}
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "nocache");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(json.toString());
		} else if (type.equals("login")) {
			User c = new User();
			c.setUsername(jsonObj.getString("username"));
			c.setPassword(jsonObj.getString("password"));
			
			int status = userDao.login(c);
			JSONObject json = new JSONObject();
			
			if (0 == status) {
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(20*60); //20 minutes
				session.setAttribute("username", c.getUsername());
				session.setAttribute("password", c.getPassword());
				session.setAttribute("priority", c.getPriority());
				session.setAttribute("userid", c.getUserId());
				session.setAttribute("first_name", c.getFirstName());
				session.setAttribute("last_name", c.getLastName());
				
				Cookie cookie = new Cookie("username", c.getUsername());
		        Cookie cookie2 = new Cookie("password", c.getPassword());
		        Cookie cookie3 = new Cookie("priority", String.valueOf(c.getPriority()));
		        Cookie cookie4 = new Cookie("userid", String.valueOf((c.getUserId())));
		        response.addCookie(cookie);
		        response.addCookie(cookie2);
		        response.addCookie(cookie3);
		        response.addCookie(cookie4);
		        
		        if (c.getPriority() == 0) {
		        	json.put("index", "profile.jsp");
		        } else {
		        	json.put("index", "admin/user_management.jsp");
		        }

		        json.put("error", 0);
				json.put("msg", "success");
			} else {
				json.put("error", 1);
				json.put("msg", "The username or password is error");
			}
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "nocache");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(json.toString());
		} else if (type.equals("logout")) {
			HttpSession session = request.getSession(false);

	        if(session != null){
	            session.invalidate();
	        }

	        JSONObject json = new JSONObject();
	        json.put("error", 0);
	        response.setContentType("application/json");
			response.setHeader("Cache-Control", "nocache");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(json.toString());
		}
	}
}
