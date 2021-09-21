/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.cakes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import linhcn.categories.CategoryDTO;
import linhcn.status.StatusDTO;
import linhcn.users.UserDTO;
import linhcn.utils.DBUtils;

/**
 *
 * @author nguye
 */
public class CakeDAO implements Serializable{

    Connection conn;
    PreparedStatement pre;
    ResultSet rs;
    public static final int ROW_PER_PAGE = 20;

    private final String GET_ALL_SEARCH_PAGING = "SELECT CakesId, Name, Description, Price, CreateDate, ExpirationDate, Image, Quantity, Ca.CategoryId, Ca.CategoryName "
            + "FROM Cakes C, Status S, Category Ca "
            + "WHERE C.StatusId = S.StatusId "
            + "AND C.CategoryId = Ca.CategoryId "
            + "AND C.Name LIKE ? "
            + "AND Ca.CategoryName LIKE ? "
            + "AND C.Price >= ? AND C.Price <= ? "
            + "AND C.Quantity > 0"
            + "AND statusName = 'Active'"
            + "ORDER BY CakesId "
            + "OFFSET ? ROWS "
            + "FETCH NEXT ? ROWS ONLY";

    private final String GET_TOTAL_PAGE_SEARCH = "SELECT COUNT(*) as num "
            + "FROM Cakes C, Status S, Category Ca "
            + "WHERE C.StatusId = S.StatusId "
            + "AND C.CategoryId = Ca.CategoryId "
            + "AND C.Name LIKE ? "
            + "AND Ca.CategoryName LIKE ? "
            + "AND C.Price >= ? AND C.Price <= ? "
            + "AND C.Quantity > 0 "
            + "AND statusName = 'Active'";

    private final String GET_QUANTITY = "SELECT quantity FROM Cakes WHERE CakesId = ?";

    private final String GET_CAKE = "SELECT Name, Description, Price, CreateDate, ExpirationDate, Image, Ca.CategoryId, Ca.CategoryName"
            + " FROM Cakes C, Category Ca "
            + "WHERE CakesId = ? "
            + "AND C.CategoryId = Ca.CategoryId ";

    private final String UPDATE_QUANTITY = "UPDATE cakes SET quantity = ? WHERE cakesId = ?";

    private final String GET_ALL_PAGING = "SELECT * "
            + "FROM Cakes C, Status S, Category Ca "
            + "WHERE C.StatusId = S.StatusId "
            + "AND C.CategoryId = Ca.CategoryId "
            + "ORDER BY CakesId "
            + "OFFSET ? ROWS "
            + "FETCH NEXT ? ROWS ONLY ";
    private final String GET_TOTAL_PAGE = "SELECT COUNT(*) as num "
            + "FROM Cakes C, Status S, Category Ca "
            + "WHERE C.StatusId = S.StatusId "
            + "AND C.CategoryId = Ca.CategoryId ";

    private final String INSERT = "INSERT INTO cakes(statusId, cakesId, name, Description, Price, CreateDate, ExpirationDate, Image, Quantity, CategoryId) "
            + "VALUES(?,?,?,?,?,?,?,?,?,?)";

    private final String DELETE = "UPDATE cakes SET statusId = 2, UpdateDate = ?, UpdateBy = ? WHERE cakesId = ?";

    private final String UPDATE = "UPDATE cakes SET statusId = ? , name =?, Description = ?, Price = ?, CreateDate = ?, "
            + "ExpirationDate = ?, Image = ?, Quantity = ?, CategoryId = ?, UpdateDate = ?, UpdateBy = ? "
            + "WHERE cakesId = ? ";

    public List<CakeDTO> searchCakes(String txtSearch, String txtCategory, double fromMoney, double toMoney, int page) throws SQLException {
        List<CakeDTO> result = new ArrayList();
        conn = null;
        pre = null;
        rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_ALL_SEARCH_PAGING;
                pre = conn.prepareStatement(sql);
                pre.setString(1, "%" + txtSearch + "%");
                String cate = txtCategory.equals("All") ? "" : txtCategory;
                pre.setString(2, "%" + cate + "%");
                pre.setDouble(3, fromMoney);
                pre.setDouble(4, toMoney);
                pre.setInt(5, (page - 1) * ROW_PER_PAGE);
                pre.setInt(6, ROW_PER_PAGE);
                rs = pre.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("CakesId");
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date createDate = rs.getDate("CreateDate");
                    Date expirationDate = rs.getDate("ExpirationDate");
                    String image = rs.getString("Image");
                    int quantity = rs.getInt("Quantity");
                    CategoryDTO category = new CategoryDTO(Integer.parseInt(rs.getString("CategoryId")), rs.getString("CategoryName"));
                    result.add(new CakeDTO(id, name, description, price, createDate, expirationDate, image, quantity, category));
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

    public int getQuantity(int id) throws SQLException {
        int result = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                String sql = GET_QUANTITY;
                pre = conn.prepareStatement(sql);
                pre.setInt(1, id);
                rs = pre.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("quantity");
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

    public List<CakeDTO> getAll(int page) throws SQLException {
        List<CakeDTO> result = new ArrayList();
        conn = null;
        pre = null;
        rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_ALL_PAGING;
                pre = conn.prepareStatement(sql);
                pre.setInt(1, (page - 1) * ROW_PER_PAGE);
                pre.setInt(2, ROW_PER_PAGE);
                rs = pre.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("CakesId");
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date createDate = rs.getDate("CreateDate");
                    Date expirationDate = rs.getDate("ExpirationDate");
                    String image = rs.getString("Image");
                    int quantity = rs.getInt("Quantity");
                    Date updateDate = rs.getDate("UpdateDate");
                    String updateBy = rs.getString("UpdateBy");
                    CategoryDTO category = new CategoryDTO(Integer.parseInt(rs.getString("CategoryId")), rs.getString("CategoryName"));
                    StatusDTO status = new StatusDTO(Integer.parseInt(rs.getString("StatusId")), rs.getString("StatusName"));
                    result.add(new CakeDTO(id, name, description, price, createDate, expirationDate, image, quantity, category, updateDate, updateBy, status));
                }
            }
        } catch (NumberFormatException | SQLException | NamingException e) {
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

    public boolean insert(CakeDTO cake) throws SQLException {
        conn = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = INSERT;
                pre = conn.prepareStatement(sql);
                pre.setInt(1, cake.getStatus().getId());
                pre.setInt(2, cake.getCakesId());
                pre.setString(3, cake.getName());
                pre.setString(4, cake.getDescription());
                pre.setFloat(5, cake.getPrice());
                pre.setDate(6, cake.getCreateDate());
                pre.setDate(7, cake.getExpirationDate());
                pre.setString(8, cake.getImage());
                pre.setInt(9, cake.getQuantity());
                pre.setInt(10, cake.getCategory().getId());
                int row = pre.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException | NamingException e) {
        } finally {

            if (pre != null) {
                pre.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean delete(int cakeId, UserDTO user) throws SQLException {
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = DELETE;
                pre = conn.prepareStatement(sql);
                long millis = System.currentTimeMillis();
                Date updateDate = new java.sql.Date(millis);
                pre.setDate(1, updateDate);
                pre.setString(2, user.getFullName());
                pre.setInt(3, cakeId);
                int row = pre.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (pre != null) {
                pre.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public boolean update(CakeDTO cake, UserDTO user) throws SQLException {

        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = UPDATE;
                pre = conn.prepareStatement(sql);
                pre.setInt(1, cake.getStatus().getId());
                pre.setString(2, cake.getName());
                pre.setString(3, cake.getDescription());
                pre.setFloat(4, cake.getPrice());
                pre.setDate(5, cake.getCreateDate());
                pre.setDate(6, cake.getExpirationDate());
                pre.setString(7, cake.getImage());
                pre.setInt(8, cake.getQuantity());
                pre.setInt(9, cake.getCategory().getId());
                long millis = System.currentTimeMillis();
                Date updateDate = new java.sql.Date(millis);
                pre.setDate(10, updateDate);
                pre.setString(11, user.getFullName());
                pre.setInt(12, cake.getCakesId());
                int row = pre.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } catch (SQLException | NamingException e) {
        } finally {

            if (pre != null) {
                pre.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public void updateQuantity(int id, int quantity) throws SQLException {
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                String sql = UPDATE_QUANTITY;
                pre = conn.prepareStatement(sql);
                pre.setInt(1, quantity);
                pre.setInt(2, id);
                pre.executeUpdate();
            }
        } catch (SQLException | NamingException e) {
        } finally {
            if (pre != null) {
                pre.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public int getTotalPageSearch(String txtSearch, String category, double fromMoney, double toMoney) throws SQLException, NamingException {
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_TOTAL_PAGE_SEARCH;
                pre = conn.prepareStatement(sql);
                pre.setString(1, "%" + txtSearch + "%");
                pre.setString(2, "%" + category + "%");
                pre.setDouble(3, fromMoney);
                pre.setDouble(4, toMoney);
                rs = pre.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("num");
                }
                return (int) Math.ceil((double) count / ROW_PER_PAGE);
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
        return 0;
    }

    public int getTotalPage() throws SQLException, NamingException {
        int count = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_TOTAL_PAGE;
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("num");
                }
                return (int) Math.ceil((double) count / ROW_PER_PAGE);
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
        return 0;
    }

    public CakeDTO getCake(int cakesId, int quantity) throws SQLException, NamingException {
        CakeDTO result = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = GET_CAKE;
                pre = conn.prepareStatement(sql);
                pre.setInt(1, cakesId);
                rs = pre.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("Name");
                    String description = rs.getString("Description");
                    float price = rs.getFloat("Price");
                    Date createDate = rs.getDate("CreateDate");
                    Date expirationDate = rs.getDate("ExpirationDate");
                    String image = rs.getString("Image");
                    CategoryDTO category = new CategoryDTO(Integer.parseInt(rs.getString("CategoryId")), rs.getString("CategoryName"));
                    result = new CakeDTO(cakesId, name, description, price, createDate, expirationDate, image, quantity, category);
                }
            }
        } catch (NumberFormatException | SQLException | NamingException e) {
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
