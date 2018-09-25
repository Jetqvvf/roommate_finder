package domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DbManager;
import domain.user.Login;
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
			
			ps =conn.prepareStatement("insert into user(username, password, first_name, last_name, email_id) values(?,?,?,?,?)");
			ps.setString(1, c.getUsername());
			ps.setString(2, c.getPassword());
			ps.setString(3, c.getFirstName());
			ps.setString(4, c.getLastName());
			ps.setString(5, c.getEmail());
			ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return status;
	}
	
	@Override
	public User validateUser(Login login) {
		User c = new User();
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("select * from user where username=? and password=?");
			ps.setString(1, login.getUsername());
			ps.setString(2, login.getPassword());

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				c.setUsername(rs.getString(1));
				c.setPassword(rs.getString(2));
				c.setFirstName(rs.getString(3));
				c.setLastName(rs.getString(4));
				c.setEmail(rs.getString(5));
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return c;
	}
}
