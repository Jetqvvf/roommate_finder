package domain.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import db.DbManager;
import domain.user.User;

public class ProfileDaoImpl implements ProfileDao{
	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();

	@Override
	public int modifyProfile(Profile file) {
		int status = 0;
		try{
			conn = db.getConnection();
			ps = conn.prepareStatement("select * from user_profile where userid=?");
			System.out.println(file.getUserid());
			ps.setInt(1, file.getUserid());
			ResultSet rs = ps.executeQuery();
			
			if(!rs.isBeforeFirst()) {
				ps = conn.prepareStatement("insert into user_profile(userid, department, phone_number, state, zip_code, lanaguage, number_of_mates,cooking, non_veg, smoking, drinking, track, city, pets, address) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setInt(1, file.getUserid());
				ps.setString(2, file.getDepartment());
				ps.setString(3, file.getPhoneNumber());
				ps.setString(4, file.getState());
				ps.setString(5, file.getZipCode());
				ps.setString(6, file.getLanguage());
				ps.setInt(7, file.getRoommateNumber());
				ps.setInt(8, file.getCooking());
				ps.setInt(9, file.getNonVeg());
				ps.setInt(10, file.getSmoking());
				ps.setInt(11, file.getDrinking());
				ps.setString(12, file.getTrack());
				ps.setString(13, file.getCity());
				ps.setInt(14, file.getPets());
				ps.setString(15, file.getAddress());
				
			} else {
				ps = conn.prepareStatement("update user_profile set department=?, phone_number=?, state=?, zip_code=?, lanaguage=?, number_of_mates=?, cooking=?, non_veg=?, smoking=?, drinking=?, track=?, city=?, pets=?, address=? where userid=?");
				ps.setString(1, file.getDepartment());
				ps.setString(2, file.getPhoneNumber());
				ps.setString(3, file.getState());
				ps.setString(4, file.getZipCode());
				ps.setString(5, file.getLanguage());
				ps.setInt(6, file.getRoommateNumber());
				ps.setInt(7, file.getCooking());
				ps.setInt(8, file.getNonVeg());
				ps.setInt(9, file.getSmoking());
				ps.setInt(10, file.getDrinking());
				ps.setString(11, file.getTrack());
				ps.setString(12, file.getCity());
				ps.setInt(13, file.getPets());
				ps.setString(14, file.getAddress());
				ps.setInt(15, file.getUserid());
			}
			
			ps.executeUpdate();
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return status;
	}
	
	@Override
	public Profile getProfile(int userid) {
		// TODO Auto-generated method stub
		Profile profile = new Profile();
		try{
			conn = db.getConnection();
			ps = conn.prepareStatement("select * from user_profile where userid=? limit 1");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				profile.setDepartment(rs.getString("department"));
				profile.setTrack(rs.getString("track"));
				profile.setPhoneNumber(rs.getString("phone_number"));
				profile.setState(rs.getString("state"));
				profile.setCity(rs.getString("city"));
				profile.setZipCode(rs.getString("zip_code"));
				profile.setLanguage(rs.getString("lanaguage"));
				profile.setRoommateNumber(rs.getInt("number_of_mates"));
				profile.setNonVeg(rs.getInt("non_veg"));
				profile.setSmoking(rs.getInt("smoking"));
				profile.setDrinking(rs.getInt("drinking"));
				profile.setPets(rs.getInt("pets"));
				profile.setLanguage(rs.getString("lanaguage"));
				profile.setCooking(rs.getInt("cooking"));
				profile.setAddress(rs.getString("address"));
				return profile;
			}
		} catch(Exception e){
			System.out.println(e);
		}
		
		return null;
	}
	
	public ArrayList display() {
		ArrayList<Profile> users = new ArrayList<Profile>();

		try{
			conn = db.getConnection();
			ps = conn.prepareStatement("select * from user_profile left join user on user_profile.userid=user.userid");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Profile c = new Profile();
				String name = rs.getString("first_name") + " " + rs.getString("last_name");
				System.out.print(name);
				c.setName(name);
				c.setCity(rs.getString("city"));
				c.setLanguage(rs.getString("lanaguage"));
				c.setTrack(rs.getString("track"));
				c.setDepartment(rs.getString("department"));
				users.add(c);
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		
		return users;
	}
}