/*
 * Project POO Smartphone
 * Author: Coline Fardel
 * Date creation: 06.05.2019
 * Date last modification: 14.05.2019
 */
import java.io.Serializable;
import javax.swing.ImageIcon;

public class Contact implements Serializable{
	private String lastname;
	private String firstname;
	private String number;
	private String photo="Images//contactnull.png";
	private String note;
	
	public Contact(String lastname, String firstname, String number) {
		this.lastname=lastname;
		this.firstname=firstname;
		this.number=number;
	}
	
	public Contact() {
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
