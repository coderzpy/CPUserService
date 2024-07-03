package com.example.cpuserservice.models;

import java.text.DateFormat;

public class UserRoleMapping extends BaseModel {
    User user;
    UserRole role;
    DateFormat assignedAt;
}
