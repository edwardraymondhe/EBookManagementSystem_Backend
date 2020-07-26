package com.dawn.ebms.service;

import com.dawn.ebms.entity.Cart;

import java.util.List;

public interface CartService {
    List<Cart> updateCart(Integer userId, Integer bookId, Integer quantity);
    List<Cart> findCartByUserId(Integer userId);
}
