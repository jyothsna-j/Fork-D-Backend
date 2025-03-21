package com.forkd.forkd_backend.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.forkd.forkd_backend.pojos.Dish;

@Repository
public class DishRepository {

	private final JdbcTemplate jdbcTemplate;

	public DishRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private RowMapper<Dish> dishMapper = (rs, rowNum) -> new Dish(rs.getInt("id"), rs.getString("name"), rs.getString("category"), rs.getFloat("price"));
	
	public List<Dish> findDishesByRestaurant(int restaurantId) {
        String sql = "SELECT d.id, d.name, d.category, d.price " +
                     "FROM dishes d " +
                     "WHERE restaurant_id = ?";
        return jdbcTemplate.query(sql, dishMapper, restaurantId);
    }
}
