/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.users;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import linhcn.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class UserDAO implements Serializable {

    private final String LOG_IN = "SELECT Name, RoleName FROM Users U, Status S, Roles R "
            + "WHERE U.StatusId = S.StatusId "
            + "AND U.RoleId = R.RoleId "
            + "AND UserID = ? "
            + "AND Password = ? "
            + "AND statusName = 'Active'";

    public UserDTO checkLogin(String userID, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        UserDTO result = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = LOG_IN;
                stm = conn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = new UserDTO(userID, password, rs.getString("RoleName").trim(), rs.getString("Name").trim());
                }
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

}
