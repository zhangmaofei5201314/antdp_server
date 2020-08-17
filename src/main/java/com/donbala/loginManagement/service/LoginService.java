package com.donbala.loginManagement.service;

import com.donbala.userManagement.model.CmsUser;

import java.util.List;
import java.util.Map;

public interface LoginService {

    void saveLoginTrace(String usercode, String logintype);

    CmsUser getUserByUsercode(Map<String, String> usermap);

    List<Map<String, Object>> getAntdUserMenuByCode(String usercode);
}
