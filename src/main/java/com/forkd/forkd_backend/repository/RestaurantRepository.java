package com.forkd.forkd_backend.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.forkd.forkd_backend.pojos.Restaurant;

@Repository
public class RestaurantRepository {

	private final JdbcTemplate jdbcTemplate;
	
	public RestaurantRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private final RowMapper<Restaurant> rowMapper = (rs, rowNum) -> new Restaurant(rs.getInt("id"), rs.getString("name"), rs.getString("cuisine"));
	
	public List<Restaurant> findAll() {
        return jdbcTemplate.query("SELECT * FROM restaurants", rowMapper);
    }
	
	public Restaurant findById(int id) {
		return jdbcTemplate.queryForObject("SELECT * FROM restaurants WHERE id = ?", rowMapper, id);
	}
	
	
}
