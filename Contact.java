import java.io.Serializable;
import javax.swing.ImageIcon;

public class Contact implements Serializable{
	private String lastname;
	private String firstname;
	private String number;
	private String photo;
	
	public Contact(String lastname, String firstname, String number) {
		this.lastname=lastname;
		this.firstname=firstname;
		this.number=number;
	}
	
	public Contact() {
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
