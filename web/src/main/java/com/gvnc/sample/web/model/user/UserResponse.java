package com.gvnc.sample.web.model.user;

import com.gvnc.sample.web.model.response.OperationResponse;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserResponse extends OperationResponse {
    private User data = new User();
}
