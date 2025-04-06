package com.forkd.forkd_backend.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.forkd.forkd_backend.pojos.Dish;
import com.forkd.forkd_backend.pojos.Image;

@Repository
public class DishRepository {

	private final JdbcTemplate jdbcTemplate;

	public DishRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private RowMapper<Dish> dishMapper = (rs, rowNum) -> new Dish(rs.getInt("id"), rs.getInt("restaurant_id"), rs.getString("name"), 
												rs.getString("category"), rs.getFloat("price"), new Image(rs.getString("image_type"), rs.getString("image_name"), rs.getBytes("image_data")));
	
	public List<Dish> findDishesByRestaurant(int restaurantId) {
        String sql = "SELECT d.id, d.name, d.category, d.price, d.restaurant_id, d.image_name, d.image_type, d.image_data " +
                     "FROM dishes d " +
                     "WHERE restaurant_id = ?";
        return jdbcTemplate.query(sql, dishMapper, restaurantId);
    }
	
	public String insertDish(Dish dish) {
		String sql = "INSERT INTO dishes (restaurant_id, name, category, price, image_name, image_type, image_data)" +
					" VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id";
		
		try {
	        Integer generatedId = jdbcTemplate.queryForObject(sql, Integer.class,
	                dish.getRestaurant_id(),
	                dish.getDishName(),
	                dish.getCategory(),
	                dish.getPrice(),
	                dish.getDishImage().getImageName(),
	                dish.getDishImage().getImageType(),
	                dish.getDishImage().getData());
	        if (generatedId != null) {
	            return "Dish inserted successfully with ID: " + generatedId;
	        } else {
	            return "Dish insertion failed!";
	        }
	        
	    }catch (DuplicateKeyException e) {
	    	System.err.println("Error inserting dish: " + e.getMessage());
	        return "Dish already exists!"; 	        
	    } catch (DataIntegrityViolationException e) {
	    	System.err.println("Error inserting dish: " + e.getMessage());
	        return "Invalid data! Please check inputs.";	        
	    } catch (Exception e) {
	    	System.err.println("Error inserting dish: " + e.getMessage());
	        return "An unexpected error occurred.";  	
	    } 
	}
	
	public int updateDish(Dish dish) {
		String sql = """
				UPDATE dishes
				SET name = ?, category = ?, price = ?, image_name = ?, image_type = ?, image_data = ?
				WHERE id=?
		""";
		
		try {
			return jdbcTemplate.update(sql,
					dish.getDishName(),
					dish.getCategory(),
					dish.getPrice(),
					dish.getDishImage().getImageName(),
					dish.getDishImage().getImageType(),
					dish.getDishImage().getData(),
					dish.getDishId());
		} catch (DataAccessException e) {
			System.err.print("ERROR UPDATING: " + e.getMessage());
			return 0;
		}
		
	}
	
	public int deleteDish(int dish_id) {
		String sql = "DELETE FROM dishes WHERE id = ?";
		
		try {
			return jdbcTemplate.update(sql, dish_id);
		} catch (DataAccessException e) {
			System.err.print("ERROR DELETING: " + e.getMessage());
			return 0;
		}
	}
}
