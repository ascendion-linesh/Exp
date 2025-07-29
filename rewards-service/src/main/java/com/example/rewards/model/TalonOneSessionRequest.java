package com.example.rewards.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class TalonOneSessionRequest {
    private String userId;
    private List<CartItem> cartItems;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public String cartHash() {
        return String.valueOf(Objects.hash(userId, cartItems));
    }

    public static class CartItem {
        private String productId;
        private int quantity;
        private BigDecimal price;

        public String getProductId() {
            return productId;
        }
        public void setProductId(String productId) {
            this.productId = productId;
        }
        public int getQuantity() {
            return quantity;
        }
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        public BigDecimal getPrice() {
            return price;
        }
        public void setPrice(BigDecimal price) {
            this.price = price;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CartItem cartItem = (CartItem) o;
            return quantity == cartItem.quantity &&
                    Objects.equals(productId, cartItem.productId) &&
                    Objects.equals(price, cartItem.price);
        }
        @Override
        public int hashCode() {
            return Objects.hash(productId, quantity, price);
        }
    }
}
