package com.busanit501.springex.sample;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


//컨트롤러 역할, @Controller
//서비스 역할, @Service
//DAO 역할, @Repository

@Service // 일반 객체 타입을 시스템 동록
@ToString
@RequiredArgsConstructor
public class SampleService {

    //방법 1 필드 주입방식
//    @Autowired
//    private SampleDAO sampleDAO;


    //방법 2 생성자 주입방식  -> 1) 생성자로 주입하는 방법 2) 롬복 애너테이션 이용


// 1) 생성자로 주입하는 방법
//    public SampleService(SampleDAO sampleDAO) {
//        this.sampleDAO = sampleDAO; }

// 2) 롬복 애너테이션. 클래스 상단에 @RequiredArgsConstructor

@Qualifier("normal")
private final SampleDAO sampleDAO;
    }

