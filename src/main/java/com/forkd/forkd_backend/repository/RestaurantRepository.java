package com.forkd.forkd_backend.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.forkd.forkd_backend.pojos.Image;
import com.forkd.forkd_backend.pojos.Restaurant;

@Repository
public class RestaurantRepository {

	private final JdbcTemplate jdbcTemplate;

	public RestaurantRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final RowMapper<Restaurant> rowMapper = (rs, rowNum) -> new Restaurant(rs.getInt("id"),
			rs.getString("name"), rs.getString("cuisine"),
			new Image(rs.getString("image_type"), rs.getString("image_name"), rs.getBytes("image_data")),
			rs.getInt("user_id"));

	public List<Restaurant> findAll() {
		return jdbcTemplate.query("SELECT * FROM restaurants", rowMapper);
	}

	public Restaurant findById(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM restaurants WHERE id = ?", rowMapper, id);
	}
	
	public Restaurant findByUserId(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM restaurants WHERE user_id = ?", rowMapper, id);
	}

	public int updateRestaurantImage(Restaurant restaurant) {
		String sql = "UPDATE restaurants SET image_data = ?, image_name = ?, image_type = ? WHERE id = ?";
		return jdbcTemplate.update(sql, restaurant.getLogo().getData(), restaurant.getLogo().getImageName(), restaurant.getLogo().getImageType(), restaurant.getRestaurantId());
	}
	
	public int updateRestaurantCuisine(Restaurant restaurant) {
		String sql = "UPDATE restaurants SET cuisine = ? WHERE id = ?";
		return jdbcTemplate.update(sql, restaurant.getCuisine(), restaurant.getRestaurantId());
	}

}
