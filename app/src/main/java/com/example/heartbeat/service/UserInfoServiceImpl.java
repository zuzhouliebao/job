package com.example.heartbeat.service;

import android.content.Context;

import com.example.heartbeat.dao.UserInfoDao;
import com.example.heartbeat.dao.UserInfoDaoImpl;
import com.example.heartbeat.entity.UserInfo;

public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoDao dao;

    public UserInfoServiceImpl(Context context) {
     dao = new UserInfoDaoImpl(context);

    }

    @Override
    public UserInfo get(String username) {
        return dao.select(username);
    }

    @Override
    public void save(UserInfo userInfo) {
        dao.insert(userInfo);
    }

    @Override
    public void modify(UserInfo userInfo) {
        dao.update(userInfo);
    }
}
