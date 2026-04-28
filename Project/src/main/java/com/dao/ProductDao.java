package com.dao;

import com.bean.product;
import com.util.ProjectUtil;
import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.bean.product;
public class ProductDao {

    public static void addProduct(product p)   
    { 
        try {
            Connection conn = ProjectUtil.creatConnection();
            String sql = "insert into product(uid, product_catagory, product_name, product_price, product_desc, product_image) values(?,?,?,?,?,?)";           
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setInt(1, p.getUid());
            pst.setString(2, p.getProduct_catagory());   
            pst.setString(3, p.getProduct_name());
            pst.setInt(4, p.getProduct_price());
            pst.setString(5, p.getProduct_desc());
            pst.setString(6, p.getProduct_image());
            pst.executeUpdate();

            System.out.println("Product inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<product> getProductBySeller(int uid)
    {
        List<product> list = new ArrayList<>();
        try {
            Connection conn = ProjectUtil.creatConnection();   
            String sql = "select * from product where uid=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, uid);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            { 
                product p = new product();
                p.setPid(rs.getInt("pid"));
                p.setUid(rs.getInt("uid"));
                p.setProduct_price(rs.getInt("product_price"));
                p.setProduct_catagory(rs.getString("product_catagory")); 
                p.setProduct_name(rs.getString("product_name"));
                p.setProduct_desc(rs.getString("product_desc"));
                p.setProduct_image(rs.getString("product_image"));

                list.add(p); 
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return list; 
    }
    
    public static product getProduct(int pid) {
        product p = null;
        try {
            Connection conn = ProjectUtil.creatConnection();   
            String sql = "select * from product where pid=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, pid);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                p = new product();
                p.setPid(rs.getInt("pid"));
                p.setUid(rs.getInt("uid"));
                p.setProduct_catagory(rs.getString("product_catagory"));
                p.setProduct_name(rs.getString("product_name"));
                p.setProduct_price(rs.getInt("product_price"));
                p.setProduct_desc(rs.getString("product_desc"));
                p.setProduct_image(rs.getString("product_image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
    
    public static void updateProduct(product p) {
        try {
            Connection conn = ProjectUtil.creatConnection();   
            String sql = "update product set product_catagory=?, product_name=?, product_price=?, product_desc=?, product_image=? where pid=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, p.getProduct_catagory());
            pst.setString(2, p.getProduct_name());
            pst.setInt(3, p.getProduct_price());
            pst.setString(4, p.getProduct_desc());
            pst.setString(5, p.getProduct_image());
            pst.setInt(6, p.getPid());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteProduct(int pid)
    {
        try {
            Connection conn = ProjectUtil.creatConnection();
            String sql = "delete from product where pid=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, pid);
            pst.executeUpdate();
            
            System.out.println("Product Deleted Successfully");
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static List<product> getAllProduct()
    {
        List<product> list = new ArrayList<product>();
        
        try {
            Connection conn = ProjectUtil.creatConnection();
            
            String sql = "select * from product";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
             
            while(rs.next())
            {
                product p = new product();
                
                p.setPid(rs.getInt("pid"));
                p.setUid(rs.getInt("uid"));
                p.setProduct_catagory(rs.getString("product_catagory"));
                p.setProduct_name(rs.getString("product_name"));
                p.setProduct_price(rs.getInt("product_price"));
                p.setProduct_desc(rs.getString("product_desc"));
                p.setProduct_image(rs.getString("product_image"));
                
                list.add(p);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }

    
}