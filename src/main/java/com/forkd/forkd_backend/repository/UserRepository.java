package com.forkd.forkd_backend.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.forkd.forkd_backend.pojos.Address;
import com.forkd.forkd_backend.pojos.User;

@Repository
public class UserRepository {
	
	private final JdbcTemplate jdbcTemplate;
	
    private final RowMapper<User> rowMapper = (rs, rowNum) -> new User(rs.getLong("user_id"), rs.getString("username"), rs.getString("email"), rs.getString("name"), rs.getString("password"), rs.getString("role"), rs.getString("contact_number"));
	
	
	public UserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public int createUser(User user) {
		String sql = "INSERT INTO users (username, password, role, email, name, contact_number) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING user_id";
		
		return jdbcTemplate.queryForObject(sql, Integer.class,
	            user.getUsername(),
	            user.getPassword(),
	            user.getRole(),
	            user.getEmail(),
	            user.getName(),
	            user.getContactNumber());
	}

	public User getUserByUsername(String username) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE username = ?", rowMapper, username);

	}
	
	public User getUserById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id = ?", rowMapper, id);

	}
	
	public Address getAddressById(Long id) {
		final RowMapper<Address> addressMapper = (rs, rowNum) -> new Address(rs.getString("address"), rs.getDouble("lat"), rs.getDouble("long"), rs.getString("contact_number"));
		return jdbcTemplate.queryForObject("SELECT address, lat, long, contact_number FROM users JOIN restaurants r ON r.user_id = users.user_id WHERE r.id = ?", addressMapper, id);
		
	}

	public boolean doesUsernameExist(String username) {
		String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
	    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
	    return count != null && count > 0;
	}
	
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM users WHERE role = 'CUSTOMER'";
		return jdbcTemplate.query(sql, rowMapper);
	}
}
