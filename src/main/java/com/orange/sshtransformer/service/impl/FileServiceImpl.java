package com.orange.sshtransformer.service.impl;

import com.orange.sshtransformer.dao.FileRepository;
import com.orange.sshtransformer.entity.OFile;
import com.orange.sshtransformer.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;


    @Override
    public List<OFile> getAll() {
        List<OFile> fileList = fileRepository.selectList(null);
        return fileList;
    }
}
