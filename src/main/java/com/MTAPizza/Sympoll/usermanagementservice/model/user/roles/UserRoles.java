package com.MTAPizza.Sympoll.usermanagementservice.model.user.roles;

import com.MTAPizza.Sympoll.usermanagementservice.model.user.roles.id.UserRolesId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "user_roles")
@IdClass(UserRolesId.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRoles {
    @Id
    private String groupId;

    @Id
    private UUID userId;

    @Column(name = "role_id")
    private UUID rollId;
}
