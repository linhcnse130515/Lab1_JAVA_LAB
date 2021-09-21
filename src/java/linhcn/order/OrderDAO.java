/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import linhcn.cakes.CakeDTO;
import linhcn.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class OrderDAO implements Serializable {

    Connection conn = null;
    PreparedStatement stm = null;
    ResultSet rs = null;

    private final String CHECK_OUT_ORDER = "INSERT INTO orders OUTPUT inserted.orderId VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String INSERT_ORDER_DETAIL = "INSERT INTO OrderDetail(Amount, OrderID, CakesId) VALUES(?, ?, ?) ";

    private final String GET_ORDER_USER = "SELECT OrderID, UserName, OrderDate, PaymentMethod, PaymentStatus, ShippingAddress "
            + "FROM Orders WHERE OrderID = ? AND UserID = ? ";

    private final String GET_ORDER_ADMIN = "SELECT OrderID, UserName, OrderDate, PaymentMethod, PaymentStatus, ShippingAddress "
            + "FROM Orders WHERE OrderID = ? ";

    private final String GET_CAKES = "SELECT CakesId, Amount "
            + "FROM OrderDetail WHERE OrderID = ?";

    public int checkoutOrder(float total, Date orderDate, String paymentMethod, String ShippingAddress, String userName, String phoneNumbe, String userId) throws SQLException, NamingException {
        try {
            conn = DBUtils.getConnection();
            String sql = CHECK_OUT_ORDER;
            stm = conn.prepareStatement(sql);
            stm.setFloat(1, total);
            stm.setInt(2, 1);
            stm.setDate(3, orderDate);
            stm.setString(4, paymentMethod);
            stm.setInt(5, 1);
            stm.setString(6, ShippingAddress);
            stm.setString(7, userName);
            stm.setString(8, phoneNumbe);
            stm.setString(9, userId);
            rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("OrderId");
            }
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

        return -1;
    }

    public boolean insertOrderDetails(int amount, int orderId, int cakesId) throws NamingException, SQLException {
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = INSERT_ORDER_DETAIL;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, amount);
                stm.setInt(2, orderId);
                stm.setInt(3, cakesId);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public OrderDTO searchOrderbyUser(int orderId, String userId) throws SQLException {
        OrderDTO result = null;
        conn = null;
        stm = null;
        rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_ORDER_USER;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, orderId);
                stm.setString(2, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userName = rs.getString("userName");
                    Date orderDate = rs.getDate("OrderDate");
                    String paymentMethod = rs.getString("PaymentMethod");
                    int paymentStatus = rs.getInt("PaymentStatus");
                    String shippingAddress = rs.getString("ShippingAddress");
                    result = new OrderDTO(orderId, orderDate, paymentMethod, paymentStatus, shippingAddress, userName);
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

    public OrderDTO searchOrderbyAdmin(int orderId) throws SQLException {
        OrderDTO result = null;
        conn = null;
        stm = null;
        rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_ORDER_ADMIN;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, orderId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String userName = rs.getString("userName");
                    Date orderDate = rs.getDate("OrderDate");
                    String paymentMethod = rs.getString("PaymentMethod");
                    int paymentStatus = rs.getInt("PaymentStatus");
                    String shippingAddress = rs.getString("ShippingAddress");
                    result = new OrderDTO(orderId, orderDate, paymentMethod, paymentStatus, shippingAddress, userName);
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

    public List<CakeDTO> getCakes(int orderId) throws SQLException {
        List<CakeDTO> result = new ArrayList<>();
        conn = null;
        stm = null;
        rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_CAKES;
                stm = conn.prepareStatement(sql);
                stm.setInt(1, orderId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("CakesId");
                    int amount = rs.getInt("Amount");
                    CakeDTO cake = new CakeDTO();
                    cake.setCakesId(id);
                    cake.setQuantity(amount);
                    result.add(cake);
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
