package com.dawn.ebms.serviceimpl;

import com.dawn.ebms.dao.CartDao;

import com.dawn.ebms.entity.*;
import com.dawn.ebms.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    public List<Cart> updateCart(Integer userId, Integer bookId, Integer quantity)
    {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setBookId(bookId);

        if(cartDao.existsCartByUserIdAndBookId(userId, bookId)) {
            if(quantity > 0) {
                cart.setQuantity(quantity);// 该用户的购物车中存在此书
                return cartDao.updateCart(cart);
            }else if(quantity == -1)
            {
                Cart fetchCart = cartDao.findCartItem(userId, bookId);
                cart.setQuantity(fetchCart.getQuantity() + 1);
                return cartDao.updateCart(cart);
            }
            else{
                return cartDao.deleteCartItem(userId, bookId);
            }
        }
        else {
            cart.setQuantity(1);// 该用户的购物车中没有此书
            return cartDao.updateCart(cart);
        }
    }

    public List<Cart> findCartByUserId(Integer userId)
    {
        return cartDao.findCartByUserIdEquals(userId);
    }

}
