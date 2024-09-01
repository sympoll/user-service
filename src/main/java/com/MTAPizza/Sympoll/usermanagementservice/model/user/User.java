package com.MTAPizza.Sympoll.usermanagementservice.model.user;

import com.MTAPizza.Sympoll.usermanagementservice.dto.user.UserResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password_hash")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "banner_picture_url")
    private String bannerPictureUrl;

    @Column(name = "created_at")
    private final LocalDateTime timeCreated = LocalDateTime.now(); // Initialize to the current time.

    public UserResponse toUserResponse(){
        return new UserResponse(
                this.userId,
                this.username,
                this.email,
                this.profilePictureUrl,
                this.bannerPictureUrl,
                this.timeCreated
        );
    }

}
