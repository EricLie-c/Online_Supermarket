package com.onlinesupermarket.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    private String name;
    private String picture;
    private String price;
    private String delivTime;
    private String delivAddr;

}
