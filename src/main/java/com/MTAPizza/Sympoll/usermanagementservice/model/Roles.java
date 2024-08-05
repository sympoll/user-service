package com.MTAPizza.Sympoll.usermanagementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID rollId;

    @Column(name = "role_name")
    private String rollName;
}
