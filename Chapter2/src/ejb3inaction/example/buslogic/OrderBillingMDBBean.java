package ejb3inaction.example.buslogic;

import ejb3inaction.example.persistence.Order;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

//@MessageDriven(mappedName = "jms/OrderBillingQueue")
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue")
        },mappedName="jms/OrderBillingQueue")
public class OrderBillingMDBBean implements MessageListener {
    public void onMessage(Message message) {
    
      try {

                 ObjectMessage objectMessage = (ObjectMessage) message;
                 Order order = (Order) objectMessage.getObject();

                 try {
                     bill(order);
                     notifyBillingSuccess(order);
                     order.setOrderStatus(OrderStatus.COMPLETE.toString());
                 } catch (BillingException be) {
                     notifyBillingFailure(be, order);
                     order.setOrderStatus(OrderStatus.BILLING_FAILED.toString());
                 } finally {
                     update(order);
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
    }
    
  private void bill(Order order) {
         System.out.println("Billing Completed by MDB ..");
         System.out.println("A/c No:"
                 + order.getBillingInfo().getAccountNo() + " charged..");
     }


     private void update(Order order) {

     }


     private void notifyBillingSuccess(Order order) {
     }


     private void notifyBillingFailure(BillingException be, Order order) {
     }

}
