package com.example.heartbeat.service;


import com.example.heartbeat.entity.UserInfo;

public interface UserInfoService {
    UserInfo get(String username);
    void save(UserInfo userInfo);
    void modify(UserInfo userInfo);
}
