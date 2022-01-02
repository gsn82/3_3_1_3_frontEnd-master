package myWeb.dao;

import myWeb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user join fetch user.roleSet role WHERE user.email = :username")
    User loadUserByUsername(@Param("username") String username);


    @Query("select distinct user from User user join fetch user.roleSet role")
    List<User> findAll();


    @Query("select distinct user from User user join fetch user.roleSet role where user.id=:id")
    Optional<User> findById(@Param("id") Long id);/**/

}
