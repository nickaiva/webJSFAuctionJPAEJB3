package actionbazaar.buslogic;


import actionbazaar.persistence.Image;

import java.lang.Double;

import java.util.List;

import javax.ejb.Local;


@Local
public interface PlaceImageLocal {
    
    public int placeImage(int objectId,
                                 String description,
                                 byte[] image) ;
    
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    public List<byte[]> getImagesByItemId(Long itemId) ;
  
    Image persistImage(Image image);

    Image mergeImage(Image image);

    void removeImage(Image image);

    List<Image> getImageFindAll();
}
