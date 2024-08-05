package com.MTAPizza.Sympoll.usermanagementservice.repository.role;

import com.MTAPizza.Sympoll.usermanagementservice.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
}
