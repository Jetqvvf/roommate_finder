package domain.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.user.User;
import domain.user.UserDao;
import domain.user.UserDaoImpl;

import org.json.JSONObject;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
    	
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
			
			int status = userDao.register(c);
			JSONObject json = new JSONObject();
			if (0 == status) {
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
		}
		
	}

}
