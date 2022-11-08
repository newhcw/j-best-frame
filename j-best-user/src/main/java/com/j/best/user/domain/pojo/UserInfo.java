package com.j.best.user.domain.pojo;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserInfo {



    private String userId;

    private String userName;


    public List<Address> getAddressList(){
        return new ArrayList<Address>();
    }
}
