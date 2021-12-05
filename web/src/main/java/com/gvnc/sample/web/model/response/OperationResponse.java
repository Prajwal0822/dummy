package com.gvnc.sample.web.model.response;

import lombok.Data;

@Data
public class OperationResponse {
    private ResponseStatusEnum  operationStatus;
    private String  operationMessage;
    private Object responseObject;
}
