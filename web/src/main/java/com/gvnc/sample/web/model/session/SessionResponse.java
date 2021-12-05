package com.gvnc.sample.web.model.session;

import com.gvnc.sample.web.model.response.OperationResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SessionResponse extends OperationResponse {
    private SessionItem item;
}
