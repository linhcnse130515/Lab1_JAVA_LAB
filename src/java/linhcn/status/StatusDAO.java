/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.status;

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
public class StatusDAO implements Serializable{

    Connection conn;
    PreparedStatement pre;
    ResultSet rs;

    private final String GET_CATEGORIES_QUERY = "SELECT StatusId, StatusName FROM Status";

    public List<StatusDTO> getStatus() throws SQLException {
        List<StatusDTO> result = new ArrayList();
        conn = null;
        pre = null;
        rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_CATEGORIES_QUERY;
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
                while (rs.next()) {
                    StatusDTO status = new StatusDTO(Integer.parseInt(rs.getString("StatusId")), rs.getString("StatusName"));
                    result.add(status);
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
