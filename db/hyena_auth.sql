create table if not exists sys_role
(
    role_id     bigint                                                not null comment '角色ID'
    primary key,
    role_name   varchar(64)                                           null comment '角色名称',
    role_code   varchar(64)                                           null comment '角色编码',
    role_desc   varchar(255)                                          null comment '角色描述',
    create_by   varchar(64) charset utf8mb3 default ' '               not null comment '创建人',
    update_by   varchar(64) charset utf8mb3 default ' '               not null comment '修改人',
    create_time datetime                    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime                                              null on update CURRENT_TIMESTAMP comment '更新时间',
    del_flag    char                        default '0'               null comment '删除标记，0未删除，1已删除'
    )
    comment '系统角色表' collate = utf8mb4_general_ci;

create index role_idx1_role_code
    on sys_role (role_code);

create table if not exists sys_user
(
    user_id     bigint                                                not null comment '用户ID'
    primary key,
    username    varchar(64)                                           null comment '用户名',
    password    varchar(255)                                          null comment '密码',
    salt        varchar(255)                                          null comment '盐值',
    phone       varchar(20)                                           null comment '电话号码',
    email       varchar(128)                                          null comment '邮箱地址',
    create_by   varchar(64) charset utf8mb3 default ' '               not null comment '创建人',
    update_by   varchar(64) charset utf8mb3 default ' '               not null comment '修改人',
    create_time datetime                    default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime                                              null on update CURRENT_TIMESTAMP comment '修改时间',
    lock_flag   char                        default '0'               null comment '锁定标记，0未锁定，9已锁定',
    del_flag    char                        default '0'               null comment '删除标记，0未删除，1已删除',
    wx_openid   varchar(32)                                           null comment '微信登录openId'
    )
    comment '用户表' collate = utf8mb4_general_ci;

create index user_idx1_username
    on sys_user (username);

create index user_wx_openid
    on sys_user (wx_openid);

create table if not exists user_roles
(
    user_id  bigint null,
    roles_id int    null
)
    comment '用户角色一对多表';


INSERT INTO hyena.user_roles (user_id, roles_id) VALUES (972251858154291200, 1);
INSERT INTO hyena.sys_user (user_id, username, password, salt, phone, email, create_by, update_by, create_time, update_time, lock_flag, del_flag, wx_openid) VALUES (972251858154291200, 'root', '$2a$10$cHILxruEUtGkxJuJ/qy2Uu8rF6qUaL4hS0NTqA0RQ8FyVdPwHByWC', null, null, null, ' ', ' ', '2024-04-01 11:02:52', null, '0', '0', null);
INSERT INTO hyena.sys_role (role_id, role_name, role_code, role_desc, create_by, update_by, create_time, update_time, del_flag) VALUES (1, '管理员权限', 'ROLE_ADMIN', null, ' ', ' ', '2024-04-01 10:21:57', '2024-04-01 10:30:19', '0');
INSERT INTO hyena.sys_role (role_id, role_name, role_code, role_desc, create_by, update_by, create_time, update_time, del_flag) VALUES (2, '普通权限', 'ROLE_NORMAL', null, ' ', ' ', '2024-04-01 10:21:57', '2024-04-01 10:30:12', '0');
