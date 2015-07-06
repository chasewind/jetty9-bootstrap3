package com.belief.module.services;

import java.io.InputStream;
import java.util.List;

import com.belief.model.FileBelong;

public interface QiniuService {
    String genUpToken(String bucketName);

    String genBucketName(FileBelong belong);

    List<String> uploadImgStream(List<InputStream> ins, FileBelong belong);
}
