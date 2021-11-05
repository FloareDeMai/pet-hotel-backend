package com.florentina.pethotel.payload.request;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SignUpRequest {

    private String username;
    private String email;
    private String password;
    private Set<String> role;
}
