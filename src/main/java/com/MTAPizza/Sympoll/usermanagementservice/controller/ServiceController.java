package com.MTAPizza.Sympoll.usermanagementservice.controller;

import com.MTAPizza.Sympoll.usermanagementservice.service.role.RoleService;
import com.MTAPizza.Sympoll.usermanagementservice.service.user.UserService;
import com.MTAPizza.Sympoll.usermanagementservice.service.userRoles.UserRolesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ServiceController {
   private final UserService userService;
   private final RoleService roleService;
   private final UserRolesService userRolesService;

}
