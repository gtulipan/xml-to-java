package hu.telecom.xmltojava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

@ControllerAdvice
public class XmlToJavaExceptionHandler {
    @ExceptionHandler(UnmarshalException.class)
    public ResponseEntity<String> handleValidationException(UnmarshalException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid XML: " + e.getMessage());
    }

    @ExceptionHandler(JAXBException.class)
    public ResponseEntity<String> handleJAXBException(JAXBException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not valid User XML: " + e.getMessage());
    }
}
