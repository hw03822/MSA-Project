package com.example.user_service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String email;
    private String name;
    private String userId;

    // 사용자가 주문했던 내역을 확인하기 위한 주문내역
    private List<ResponseOrder> orders;
}
