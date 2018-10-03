package domain.profile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import domain.user.User;
import domain.user.UserDao;
import domain.user.UserDaoImpl;

/**
 * Servlet implementation class ProfileControl
 */
@WebServlet("/ProfileControl")
public class ProfileControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProfileDao profileDao = new ProfileDaoImpl();
		ArrayList<Profile> users = profileDao.display();
		JSONArray ja = new JSONArray();
		for (Profile c : users) {
			JSONObject jo = new JSONObject();
			jo.put("name", c.getName());
			jo.put("department", c.getDepartment());
			jo.put("track", c.getTrack());
			jo.put("language", c.getLanguage());
			jo.put("city", c.getCity());
			ja.put(jo);
		}
		
		JSONObject mainObj = new JSONObject();
		mainObj.put("rows", ja);
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "nocache");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.print(mainObj.toString());
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
        ProfileDao profileDao = new ProfileDaoImpl();
        String type = jsonObj.getString("type");
        
		if (type.equals("modify")) {
			Profile profile = new Profile();
			HttpSession session = request.getSession(false);
			int id = -1;
			
			if (null != session) {
				id = Integer.parseInt(session.getAttribute("userid").toString());
			}

			profile.setDepartment(jsonObj.getString("department"));
			profile.setAddress(jsonObj.getString("address"));
			profile.setUserid(id);
			profile.setTrack(jsonObj.getString("track"));
			profile.setPhoneNumber(jsonObj.getString("phone_number"));
			profile.setState(jsonObj.getString("state"));
			profile.setCity(jsonObj.getString("city"));
			profile.setZipCode(jsonObj.getString("zip_code"));
			profile.setLanguage(jsonObj.getString("language"));
			profile.setRoommateNumber(Integer.parseInt(jsonObj.getString("roommate_number")));
			profile.setNonVeg(jsonObj.getInt("non_veg"));
			profile.setSmoking(jsonObj.getInt("smoking"));
			profile.setDrinking(jsonObj.getInt("drinking"));
			profile.setPets(jsonObj.getInt("pets"));
			profile.setCooking(jsonObj.getInt("cooking"));
			int status = profileDao.modifyProfile(profile);
			JSONObject json = new JSONObject();

			if (0 == status) {
				json.put("error", 0);
				json.put("msg", "success");
			} else if (1 == status) {
				json.put("error", 1);
				json.put("msg", "Modify error");
			} 
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "nocache");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(json.toString());
		} else if (type.equals("getprofile")){
			HttpSession session = request.getSession(false);
			int id = -1;
			
			if (null != session) {
				id = Integer.parseInt(session.getAttribute("userid").toString());
			}

			Profile profile = profileDao.getProfile(id);
			JSONObject json = new JSONObject();

			if (null == profile) {
				json.put("error", 1);
			} else {
				json.put("error", 0);
				json.put("department", profile.getDepartment());
				json.put("track", profile.getTrack());
				json.put("language", profile.getLanguage());
				json.put("phone_number", profile.getPhoneNumber());
				json.put("department", profile.getDepartment());
				json.put("state", profile.getState());
				json.put("city", profile.getCity());
				json.put("zip_code", profile.getZipCode());
				json.put("number_of_mates", profile.getRoommateNumber());
				json.put("non_veg", profile.getNonVeg());
				json.put("smoking", profile.getSmoking());
				json.put("drinking", profile.getDrinking());
				json.put("cooking", profile.getCooking());
				json.put("pets", profile.getPets());
				json.put("address", profile.getAddress());
			}

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "nocache");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(json.toString());
		} 
	}
}
