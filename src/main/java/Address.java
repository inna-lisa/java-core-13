import java.util.Map;

public class Address {
	private String street;
	private String suite;
	private String sity;
	private String zipcode;

	public Address(String street, String suite, String sity, String zipcode) {
		this.street = street;
		this.suite = suite;
		this.sity = sity;
		this.zipcode = zipcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getSity() {
		return sity;
	}

	public void setSity(String sity) {
		this.sity = sity;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Address{" +
				"street='" + street + '\'' +
				", suite='" + suite + '\'' +
				", sity='" + sity + '\'' +
				", zipcode='" + zipcode + '\'' +
				'}';
	}
}
