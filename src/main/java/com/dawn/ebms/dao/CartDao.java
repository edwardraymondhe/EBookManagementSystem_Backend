package com.dawn.ebms.dao;

import com.dawn.ebms.entity.Cart;

import java.util.List;

public interface CartDao {
    List<Cart> updateCart(Cart cart);
    boolean existsCartByUserIdAndBookId(Integer userId, Integer bookId);
    List<Cart> findCartByUserIdEquals(Integer userId);
    Cart findCartItem(Integer userId, Integer bookId);
    List<Cart> deleteCartItem(Integer userId, Integer bookId);
    List<Cart> deleteCart(Integer userId);
    Cart findCartByUserIdAndBookId(Integer userId, Integer bookId);

}
