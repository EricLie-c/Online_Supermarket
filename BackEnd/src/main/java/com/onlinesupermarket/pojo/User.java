package com.onlinesupermarket.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userName;
    private String account;
    private String password;
    private String address;
    private String role;

    public static String userN;
}
