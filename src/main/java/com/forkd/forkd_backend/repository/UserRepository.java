package com.forkd.forkd_backend.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.forkd.forkd_backend.pojos.User;

@Repository
public class UserRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
    private final RowMapper<User> rowMapper = (rs, rowNum) -> new User(rs.getLong("user_id"), rs.getString("username"), rs.getString("email"), rs.getString("name"), rs.getString("password"), rs.getString("role"));
	
	
	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int createUser(User user) {
		String sql = "INSERT INTO users (username, password, role, email, name) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING user_id";
		
		return jdbcTemplate.queryForObject(sql, Integer.class,
	            user.getUsername(),
	            user.getPassword(),
	            user.getRole(),
	            user.getEmail(),
	            user.getName());
	}

	public User getUserByUsername(String username) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username = ?", rowMapper, username);

	}
	
	public User getUserById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id = ?", rowMapper, id);

	}
	
	
	public boolean doesUsernameExist(String username) {
		return false;
	}
}
