package com.MTAPizza.Sympoll.usermanagementservice.model.user.roles.id;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UserRolesId implements Serializable {
    private String groupId;
    private UUID userId;

    public UserRolesId() {}

    public UserRolesId(String groupId, UUID userId) {
        this.groupId = groupId;
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolesId that = (UserRolesId) o;
        return Objects.equals(groupId, that.groupId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, userId);
    }
}