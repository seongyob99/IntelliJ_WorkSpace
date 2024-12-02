package com.busanit501.springex.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
    //방법1, 맵퍼 인터페이스에서 애너테이션 이용 후 디비 호출
    @Select("select now()")
    String getNow();

    //방법2, SQL 문장을 따로 파일로 분리
    
}
