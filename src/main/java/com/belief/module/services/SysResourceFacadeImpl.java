package com.belief.module.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belief.model.FileBelong;

@Service
public class SysResourceFacadeImpl implements SysResourceFacade {

    @Autowired
    private QiniuService qiniuService;

    @Override
    public void uploadFileStream(InputStream in, FileBelong belong) throws Exception {
        List<InputStream> ins = new ArrayList<InputStream>();
        ins.add(in);
        qiniuService.uploadImgStream(ins, belong);


    }

}
