package com.dawn.ebms.daoimpl;

import com.dawn.ebms.dao.CartDao;
import com.dawn.ebms.entity.*;
import com.dawn.ebms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    private CartRepository cartRepository;


    public List<Cart> updateCart(Cart cart)
    {
        cartRepository.save(cart);
        return cartRepository.findByUserIdEquals(cart.getUserId());
    }

    public List<Cart> deleteCart(Integer userId)
    {
        cartRepository.deleteAllByUserId(userId);
        return cartRepository.findByUserIdEquals(userId);
    }
    public List<Cart> deleteCartItem(Integer userId, Integer bookId)
    {
        cartRepository.deleteCartByUserIdAndBookId(userId, bookId);
        return cartRepository.findByUserIdEquals(userId);
    }

    public boolean existsCartByUserIdAndBookId(Integer userId, Integer bookId) {
        return cartRepository.existsByUserIdAndBookId(userId, bookId);
    }

    public List<Cart> findCartByUserIdEquals(Integer userId) {
        return cartRepository.findByUserIdEquals(userId);
    }

    public Cart findCartItem(Integer userId, Integer bookId)
    {
        return cartRepository.findCartByUserIdAndBookId(userId, bookId);
    }

    public Cart findCartByUserIdAndBookId(Integer userId, Integer bookId)
    {
        return cartRepository.findCartByUserIdAndBookId(userId, bookId);
    }


}
