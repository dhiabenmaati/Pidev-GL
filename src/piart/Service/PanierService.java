/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import piart.Entities.Panier;
import piart.Entities.Produit;

/**
 *
 * @author Amine
 */
public class PanierService {
    
    private static Map panier = new HashMap();
    
    public void add(Produit p) {
        int qte = (int) panier.getOrDefault(p, 0);
        panier.put(p, qte + 1);
    }
    
    public List<Panier> getPanierItems() {
        List<Panier> panierItems = new ArrayList<>();
        Set set = panier.entrySet(); 
        Iterator itr = set.iterator();  
        while(itr.hasNext()){  
            Map.Entry entry=(Map.Entry)itr.next();  
            Produit p = (Produit)entry.getKey();
            int qte = (int)entry.getValue();
            Panier item = new Panier(p.getNom_prod(), p.getPrix_prod(), qte);
            panierItems.add(item);
        }  
        return panierItems;
    }
}