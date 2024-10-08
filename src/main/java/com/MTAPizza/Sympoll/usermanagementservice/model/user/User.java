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
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "profile_banner_url")
    private String profileBannerUrl;

    @Column(name = "created_at")
    private final LocalDateTime timeCreated = LocalDateTime.now(); // Initialize to the current time.

    public UserResponse toUserResponse(){
        return new UserResponse(
                this.userId,
                this.username,
                this.email,
                this.description,
                this.profilePictureUrl,
                this.profileBannerUrl,
                this.timeCreated
        );
    }

}
