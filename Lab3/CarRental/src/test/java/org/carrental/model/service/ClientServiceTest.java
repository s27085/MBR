package org.carrental.model.service;

import org.carrental.exception.ValidationException;
import org.carrental.model.client.Client;
import org.carrental.model.client.Gender;
import org.carrental.model.repository.CarRepository;
import org.carrental.model.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ClientServiceTest {
    private ClientService clientService;
    @BeforeEach
    void setUp() {
        clientService = new ClientService(new ClientRepository());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidClientCredentials")
    void shouldNotRegisterNewClient(Client client, String message) {
        ValidationException result = Assertions.assertThrows(ValidationException.class, () -> clientService.register(client));
        Assertions.assertEquals(result.getMessage(), message);
    }

    public static Stream<Arguments> provideInvalidClientCredentials() {
        return Stream.of(
                Arguments.of(new Client(null, null, Gender.FEMALE), "Cannot be null or blank"),
                Arguments.of(new Client(null, "grzegrzółka", null), "Cannot be null or blank")
        );
    }
}