/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.categories;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import linhcn.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class CategoryDAO implements Serializable{

    Connection conn;
    PreparedStatement pre;
    ResultSet rs;

    private final String GET_CATEGORIES_QUERY = "SELECT * FROM Category";

    public List<CategoryDTO> getGategories() throws SQLException {
        List<CategoryDTO> result = new ArrayList();
        conn = null;
        pre = null;
        rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_CATEGORIES_QUERY;
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
                CategoryDTO category = new CategoryDTO(0, "All");
                result.add(category);
                while (rs.next()) {
                    category = new CategoryDTO(Integer.parseInt(rs.getString("CategoryId")), rs.getString("CategoryName"));
                    result.add(category);
                }
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pre != null) {
                pre.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
