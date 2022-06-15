package com.pos.cart.model;

import com.pos.database.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserItem {
    private Item item;
    private String uid;
}
