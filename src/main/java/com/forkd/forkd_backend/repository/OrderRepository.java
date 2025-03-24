package com.forkd.forkd_backend.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.forkd.forkd_backend.pojos.Dish;
import com.forkd.forkd_backend.pojos.Order;
import com.forkd.forkd_backend.pojos.OrderedItems;
import com.forkd.forkd_backend.pojos.Restaurant;
import com.forkd.forkd_backend.pojos.User;

@Repository
public class OrderRepository {

	private final JdbcTemplate jdbcTemplate;
	
	public OrderRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<Order> getOrdersByUserId(Long userId) {
	    String sql = """
	        SELECT 
	            o.order_id, o.amount, o.order_status, o.order_date, o.order_ref_no, 
	            u.user_id, u.username, u.email, u.name,
	            r.id, r.name as restaurant_name
	        FROM orders o
	        JOIN users u ON o.user_id = u.user_id
	        JOIN restaurants r ON o.restaurant_id = r.id
	        WHERE o.user_id = ?
	    """;

	    return jdbcTemplate.query(sql, (rs, rowNum) -> {
	        Order order = new Order();
	        order.setOrderId(rs.getInt("order_id"));
	        order.setAmount(rs.getFloat("amount"));
	        order.setOrderStatus(rs.getString("order_status"));
	        order.setOrderDate(rs.getDate("order_date"));
	        order.setOrderReferenceNumber(UUID.fromString(rs.getString("order_ref_no")));

	        // 🟢 Populate User
	        User user = new User();
	        user.setUserId(rs.getLong("user_id"));
	        user.setUsername(rs.getString("username"));
	        user.setEmail(rs.getString("email"));
	        user.setName(rs.getString("name"));
	        order.setUser(user);

	        // 🟡 Populate Restaurant
	        Restaurant restaurant = new Restaurant();
	        restaurant.setRestaurantId(rs.getInt("id"));
	        restaurant.setRestaurantName(rs.getString("restaurant_name"));
	        order.setRestaurant(restaurant);
	        
	        List<OrderedItems> items = getOrderedItemsByOrderRefNo(order.getOrderReferenceNumber());
	        order.setItems(items);

	        return order;
	    }, userId);
	}
	
	public List<Order> getOrdersByRestaurantId(int restaurantId) {
	    String sql = """
	        SELECT 
	            o.order_id, o.amount, o.order_status, o.order_date, o.order_ref_no, 
	            u.user_id, u.username, u.email, u.name,
	            r.id, r.name as restaurant_name
	        FROM orders o
	        JOIN users u ON o.user_id = u.user_id
	        JOIN restaurants r ON o.restaurant_id = r.id
	        WHERE o.restaurant_id = ?
	    """;

	    return jdbcTemplate.query(sql, (rs, rowNum) -> {
	        Order order = new Order();
	        order.setOrderId(rs.getInt("order_id"));
	        order.setAmount(rs.getFloat("amount"));
	        order.setOrderStatus(rs.getString("order_status"));
	        order.setOrderDate(rs.getDate("order_date"));
	        order.setOrderReferenceNumber(UUID.fromString(rs.getString("order_ref_no")));

	        // 🟢 Populate User
	        User user = new User();
	        user.setUserId(rs.getLong("user_id"));
	        user.setUsername(rs.getString("username"));
	        user.setEmail(rs.getString("email"));
	        user.setName(rs.getString("name"));
	        order.setUser(user);

	        // 🟡 Populate Restaurant
	        Restaurant restaurant = new Restaurant();
	        restaurant.setRestaurantId(rs.getInt("id"));
	        restaurant.setRestaurantName(rs.getString("restaurant_name"));
	        order.setRestaurant(restaurant);
	        
	        List<OrderedItems> items = getOrderedItemsByOrderRefNo(order.getOrderReferenceNumber());
	        order.setItems(items);

	        return order;
	    }, restaurantId);
	}
	
	private List<OrderedItems> getOrderedItemsByOrderRefNo(UUID orderRefNo) {
	    String sql = """
	        SELECT o.order_dishes_id, o.quantity, o.price, o.order_ref_no,
	    		   d.id, d.name
	        FROM order_dishes o
	        JOIN dishes d ON o.dish_id = d.id
	        WHERE o.order_ref_no = ?
	    """;

	    return jdbcTemplate.query(sql, (rs, rowNum) -> {
	        OrderedItems item = new OrderedItems();
	        item.setOrderedItemId(rs.getInt("order_dishes_id"));
	        item.setQuantity(rs.getInt("quantity"));
	        item.setPrice(rs.getFloat("price"));
	        
	        Dish dish = new Dish();
	        dish.setDishId(rs.getInt("id"));
	        dish.setDishName(rs.getString("name"));
	        item.setDish(dish);
	        return item;
	    }, orderRefNo);
	}
	
	public int insertOrder(Order order) {
        String sql = "INSERT INTO orders (user_id, restaurant_id, amount, order_status, order_date, order_ref_no) " +
                     "VALUES (?, ?, ?, ?, ?, ?) RETURNING order_id";

        int rows = jdbcTemplate.queryForObject(sql, Integer.class,
            order.getUser().getUserId(),
            order.getRestaurant().getRestaurantId(),
            order.getAmount(),
            order.getOrderStatus(),
            new java.sql.Timestamp(order.getOrderDate().getTime()),  // Convert Date to SQL Timestamp
            order.getOrderReferenceNumber());
        
        insertOrderItems(order.getItems(), order.getOrderReferenceNumber());
        
        return rows;
    }

	private void insertOrderItems(List<OrderedItems> items, UUID orderReferenceNumber) {
		String sql = """
		        INSERT INTO order_dishes (order_ref_no, dish_id, quantity, price) 
		        VALUES (?, ?, ?, ?)
		    """;

		    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
		        @Override
		        public void setValues(PreparedStatement ps, int i) throws SQLException {
		            OrderedItems item = items.get(i);
		            ps.setObject(1, orderReferenceNumber);
		            ps.setInt(2, item.getDish().getDishId());
		            ps.setInt(3, item.getQuantity());
		            ps.setFloat(4, item.getPrice());
		        }

		        @Override
		        public int getBatchSize() {
		            return items.size();
		        }
		    });
		
	}
	
	public void updateStatus(int orderId, String status) {
		String sql = """
				UPDATE orders
				SET order_status = ?
				WHERE order_id = ?;
		""";
		
		jdbcTemplate.update(sql, status, orderId);
	}
}
