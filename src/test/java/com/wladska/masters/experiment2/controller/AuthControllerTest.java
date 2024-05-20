package com.wladska.masters.experiment2.controller;
import com.wladska.masters.experiment2.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private Map<String, String> credentials;

    @BeforeEach
    void setUp() {
        credentials = new HashMap<>();
        credentials.put("username", "testUser");
        credentials.put("password", "testPass");
    }

    @Test
    void authenticateUser_Success() throws Exception {
        when(authService.authenticate("testUser", "testPass")).thenReturn("token123");

        ResponseEntity<?> response = authController.authenticateUser(credentials);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());

        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertEquals("testUser", responseBody.get("username"));
        assertEquals("token123", responseBody.get("token"));
    }

    @Test
    void authenticateUser_Failure() throws Exception {
        when(authService.authenticate("testUser", "testPass")).thenThrow(new RuntimeException("Authentication failed"));

        ResponseEntity<?> response = authController.authenticateUser(credentials);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Authentication failed", response.getBody());
    }
}
