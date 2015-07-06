package com.belief.module.services;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.belief.model.FileBelong;
import com.qiniu.util.Auth;

@Service
public class QiniuServiceImpl implements QiniuService {
    private static String[] array;
    private static String bucketName;
    private static String siteHost;
    private static String accessKey;
    private static String secretKey;
    static {
        array =
                "ad-products|http://img.kcyouli.com|iPhkRNUl5GwPOIs-yGElVYb2PrWsBqWIqmUZMpsv|p4_noUMIvnfWxxzhkUwMWsrwd-9hB0u2CFbQalIS"
                        .split("\\|");
        bucketName = array[0];
        siteHost = array[1];
        accessKey = array[2];
        secretKey = array[3];

    }

    @Override
    public String genUpToken(String bucketName) {
        Auth auth = Auth.create(accessKey, secretKey);
        auth.uploadToken(bucketName);
        return null;
    }

    @Override
    public String genBucketName(FileBelong belong) {
        return null;
    }

    @Override
    public List<String> uploadImgStream(List<InputStream> ins, FileBelong belong) {
        return null;
    }
}
