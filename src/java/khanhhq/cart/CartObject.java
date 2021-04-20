/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import khanhhq.dtos.TblItemDTO;
 
/**
 *
 * @author Administrator
 */
public class CartObject implements Serializable {
//      private Map<String,Integer> items;
//     public  Map<String,Integer> getItems(){
//         return items;
//     }
//     public void addtemToCart(String title){
//         //1.Check items existed
//         if(this.items == null){
//             this.items = new HashMap<>();
//         }
//         int quality = 1;
//         //2. Check items not existed
//         if(this.items.containsKey(title)){
//             quality = this.items.get(title) +1;
//         }
//         
//         this.items.put(title, quality);
//     }
//     public void removeItemFromCart(String title){
//         //1. Check items existed
//         if( this.items == null){
//             return;
//         }
//         //2.Check item existed
//         if(this.items.containsKey(title)){
//             this.items.remove(title);
//             if(this.items.isEmpty()){
//                 this.items = null;
//             }
//         }
//     }

     private Map<TblItemDTO, Integer> items;

    public Map<TblItemDTO, Integer> getItems() {
        return items;
    }

    public void addtemToCart(TblItemDTO dto) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        int quantity = 1;
        boolean isExist = false;
        if (dto != null) {
           
            for (TblItemDTO car : items.keySet()) {
                if (car.getItemID().equals(dto.getItemID())) {
                    quantity = this.items.get(car);
                    isExist = true;
                    this.items.replace(car, quantity + 1);
                    break;
                } 
            }
            if (!isExist) {               
                this.items.put(dto, quantity);
            }
        }

    }

    public void removeItemFromCart(String id) {
        if (this.items == null) {
            return;
        }
        for (TblItemDTO car : items.keySet()) {
            if (car.getItemID().equals(id)) {
                this.items.remove(car);
                if (this.items.isEmpty()) {
                    this.items = null;
                }
                break;
            }
        }

    }
}
