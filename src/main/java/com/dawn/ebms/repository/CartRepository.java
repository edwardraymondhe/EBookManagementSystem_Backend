package com.dawn.ebms.repository;

import com.dawn.ebms.entity.Cart;
import com.dawn.ebms.entity.CartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, CartId> {
    List<Cart> findByUserIdEquals(Integer userId);
    boolean existsByUserIdAndBookId(Integer userId, Integer bookId);

    Cart findCartByUserIdAndBookId(Integer userId, Integer bookId);

    @Modifying
    @Transactional
    void deleteCartByUserIdAndBookId(Integer userId, Integer bookId);

    @Modifying
    @Transactional
    void deleteAllByUserId(Integer userId);
}
