package com.zljin.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_roles")
public class UserRoles {

  private long userId;
  private long rolesId;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getRolesId() {
    return rolesId;
  }

  public void setRolesId(long rolesId) {
    this.rolesId = rolesId;
  }

}
