/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhcn.cart;

import java.util.HashMap;
import java.util.Map;
import linhcn.cakes.CakeDTO;

/**
 *
 * @author nguye
 */
public class Cart {

    private Map<Integer, CakeDTO> cart;

    public Cart(Map<Integer, CakeDTO> cart) {
        this.cart = cart;
    }

    public Cart() {
    }

    public Map<Integer, CakeDTO> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, CakeDTO> cart) {
        this.cart = cart;
    }

    public void add(CakeDTO dto, int amount) {
        if (this.cart == null) {
            this.cart = new HashMap();
        }
        if (this.cart.containsKey(dto.getCakesId())) {
            int quantity = this.cart.get(dto.getCakesId()).getQuantity();
            dto.setQuantity(quantity + amount);
        }
        cart.put(dto.getCakesId(), dto);
    }

    public int getQuantity(CakeDTO dto) {
        int cakeId = dto.getCakesId();
        int quantity = 0;
        if (this.cart.containsKey(cakeId)) {
            quantity = this.cart.get(cakeId).getQuantity();
        }
        return quantity;
    }

    public void delete(int id) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(id)) {
            this.cart.remove(id);
        }
    }

    public void update(CakeDTO dto) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(dto.getCakesId())) {
            this.cart.replace(dto.getCakesId(), dto);
        }
    }
}
