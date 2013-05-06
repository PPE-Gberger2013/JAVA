import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class XMLTools {

    private XMLTools() {}
	
    /**
     * Serialisation d'un objet dans un fichier
     * @param object objet a serialiser
     * @param filename chemin du fichier
     */
    public static void encodeToFile(Object object, String fileName) throws FileNotFoundException, IOException, IntrospectionException {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName))) {
            
            // On récupère le BeanInfo de la classe User
            BeanInfo info = Introspector.getBeanInfo(Emplacement.class);

            // On récupère les PropertyDescriptors de la classe User via le BeanInfo
            PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();

            for (PropertyDescriptor descriptor : propertyDescriptors) {

                // On met la propriété "transient" à vrai pour le PropertyDescriptor de l'attribut "password"
                if (descriptor.getName().equals("centerX")
                || descriptor.getName().equals("centerY")
                || descriptor.getName().equals("height")        
                ) {descriptor.setValue("transient", Boolean.TRUE);}

            }
            // serialisation de l'objet
            encoder.writeObject(object);
            encoder.flush();
        }
    }
    
    public static Object decodeFromFile(String fileName) throws FileNotFoundException, IOException {
        Object object = null;
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName))) {
            // deserialisation de l'objet
            object = decoder.readObject();
        }
        return object;
    }
}