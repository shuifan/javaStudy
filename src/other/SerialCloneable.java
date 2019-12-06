package other;

import java.io.*;

/**
 * @author fandong
 * @create 2018/5/7
 */
public class SerialCloneable implements Serializable, Cloneable{

    @Override
    public Object clone(){
        try {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bout);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bout.toByteArray()));
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        }catch (Exception e){
            return null;
        }
    }
}
