package com.busanit501.springex.sample;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
//@Primary // 주 클래스로 사용.
@Qualifier("event")
public class EventSamplDAOImp implements SampleDAO{
}
