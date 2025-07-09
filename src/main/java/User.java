public class User {
	private int id;
	private String name;
	private String username;
	private String email;
	private Address address;
	private String phone;
	private String website;

	public User(int id, String name, Address address, String username, String email, String website, String phone) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.username = username;
		this.email = email;
		this.website = website;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public Address getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getWebsite() {
		return website;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", username='" + username + '\'' +
				", email='" + email + '\'' +
				", address=" + address +
				", phone='" + phone + '\'' +
				", website='" + website + '\'' +
				'}';
	}
}
