package com.springboot.controller;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplyCartOfferRequest {
    private int cart_value;
    private int restaurant_id;
    private int user_id;
}
