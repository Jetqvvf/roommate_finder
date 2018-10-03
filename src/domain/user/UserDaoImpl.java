package domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Statement;

import db.DbManager;
import domain.user.User;

public class UserDaoImpl implements UserDao{

	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();
	
	@Override
	public int register(User c) {
		int status = 0;
		try{
			conn = db.getConnection();
			ps = conn.prepareStatement("select * from user where username=?");
			ps.setString(1, c.getUsername());
			ResultSet rs = ps.executeQuery();
			if(rs.isBeforeFirst()) {
				status = 1;
				conn.close();
				return status;
			}
			
			ps = conn.prepareStatement("select * from user where email_id=?");
			ps.setString(1, c.getEmail());
			rs = ps.executeQuery();
			if(rs.isBeforeFirst()) {
				status = 2;
				conn.close();
				return status;
			}
			//modified by QiaoWang @20180929
			ps =conn.prepareStatement("insert into user(username, password, first_name, last_name, email_id, priority) values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, c.getUsername());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getFirstName());
			ps.setString(4, c.getLastName());
			ps.setString(5, c.getEmail());
			ps.setInt(6, c.getPriority());
			ps.executeUpdate();
			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                c.setUserId(generatedKeys.getInt(1));
	                System.out.println(generatedKeys.getInt(1));
	            }
	            else {
	                throw new Exception("Creating user failed, no ID obtained.");
	            }
	        }
			
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return status;
	}
	
	@Override
	public int login(User c) {
		int status = 0;
		
		try{
			conn = db.getConnection();
			ps = conn.prepareStatement("select * from user where username=? and password=? limit 1");
			ps.setString(1, c.getUsername());
			ps.setString(2, c.getPassword());
			ResultSet rs = ps.executeQuery();
			if(!rs.isBeforeFirst()) {
				status = 1;
				conn.close();
				return status;
			} 
			if (rs.next()) {
				c.setPriority(rs.getInt("priority"));
				c.setUserId(rs.getInt("userid"));
				c.setFirstName(rs.getString("first_name"));
				c.setLastName(rs.getString("last_name"));
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		
		return status;
	}
	
	public ArrayList display() {
		ArrayList<User> users = new ArrayList<User>();
		try{
			conn = db.getConnection();
			ps = conn.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User c = new User();
				c.setFirstName(rs.getString("first_name"));
				c.setLastName(rs.getString("last_name"));
				c.setUsername(rs.getString("username"));
				c.setEmail(rs.getString("email_id"));
				users.add(c);
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		
		return users;
	}
	
	@Override
	public ArrayList<User> searchUser(String input) {
		ArrayList<User> users = new ArrayList<>();

		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("select * from user where username=?");
			ps.setString(1, input);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				User c = new User();
				c.setUserId(rs.getInt(1));
				c.setUsername(rs.getString(2));
				c.setPassword(rs.getString(3));
				c.setFirstName(rs.getString(4));
				c.setLastName(rs.getString(5));
				c.setEmail(rs.getString(6));
				users.add(c);
			}

			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return users;
	}
	@Override
	public void uploadUser(String[] input){
		ArrayList<User> users = searchUser(input[1]);
		Connection conn;
		PreparedStatement ps;
		DbManager db = new DbManager();
		if(users.size() == 0) {
			try {
				conn = db.getConnection();
				ps = conn.prepareStatement("insert into user(username, first_name, last_name, email_id, priority) values (?, ?, ?, ?, 0);");
				ps.setString(1, input[1]);
				ps.setString(2, input[2]);
				ps.setString(3, input[3]);
				ps.setString(4, input[4]);
				ps.executeUpdate();
				conn.close();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		else {
			try {
				conn = db.getConnection();
				ps = conn.prepareStatement("update user set username=?, first_name=?, last_name=?, email_id=? where userid=?");
				ps.setString(1, input[1]);
				ps.setString(2, input[2]);
				ps.setString(3, input[3]);
				ps.setString(4, input[4]);
				ps.setString(5, input[0]);
				ps.executeUpdate();
				conn.close();
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}
