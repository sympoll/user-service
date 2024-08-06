package com.MTAPizza.Sympoll.usermanagementservice.repository.user.roles;

import com.MTAPizza.Sympoll.usermanagementservice.model.user.roles.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, UUID> {
}
