package com.forkd.forkd_backend.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.forkd.forkd_backend.controller.uEngagePojos.TrackTaskCallbackRequest;
import com.forkd.forkd_backend.controller.uEngagePojos.TrackTaskCallbackRequest.Data;
import com.forkd.forkd_backend.pojos.Address;
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
    
    public List<Order> getOrders() {
        String sql = """
            SELECT 
                o.order_id, o.amount, o.order_status, o.delivery_status, o.order_date, o.order_ref_no, 
                o.pickup_address, o.pickup_latitude, o.pickup_longitude,
                o.drop_address, o.drop_latitude, o.drop_longitude,
                u.user_id, u.username, u.email, u.name, u.contact_number,
                r.id, r.name as restaurant_name
            FROM orders o
            JOIN users u ON o.user_id = u.user_id
            JOIN restaurants r ON o.restaurant_id = r.id
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setAmount(rs.getFloat("amount"));
            order.setOrderStatus(rs.getString("order_status"));
            order.setDeliveryStatus(rs.getString("delivery_status"));
            order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
            order.setOrderReferenceNumber(UUID.fromString(rs.getString("order_ref_no")));

            order.setPickupAddress(new Address(
                rs.getString("pickup_address"),
                rs.getDouble("pickup_latitude"),
                rs.getDouble("pickup_longitude")
            ));

            order.setDropAddress(new Address(
                rs.getString("drop_address"),
                rs.getDouble("drop_latitude"),
                rs.getDouble("drop_longitude")
            ));

            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setContactNumber(rs.getString("contact_number"));
            order.setUser(user);

            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(rs.getInt("id"));
            restaurant.setRestaurantName(rs.getString("restaurant_name"));
            order.setRestaurant(restaurant);

            order.setItems(getOrderedItemsByOrderRefNo(order.getOrderReferenceNumber()));
            return order;
        });
    }
    
    public List<Order> getOrdersByUserId(Long userId) {
        String sql = """
            SELECT 
                o.order_id, o.amount, o.order_status, o.delivery_status, o.order_date, o.order_ref_no, 
                o.pickup_address, o.pickup_latitude, o.pickup_longitude,
                o.drop_address, o.drop_latitude, o.drop_longitude,
                u.user_id, u.username, u.email, u.name, u.contact_number,
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
            order.setDeliveryStatus(rs.getString("delivery_status"));
            order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
            order.setOrderReferenceNumber(UUID.fromString(rs.getString("order_ref_no")));

            order.setPickupAddress(new Address(
                rs.getString("pickup_address"),
                rs.getDouble("pickup_latitude"),
                rs.getDouble("pickup_longitude")
            ));

            order.setDropAddress(new Address(
                rs.getString("drop_address"),
                rs.getDouble("drop_latitude"),
                rs.getDouble("drop_longitude")
            ));

            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setContactNumber(rs.getString("contact_number"));
            order.setUser(user);

            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(rs.getInt("id"));
            restaurant.setRestaurantName(rs.getString("restaurant_name"));
            order.setRestaurant(restaurant);

            order.setItems(getOrderedItemsByOrderRefNo(order.getOrderReferenceNumber()));
            return order;
        }, userId);
    }

    public List<Order> getOrdersByRestaurantId(int restaurantId) {
        String sql = """
            SELECT 
                o.order_id, o.amount, o.order_status, o.delivery_status, o.order_date, o.order_ref_no,
                o.pickup_address, o.pickup_latitude, o.pickup_longitude,
                o.drop_address, o.drop_latitude, o.drop_longitude,
                u.user_id, u.username, u.email, u.name, u.contact_number,
                r.id, r.name as restaurant_name
            FROM orders o
            JOIN users u ON o.user_id = u.user_id
            JOIN restaurants r ON o.restaurant_id = r.id
            WHERE r.user_id = ?
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setAmount(rs.getFloat("amount"));
            order.setOrderStatus(rs.getString("order_status"));
            order.setDeliveryStatus(rs.getString("delivery_status"));
            order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
            order.setOrderReferenceNumber(UUID.fromString(rs.getString("order_ref_no")));

            order.setPickupAddress(new Address(
                rs.getString("pickup_address"),
                rs.getDouble("pickup_latitude"),
                rs.getDouble("pickup_longitude")
            ));

            order.setDropAddress(new Address(
                rs.getString("drop_address"),
                rs.getDouble("drop_latitude"),
                rs.getDouble("drop_longitude")
            ));

            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setContactNumber(rs.getString("contact_number"));
            order.setUser(user);

            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(rs.getInt("id"));
            restaurant.setRestaurantName(rs.getString("restaurant_name"));
            order.setRestaurant(restaurant);

            order.setItems(getOrderedItemsByOrderRefNo(order.getOrderReferenceNumber()));
            return order;
        }, restaurantId);
    }

    public List<Order> getPaymentApprovalPendingOrders() {
        String sql = """
            SELECT 
                o.order_id, o.amount, o.order_status, o.delivery_status, o.order_date, o.order_ref_no,
                o.pickup_address, o.pickup_latitude, o.pickup_longitude,
                o.drop_address, o.drop_latitude, o.drop_longitude,
                u.user_id, u.username, u.email, u.name, u.contact_number, ur.contact_number as res_cn,
                r.id, r.name as restaurant_name
            FROM orders o
            JOIN users u ON o.user_id = u.user_id
            JOIN restaurants r ON o.restaurant_id = r.id
            JOIN users ur ON ur.user_id = r.user_id
            WHERE o.order_status = 'PAYMENT_APPROVAL_PENDING'
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Order order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setAmount(rs.getFloat("amount"));
            order.setOrderStatus(rs.getString("order_status"));
            order.setDeliveryStatus(rs.getString("delivery_status"));
            order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
            order.setOrderReferenceNumber(UUID.fromString(rs.getString("order_ref_no")));

            order.setPickupAddress(new Address(
                rs.getString("pickup_address"),
                rs.getDouble("pickup_latitude"),
                rs.getDouble("pickup_longitude"),
                rs.getString("res_cn")
            ));

            order.setDropAddress(new Address(
                rs.getString("drop_address"),
                rs.getDouble("drop_latitude"),
                rs.getDouble("drop_longitude")
            ));

            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setUsername(rs.getString("username"));
            user.setEmail(rs.getString("email"));
            user.setName(rs.getString("name"));
            user.setContactNumber(rs.getString("contact_number"));
            order.setUser(user);

            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(rs.getInt("id"));
            restaurant.setRestaurantName(rs.getString("restaurant_name"));
            order.setRestaurant(restaurant);

            order.setItems(getOrderedItemsByOrderRefNo(order.getOrderReferenceNumber()));
            return order;
        });
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

    public Integer insertOrder(Order order) {
        String sql = """
            INSERT INTO orders 
            (user_id, restaurant_id, amount, order_status, delivery_status, order_date, order_ref_no, 
            pickup_address, pickup_latitude, pickup_longitude,
            drop_address, drop_latitude, drop_longitude)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            RETURNING order_id
        """;

        try {
        	Integer orderId = jdbcTemplate.queryForObject(sql, Integer.class,
        		order.getUser().getUserId(),
        		order.getRestaurant().getRestaurantId(),
        		order.getAmount(),
        		order.getOrderStatus(),
        		order.getDeliveryStatus(),
        		Timestamp.valueOf(order.getOrderDate()),
        		order.getOrderReferenceNumber(),
        		order.getPickupAddress().getAddress(),
        		order.getPickupAddress().getLatitude(),
        		order.getPickupAddress().getLongitude(),
        		order.getDropAddress().getAddress(),
	        	order.getDropAddress().getLatitude(),
	            order.getDropAddress().getLongitude()
        	);

        	if (orderId != null) {
                insertOrderItems(order.getItems(), order.getOrderReferenceNumber());
                return orderId;
            } else {
                return -1;
            }
        	
        } catch (Exception e) {
            System.err.println("Error during insertOrder: " + e.getMessage());
            return -1;
        }
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

    public boolean updateStatus(int orderId, String status) {
        String sql = """
            UPDATE orders
            SET order_status = ?
            WHERE order_id = ?
        """;
        
        try {
            int rowsAffected = jdbcTemplate.update(sql, status, orderId);
            return rowsAffected > 0; // true if the update happened
        } catch (Exception e) {
            System.err.println("Failed to update order status for orderId " + orderId + ": " + e.getMessage());
            return false;
        }
    }
    
    public void updateStatus(String taskId, String status) {
        String sql = """
            UPDATE orders
            SET order_status = ?
            WHERE taskId = ?
        """;
        
        jdbcTemplate.update(sql, status, taskId);
    }
    
    public void updatetaskId(int orderId, String taskId) {
        String sql = """
            UPDATE orders
            SET taskId = ?
            WHERE order_id = ?
        """;
        
        Integer count = jdbcTemplate.update(sql, taskId, orderId);
        
        if (count > 0) {
            String insertSql = """
                INSERT INTO rider_details (task_id)
                VALUES (?)
            """;
            jdbcTemplate.update(insertSql, taskId);
        }
    }

    public void updateDeliveryStatus(String taskId, String deliveryStatus) {
        String sql = """
            UPDATE orders
            SET delivery_status = ?
            WHERE taskId = ?
        """;
        jdbcTemplate.update(sql, deliveryStatus, taskId);
    }
    
    public void updateRiderTaskDetails(TrackTaskCallbackRequest task) {
        String sql = """
            UPDATE rider_details
            SET
                rider_name = ?,
                rider_contact = ?,
                latitude = ?,
                longitude = ?,
                tracking_url = ?
            WHERE task_id = ?
        """;

        jdbcTemplate.update(sql,
            task.getData().getRider_name(),
            task.getData().getRider_contact(),
            task.getData().getLatitude(),
            task.getData().getLongitude(),
            task.getData().getTracking_url(),
            task.getData().getTaskId()
        );
    }
    
    //TODO: error handling for taskId or data not existing
    public Data getRiderDetails(int ordId) {
    	String sql = """
    	        SELECT rd.*
    	        FROM rider_details rd
    	        JOIN orders o ON o.taskId = rd.task_id
    	        WHERE o.order_id = ?;
    	    """;

    	    try {
    	        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
    	            String taskId = rs.getString("task_id");
    	            String name = rs.getString("rider_name");
    	            String contact = rs.getString("rider_contact");
    	            String lat = rs.getString("latitude");
    	            String lon = rs.getString("longitude");
    	            String tracking = rs.getString("tracking_url");

    	            // If taskId is null or any essential rider detail is missing, return null
    	            if (taskId == null || name == null || contact == null || lat == null || lon == null) {
    	                return null;
    	            }

    	            Data rd = new Data();
    	            rd.setTaskId(taskId);
    	            rd.setRider_name(name);
    	            rd.setRider_contact(contact);
    	            rd.setLatitude(lat);
    	            rd.setLongitude(lon);
    	            rd.setTracking_url(tracking);
    	            return rd;
    	        }, ordId);
    	    } catch (EmptyResultDataAccessException e) {
    	        return null;
    	    } catch (Exception e) {
    	        System.err.println("Error fetching rider details: " + e.getMessage());
    	        return null;
    	    }
    }
}
