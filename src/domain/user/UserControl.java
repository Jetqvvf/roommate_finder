package domain.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Servlet implementation class UserControl
 */
@WebServlet("/UserControl")
public class UserControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		UserDao userDao = new UserDaoImpl();
		JSONObject jsonObj = new JSONObject();
		
		ArrayList<User> users = userDao.display();
		JSONArray ja = new JSONArray();
		for (User c : users) {
			JSONObject jo = new JSONObject();
			jo.put("first_name", c.getFirstName());
			jo.put("last_name", c.getLastName());
			jo.put("username", c.getUsername());
			jo.put("email", c.getEmail());
			ja.put(jo);
		}
		
		JSONObject mainObj = new JSONObject();
		mainObj.put("rows", ja);
		mainObj.put("total", 3);
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(mainObj.toString());
	}
}
