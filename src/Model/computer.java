package Model;

public class computer {
	public String Ma_hang;
	public String Ten;
	public String Hang;
	public double Gia_moi;
	public double Gia_cu;
	public double So_luong;
	
	public computer() {
		
	}

	public computer(String ma_hang, String ten, String hang, double gia_moi, double gia_cu, double so_luong) {
		super();
		Ma_hang = ma_hang;
		Ten = ten;
		Hang = hang;
		Gia_moi = gia_moi;
		Gia_cu = gia_cu;
		So_luong = so_luong;
	}

	public String getMa_hang() {
		return Ma_hang;
	}

	public void setMa_hang(String ma_hang) {
		Ma_hang = ma_hang;
	}

	public String getTen() {
		return Ten;
	}

	public void setTen(String ten) {
		Ten = ten;
	}

	public String getHang() {
		return Hang;
	}

	public void setHang(String hang) {
		Hang = hang;
	}

	public double getGia_moi() {
		return Gia_moi;
	}

	public void setGia_moi(double gia_moi) {
		Gia_moi = gia_moi;
	}

	public double getGia_cu() {
		return Gia_cu;
	}

	public void setGia_cu(double gia_cu) {
		Gia_cu = gia_cu;
	}

	public double getSo_luong() {
		return So_luong;
	}

	public void setSo_luong(double so_luong) {
		So_luong = so_luong;
	}
	

}
