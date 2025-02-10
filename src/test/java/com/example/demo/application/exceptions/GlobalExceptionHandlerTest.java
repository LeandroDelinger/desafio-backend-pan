package com.example.demo.application.exceptions;

import com.example.demo.application.exceptions.dto.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @Mock
    private HttpServletRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exceptionHandler = new GlobalExceptionHandler();
        when(request.getRequestURI()).thenReturn("/test-path");
    }


    @Test
    void shouldHandleClientNotFoundException() {
        ClientNotFoundException exception = new ClientNotFoundException("Client not found");

        ResponseEntity<Object> response = exceptionHandler.handleClientNotFoundException(exception, request);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponseDTO);
        assertEquals("Not Found", ((ErrorResponseDTO) response.getBody()).getError());
        assertEquals("Client not found", ((ErrorResponseDTO) response.getBody()).getMessage());
    }

    @Test
    void shouldHandleBadRequestException() {
        HttpClientErrorException.BadRequest badRequestException = mock(HttpClientErrorException.BadRequest.class);
        when(badRequestException.getMessage()).thenReturn("Invalid Request");

        ResponseEntity<ErrorResponseDTO> response = exceptionHandler.handleBadRequest(badRequestException, request);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad Request", response.getBody().getError());
        assertEquals("Invalid Request", response.getBody().getMessage());
    }

    @Test
    void shouldHandleCepNotFoundException() {
        CepNotFoundException exception = new CepNotFoundException("CEP not found");

        ResponseEntity<Object> response = exceptionHandler.handleCepNotFoundException(exception, request);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Not Found", ((ErrorResponseDTO) response.getBody()).getError());
        assertEquals("CEP not found", ((ErrorResponseDTO) response.getBody()).getMessage());
    }

    @Test
    void shouldHandleClientAlreadyExistsException() {
        ClientAlreadyExistsException exception = new ClientAlreadyExistsException("Client already exists");

        ResponseEntity<Object> response = exceptionHandler.handleClientAlreadyExistsException(exception, request);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Bad Request", ((ErrorResponseDTO) response.getBody()).getError());
        assertEquals("Client already exists", ((ErrorResponseDTO) response.getBody()).getMessage());
    }

    @Test
    void shouldHandleValidationErrors() {
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("objectName", "campo1", "Campo obrigatório"),
                new FieldError("objectName", "campo2", "Formato inválido")
        ));
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        Map<String, String> response = exceptionHandler.handleValidationErrors(exception, request);

        assertNotNull(response);
        assertEquals(response.size(), 2);
    }

    @Test
    void shouldHandleGenericException() {
        Exception exception = new Exception("Unexpected error");

        ResponseEntity<Object> response = exceptionHandler.handleGenericException(exception, request);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal Server Error", ((ErrorResponseDTO) response.getBody()).getError());
        assertEquals("Ocorreu um erro inesperado", ((ErrorResponseDTO) response.getBody()).getMessage());
    }
}
