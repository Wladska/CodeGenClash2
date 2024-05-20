package com.wladska.masters.experiment2.service;
import com.wladska.masters.experiment2.config.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    private AuthService authService;

    private Authentication authentication;
    private String username = "user";
    private String password = "password";
    private String expectedToken = "mockedToken";

    @BeforeEach
    void setUp() {
        authentication = new UsernamePasswordAuthenticationToken(username, null);
    }

    @Test
    void authenticate_ShouldReturnToken_WhenCredentialsAreValid() {
        // Arrange
        given(authenticationManager.authenticate(any(Authentication.class))).willReturn(authentication);
        given(jwtTokenProvider.generateToken(authentication)).willReturn(expectedToken);

        // Act
        String resultToken = authService.authenticate(username, password);

        // Assert
        assertNotNull(resultToken);
        assertEquals(expectedToken, resultToken);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider).generateToken(authentication);
    }

    @Test
    void authenticate_ShouldThrowException_WhenCredentialsAreInvalid() {
        // Arrange
        given(authenticationManager.authenticate(any(Authentication.class)))
                .willThrow(RuntimeException.class);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> authService.authenticate(username, password));
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtTokenProvider, never()).generateToken(any(Authentication.class));
    }
}
