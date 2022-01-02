package myWeb.dao;

import myWeb.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.role = :role")
    Role getAuthByName(@Param("role") String role);
}
