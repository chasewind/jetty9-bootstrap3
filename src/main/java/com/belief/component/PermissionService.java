package com.belief.component;

import com.belief.module.model.User;

public interface PermissionService {

    User getSimpleUserByUserName(String userName);

}
