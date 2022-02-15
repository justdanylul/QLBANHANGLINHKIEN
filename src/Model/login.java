package Model;

public class login {
	public String id;
	public String passw;
	public login(String id, String passw) {
		super();
		this.id = id;
		this.passw = passw;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassw() {
		return passw;
	}
	public void setPassw(String passw) {
		this.passw = passw;
	}
	

}
