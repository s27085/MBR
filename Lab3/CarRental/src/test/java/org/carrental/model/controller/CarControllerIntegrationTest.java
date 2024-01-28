package org.carrental.model.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.carrental.model.car.Car;
import org.carrental.model.car.CarClass;
import org.carrental.model.car.CarStatus;
import org.carrental.model.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
class CarControllerIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ObjectMapper objectMapper;
    private Car car = new Car(null, "volkswagen", "bleble", "12A", CarStatus.AVAILABLE, CarClass.PREMIUM, 900);

    @BeforeEach
    void setUp() {
        carRepository.removeAll();
    }
    public static Stream<Arguments> provideCarId(){
        return Stream.of(
                Arguments.of(0),
                Arguments.of(1)
        );
    }
    @Test
     void should_createnewcar() {
         try {
             String serializedCar = objectMapper.writeValueAsString(car);
             Car result = webTestClient.post().uri("/car/create")
                     .contentType(MediaType.APPLICATION_JSON)
                     .bodyValue(serializedCar)
                     .exchange()
                     .expectStatus().isCreated()
                     .expectBody(Car.class)
                     .returnResult().getResponseBody();
             Optional<Car> carById = carRepository.getCarById(result.getId());
             assertEquals(1, (long) carRepository.GetAll().size());

         }catch (Exception e){
             e.printStackTrace();
         }
     }
    @Test
    void should_shouldReturnAllCar(){
        Car newCar = webTestClient.post().uri("/car/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(car)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Car.class)
                .returnResult().getResponseBody();
        Car car2 = new Car(null, "volkswagen", "bleble", "12A", CarStatus.AVAILABLE, CarClass.PREMIUM, 900);
        carRepository.create(car);
        webTestClient.get().uri("/car/all");
        assertEquals(carRepository.GetAll().size(), 2);
    }

    @ParameterizedTest
    @MethodSource("provideCarId")
    void should_getCarById(int iid){
        carRepository.create(car);
        Car car2 = new Car(null, "volkswagen", "bleble", "12A", CarStatus.AVAILABLE, CarClass.PREMIUM, 900);
        carRepository.create(car2);
        Car result = webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/car")
                        .queryParam("id", iid)
                        .build()
                )
        .exchange()
        .expectStatus().isOk()
        .expectBody(Car.class)
        .returnResult()
        .getResponseBody();
        assertEquals(carRepository.getCarById(iid).get(), result);
    }
    @Test
    void should_getCarByPathVariable() throws JsonProcessingException {
        Car car0 = new Car(null, null, "bleble", "12A", CarStatus.AVAILABLE, CarClass.PREMIUM, 900);
        carRepository.create(car0);
        String serializedcar = objectMapper.writeValueAsString(car0);
        String result = webTestClient.post()
                .uri("/car/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(serializedcar)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        //read error message from reply
        assertEquals("{\"message\":\"make Cannot be null or blank\"}", result);
    }
}