package com.amu.controllers;

import com.amu.Entities.ProdInfo;
import com.amu.mappers.ProdInfoMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("prod")
public class ProdController {
    @Autowired ProdInfoMapper prodInfoMapper;

    @RequestMapping("")
    public List<ProdInfo> prodList(){
        return prodInfoMapper.selectByExample(null);
    }
}
