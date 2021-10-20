package com.florentina.pethotel.payload.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogOutRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
