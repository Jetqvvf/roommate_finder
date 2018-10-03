package domain.admin;

import domain.user.User;
import domain.user.UserDao;
import domain.user.UserDaoImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/search_user")
public class AdminController extends HttpServlet {
    public AdminController() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String types = request.getParameter("type");

        if(types.equals("upload")){
            String[] uploadInfo = new String[5];
            uploadInfo[0] = request.getParameter("row_id");
            uploadInfo[1] = request.getParameter("row_name");
            uploadInfo[2] = request.getParameter("row_firstname");
            uploadInfo[3] = request.getParameter("row_lastname");
            uploadInfo[4] = request.getParameter("row_email");

            UserDao userDao = new UserDaoImpl();
            userDao.uploadUser(uploadInfo);
        }
        else{
            String username = request.getParameter("input");
            UserDao userDao = new UserDaoImpl();
            ArrayList<User> users = userDao.searchUser(username);


            JSONArray jsonArray = new JSONArray();
            if (users.size() != 0) {
                for(User user : users) {
                    JSONObject json = new JSONObject();
                    json.put("id", user.getUserId());
                    json.put("name", user.getUsername());
                    json.put("first name", user.getFirstName());
                    json.put("last name", user.getLastName());
                    json.put("email",user.getEmail());
                    jsonArray.put(json);
                }
            }
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "nocache");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print(jsonArray.toString());
            }
        }
}
