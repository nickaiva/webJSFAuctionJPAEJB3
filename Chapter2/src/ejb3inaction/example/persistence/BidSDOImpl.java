package ejb3inaction.example.persistence;

import org.eclipse.persistence.sdo.SDODataObject;

public class BidSDOImpl extends SDODataObject implements BidSDO {

   public static final int START_PROPERTY_INDEX = 0;

   public static final int END_PROPERTY_INDEX = START_PROPERTY_INDEX + 5;

   public BidSDOImpl() {}

   public java.lang.String getBidBidder() {
      return getString(START_PROPERTY_INDEX + 0);
   }

   public void setBidBidder(java.lang.String value) {
      set(START_PROPERTY_INDEX + 0 , value);
   }

   public java.sql.Timestamp getBidDate() {
      return (java.sql.Timestamp)get(START_PROPERTY_INDEX + 1);
   }

   public void setBidDate(java.sql.Timestamp value) {
      set(START_PROPERTY_INDEX + 1 , value);
   }

   public long getBidId() {
      return getLong(START_PROPERTY_INDEX + 2);
   }

   public void setBidId(long value) {
      set(START_PROPERTY_INDEX + 2 , value);
   }

   public long getBidItemId() {
      return getLong(START_PROPERTY_INDEX + 3);
   }

   public void setBidItemId(long value) {
      set(START_PROPERTY_INDEX + 3 , value);
   }

   public double getBidPrice() {
      return getDouble(START_PROPERTY_INDEX + 4);
   }

   public void setBidPrice(double value) {
      set(START_PROPERTY_INDEX + 4 , value);
   }

   public java.lang.String getBidStatus() {
      return getString(START_PROPERTY_INDEX + 5);
   }

   public void setBidStatus(java.lang.String value) {
      set(START_PROPERTY_INDEX + 5 , value);
   }


}

