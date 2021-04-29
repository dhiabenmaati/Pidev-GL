/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piart.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Token;
import com.stripe.param.CustomerRetrieveParams;
import com.stripe.param.PaymentIntentCreateParams;
import java.util.HashMap;
import java.util.Map;
import piart.Entities.Users;

/**
 *
 * @author Amine
 */
public class StripeService {
    
    public StripeService() {
        Stripe.apiKey = "sk_test_51Il3FoAujkDiX9jOITFmeNmdkg5gcURWXhYfIC5EMxuTu1lgRwksqqCS8E2i4TO5OZJBEmx1xgzKzF8oObD1lwVI00bScikJkR";
    }
    
    public void addClient(Users user) throws StripeException {
        if(user.getstripID() == null) {
            Map<String, Object> customerParameter = new HashMap<>();
            customerParameter.put("email", user.getEmail());
            Customer newCustomer = Customer.create(customerParameter);
            UserService us = new UserService();
            us.update_stripe_id(user, newCustomer.getId());
        }
        
    }
    
    public void addCard(Users user, Map<String, Object> cardParam) throws StripeException {
        Map<String, Object> tokenParam = new HashMap<>();
        tokenParam.put("card", cardParam);
        Token token =  Token.create(tokenParam);
        Map<String, Object> source = new HashMap<>();
        source.put("source", token.getId());
        CustomerRetrieveParams retrieveParams = CustomerRetrieveParams.builder().addExpand("sources").build();
        Customer customer = Customer.retrieve(user.getstripID(), retrieveParams, null);
        Card card = (Card) customer.getSources().create(source);
        customer.setDefaultSource(card.getId());
    }
    
    public void chargeClient(Users user, Integer amount) throws StripeException {
        Map<String, Object> chargeParam = new HashMap<>();
        chargeParam.put("amount", amount);
        chargeParam.put("currency", "usd");
        chargeParam.put("customer", user.getstripID());
        Charge charge = Charge.create(chargeParam);
    }
    
    public void sendRecipt(Users user, Long amount) throws StripeException {
        PaymentIntentCreateParams params =
        PaymentIntentCreateParams.builder()
          .setAmount(amount)
          .setCurrency("usd")
          .addPaymentMethodType("card")
          .setReceiptEmail("aminedahmen14@gmail.com")
          .build();

      PaymentIntent paymentIntent = PaymentIntent.create(params);

    }

}
