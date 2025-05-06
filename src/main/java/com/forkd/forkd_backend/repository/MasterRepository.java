package com.forkd.forkd_backend.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MasterRepository {

    private final JdbcTemplate jdbcTemplate;

    public MasterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Get the current value
    public boolean getIsEnabled() {
        String sql = "SELECT is_enabled FROM master_data WHERE id = 1";
        return jdbcTemplate.queryForObject(sql, Boolean.class);
    }

    // Update the value directly
    public void updateIsEnabled(boolean isEnabled) {
        String sql = "UPDATE master_data SET is_enabled = ? WHERE id = 1";
        jdbcTemplate.update(sql, isEnabled);
    }
}
