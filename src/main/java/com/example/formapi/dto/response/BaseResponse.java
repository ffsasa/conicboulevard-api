package com.example.formapi.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class BaseResponse<T> {
    private String status;
    private String message;
    private T data;
}
