package com.MTAPizza.Sympoll.usermanagementservice.model.userRoles;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "user_roles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRoles {
    @Id
    private UUID groupId;

    @Id
    private UUID userId;

    @Column(name = "role_id")
    private UUID rollId;
}
