package basketDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import basketDBConn.BasketDBConn;
import basketVO.BasketVO;



public class BasketDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public BasketDAO() throws ClassNotFoundException, SQLException {
		con = new BasketDBConn().getConnection();
	}

	public void pstmtClose() throws SQLException {
		if(pstmt != null) {
			pstmt.close();
		}
	}

	public void getAllInfoClose() throws SQLException {
		if(rs != null) {
			rs.close();
		}
		if(pstmt != null) {
			pstmt.close();
		}
		if(con != null) {
			con.close();
		}
	}					
	//��ٱ��Ͽ� ���� �Ŀ� ��ٱ��� ȭ�鿡 ���� ��ǰ ��ϵ�
	public ArrayList<BasketVO> getAllCart(String user_id1) throws SQLException{
		ArrayList<BasketVO> ciarray = new ArrayList<>();
		String sql = "SELECT * FROM ShopCart1 where user_id = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user_id1);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			int cart_id = rs.getInt("cart_id");
			String user_id = rs.getString("user_id");
			String p_image = rs.getString("p_image");
			String p_name = rs.getString("p_name");
			int price = rs.getInt("price");
			int cnt = rs.getInt("cnt");
			int one_total = rs.getInt("one_total");

			BasketVO cv = new BasketVO(cart_id, user_id,p_image, p_name, price,cnt,one_total);

			ciarray.add(cv);
		}

		return ciarray;

	}	
	int amount = 0;				
	//String user_id//�α����� user_id�� �ֹ��� ��ǰ�� �̹���, ��ǰ��, ����, ���� DB�� insert
	/*public boolean insert_cart(String user_id,String p_image, String p_name,int price,int cnt) throws SQLException {
		String sql = "INSERT into ShopCart1 values(?,?,?,?,?)";
	try {											//(?,?,?,?,?)
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user_id);
		pstmt.setString(2, p_image);
		pstmt.setString(3, p_name);
		pstmt.setInt(4, price);
		pstmt.setInt(5, cnt);
		//amount = cvo.getPrice() * cvo.getCnt();
		//pstmt.setInt(6, amount);

		pstmt.executeUpdate();
	}catch(SQLException e) {
		System.out.println("insert Exception");
		e.printStackTrace();
		return false;
	}
		return true;
}//insert-end
	 */								//int cart_id,
	public boolean insert_cart(String user_id,String p_image, String p_name,int price,int cnt,int one_total) throws SQLException {
		String sql = "INSERT into ShopCart1 values(aaa.nextval,?,?,?,?,?,?)";
		try {										//cart_id�� �������� �༭ ��ٱ��Ͽ� ���� ��ǰ �������� �ٸ� ��ȣ�� �ο�.
			pstmt = con.prepareStatement(sql);
			//pstmt.setInt(1, cart_id);
			pstmt.setString(1, user_id);
			pstmt.setString(2, p_image);
			pstmt.setString(3, p_name);
			pstmt.setInt(4, price);
			pstmt.setInt(5, cnt);
			pstmt.setInt(6, one_total);

			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("insert Exception");
			e.printStackTrace();
			return false;
		}
		return true;
	}//insert-end
	
	
	public boolean totalPrice_cart(String user_id,int all_total) throws SQLException {
		String sql = "INSERT into totalPrice values(?,?)";
		try {										//cart_id�� �������� �༭ ��ٱ��Ͽ� ���� ��ǰ �������� �ٸ� ��ȣ�� �ο�.
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, all_total);

			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("insert2 Exception");
			e.printStackTrace();
			return false;
		}
		return true;
	}//insert-end


	//�α����� user_id�� �ֹ��� ��ǰ�� ����, �Ѱ��� ǰ�� ���� �հ� ����.
	public boolean update_cart(int cnt, int one_total, String user_id, int price ) throws SQLException {

		String sql ="update ShopCart1 set cnt =?, one_total = ? where user_id = ? and price= ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cnt);
		pstmt.setInt(2, one_total);
		pstmt.setString(3, user_id);
		pstmt.setInt(4, price);
		pstmt.executeUpdate();

		return true;
	}
	//�α����� user_id�� �ֹ��� ��ǰ�� ���� ��ٱ��� ��ü ����.
	public boolean deleteAll_cart(String user_id){
		String sql = "delete from ShopCart1 where user_id =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.executeUpdate();/////////////

		}catch(SQLException e) {
			System.out.println("delete Exception");
			e.printStackTrace();
			return false;
		}
		return true;
	}//delete-end

						//cart_id�� ��ٱ��� ���� ����.
	public boolean one_delete_cart(int cart_id){
		String sql = "delete from ShopCart1 where cart_id =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart_id);
			pstmt.executeUpdate();/////////////

		}catch(SQLException e) {
			System.out.println("one_delete Exception");
			e.printStackTrace();
			return false;
		}
		return true;
	}//delete-end
	//���� �̸��� ��ǰ�� �� insert�� ���, ������ ����ǰ�� �հ踸 update�ǵ���
	public boolean cntUpdate_cart(int cnt,int one_total,String p_name) throws SQLException {
		String sql ="update ShopCart1 set cnt =?, one_total = ? where p_name= ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, cnt);
		pstmt.setInt(2, one_total);
		pstmt.setString(3, p_name);
		pstmt.executeUpdate();

		return true;
	}

}

