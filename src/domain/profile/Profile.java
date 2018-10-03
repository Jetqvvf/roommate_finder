package domain.profile;

public class Profile {
	private int userid;
	private String phoneNumber;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String language;
	private int roommateNumber;
	private String department;
	private String track;
	private int cooking;
	private int nonVeg;
	private int drinking;
	private int smoking;
	private int pets;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getRoommateNumber() {
		return roommateNumber;
	}
	public void setRoommateNumber(int roommateNumber) {
		this.roommateNumber = roommateNumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	public int getCooking() {
		return cooking;
	}
	public void setCooking(int cooking) {
		this.cooking = cooking;
	}
	public int getDrinking() {
		return drinking;
	}
	public void setDrinking(int drinking) {
		this.drinking = drinking;
	}
	public int getNonVeg() {
		return nonVeg;
	}
	public void setNonVeg(int nonVeg) {
		this.nonVeg = nonVeg;
	}
	public int getSmoking() {
		return smoking;
	}
	public void setSmoking(int smoking) {
		this.smoking = smoking;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getPets() {
		return pets;
	}
	public void setPets(int pets) {
		this.pets = pets;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
