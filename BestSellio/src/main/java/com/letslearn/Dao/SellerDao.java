package com.letslearn.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.letslearn.Modal.Seller;

public class SellerDao {
	private Connection con;
	private String query;
    private PreparedStatement pst;
    private ResultSet rs;

	public SellerDao(Connection con) {
		this.con = con;
	}
	
	public int addSeller(Seller seller) {
		int result = 0;
  		try {
  			query="INSERT INTO seller (name,email,nic,city,grade,tel)\r\n"
  					+ "VALUES (?,?,?,?,?,?);";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,  seller.getName());
            pst.setString(2, seller.getEmail());
            pst.setString(3, seller.getNic());
            pst.setString(4, seller.getCity());
            pst.setString(5, seller.getGrade());
            pst.setString(6, seller.getTel());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
	}
	//get coupon id name
	public List<Seller> getAllSellers(){
  		List<Seller> seller = new ArrayList<Seller>();
  		try {
  			query="select * from seller";
  			pst=this.con.prepareStatement(query);
  			rs=pst.executeQuery();
  			while(rs.next()) {
  				Seller cpn = new Seller("","","");
  				cpn.setCity(rs.getString("city"));
  				cpn.setName(rs.getString("name"));
  				cpn.setNic(rs.getString("nic"));
  				cpn.setEmail(rs.getString("email"));
  				cpn.setGrade(rs.getString("grade"));
  				cpn.setTel(rs.getString("tel"));
  				seller.add(cpn);
  				
  			}
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return seller;
  		
  	}
	
	public int deleteSeller(String email) {
  		int result=0;
  		try {
  			query="DELETE FROM seller WHERE email=?;";
  			pst = this.con.prepareStatement(query);
            pst.setString(1,email);
      
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}
	
	//updating coupons
  	public int updateSeller(Seller seller) {
  		int result=0;
  		try {
  			query="UPDATE seller SET name = ?, grade= ? , city=?,nic=?,tel=? WHERE email = ?;";
  			pst = this.con.prepareStatement(query);
  			 pst.setString(1,  seller.getName());
             pst.setString(2, seller.getGrade());
             pst.setString(3, seller.getCity());
             pst.setString(4, seller.getNic());
             pst.setString(5, seller.getTel());
             pst.setString(6, seller.getEmail());
            result=pst.executeUpdate();
  		}catch(Exception e) {
  			e.printStackTrace();
  		}
  		return result;
  	}

}
