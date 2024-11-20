package civa.shared.exception;

/**
 * Clase que representa una excepción de recurso no encontrado (404)
 * @author Leonardo Lopez
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
