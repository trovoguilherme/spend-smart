package br.com.skeleton.spendsmart.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseException {

    private String exceptionMessage;
    private String exceptionDetail;

}
