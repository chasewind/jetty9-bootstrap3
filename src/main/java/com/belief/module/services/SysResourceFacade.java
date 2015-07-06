package com.belief.module.services;

import java.io.InputStream;

import com.belief.model.FileBelong;

/**
 * 系统资源管理
 * 
 * @author YuDongwei
 *
 */
public interface SysResourceFacade {
    void uploadFileStream(InputStream in, FileBelong belong) throws Exception;
}
