package com.barcode.springbootqrcodegeneratorapp.service;

import java.util.List;

public interface IQRService {
    void generate(int id, int width, int height,String filepath,int col) ;
    void generate(List<Integer> ids,int width,int height,String filepath,int col) ;

}
