package com.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecommerce.exception.CartItemException;
import com.ecommerce.exception.UserException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.CartRepository;

@Component
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private CartRepository cartRepo;
	
	
	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
		CartItem createdCartItem=cartItemRepo.save(cartItem);
		return createdCartItem;
	
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		CartItem item=findCartItemById(id);
		User user=userService.findUserById(userId);
		if(user.getId().equals(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity()*item.getProduct().getPrice());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
		}
		return cartItemRepo.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		CartItem cartItem=cartItemRepo.isCartItemExist(cart, product, size, userId);
		
		return cartItem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem=findCartItemById(cartItemId);
		User user=userService.findUserById(cartItem.getUserId());
		User reqUser=userService.findUserById(userId);//logged In user
		if(user.getId().equals(reqUser.getId())) {
			cartItemRepo.deleteById(cartItemId);
		}else {
			throw new UserException("You don't have permission to remove another user Item");
		}
		
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		Optional<CartItem> opt=cartItemRepo.findById(cartItemId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartItemException("CartItem not found with Id.."+cartItemId);
	}

}
