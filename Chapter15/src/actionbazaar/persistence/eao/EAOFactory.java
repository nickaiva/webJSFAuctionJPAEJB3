package actionbazaar.persistence.eao;

public abstract class EAOFactory {
    public EAOFactory() {
        super();
    }

  public static final EAOFactory JPA = new JPAEAOFactory();
  public abstract ItemEAO getItemEAO();
  public abstract BidEAO getBidEAO();

}
