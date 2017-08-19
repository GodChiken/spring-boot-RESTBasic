package org.phpbae.advice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Administrator on 2017-08-12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionResponseModel {
    private String msg;
    private int statusCode;
}
