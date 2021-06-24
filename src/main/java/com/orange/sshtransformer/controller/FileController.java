package com.orange.sshtransformer.controller;

import com.orange.sshtransformer.entity.OFile;
import com.orange.sshtransformer.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/all")
    public List<OFile> getAll(){
        return fileService.getAll();
    }

}
