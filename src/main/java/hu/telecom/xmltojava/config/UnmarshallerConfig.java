package hu.telecom.xmltojava.config;

import generated.User;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.InputStream;

@Configuration
public class UnmarshallerConfig {

    @Bean(name = "UserUnmarshaller")
    public Unmarshaller createUnmarshaller() throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(User.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

        InputStream schemaStream = getClass().getClassLoader().getResourceAsStream("xsd/users.xsd");
        Schema schema = schemaFactory.newSchema(new StreamSource(schemaStream));

        unmarshaller.setSchema(schema);
        return unmarshaller;
    }
}
