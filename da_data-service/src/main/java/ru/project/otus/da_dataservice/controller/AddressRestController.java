package ru.project.otus.da_dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.project.otus.da_dataservice.model.Suggestion;
import ru.project.otus.da_dataservice.model.response_model.Data;
import ru.project.otus.da_dataservice.service.address.AddressService;

@Slf4j
@Tag(name = "Address Controller", description = "Контроллер для получения информации по городу его идентификатора " +
        "в службе доставки и почтовых отделений")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressRestController {
    private final AddressService addressService;

    /**
     * Геокодирование (координаты по адресу)
     * @param search - строка поиска
     * @return Массив адресов
     */
    @Operation(summary = "Геокодирование (координаты по адресу)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос успешно обработан",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Data.class)) }),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = { @Content(mediaType = "application/text",
                    schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "401", description = "В запросе отсутствует API-ключ или секретный ключ\n" +
                    "Или в запросе указан несуществующий ключ",
                    content = { @Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "403", description = "Не подтверждена почта\n" +
                    "или недостаточно средств для обработки запроса, пополните баланс",
                    content = { @Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "5xx", description = "Произошла внутренняя ошибка сервиса", content = @Content)})
    @GetMapping("/addressBySource")
    public final Mono<Data[]> findBySource(@RequestParam("search") String search) {
        log.info("findBySource request :: {}", search);
        var result = addressService.findBySource(search);
        log.info("findBySource response :: {}", result);
        return result;
    }

    /**
     * Обратное геокодирование (адрес по координатам)
     * @param body Координаты (lat, lon)
     * @return Геокоординаты обекта
     */
    @Operation(summary = "Обратное геокодирование (адрес по координатам)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос успешно обработан",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Suggestion.class)) }),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = { @Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "401", description = "В запросе отсутствует API-ключ или секретный ключ\n" +
                    "Или в запросе указан несуществующий ключ",
                    content = { @Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "403", description = "Не подтверждена почта\n" +
                    "или недостаточно средств для обработки запроса, пополните баланс",
                    content = { @Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "5xx", description = "Произошла внутренняя ошибка сервиса", content = @Content)})
    @PostMapping("/addressByGeo")
    public final Mono<Suggestion> findByGeo(@RequestBody String body) {
        log.info("findByGeo request :: {}", body);
        var result = addressService.findByGeo(body);
        log.info("findByGeo response :: {}", result);
        return result;
    }

    /**
     *  Идентификатор города в СДЭК, BoxBerry и DPD
     * @param body КЛАДР-код города
     * @return Идентификатор города в службе доставке
     */
    @Operation(summary = "Идентификатор города в СДЭК, BoxBerry и DPD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос успешно обработан",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Suggestion.class)) }),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = { @Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "401", description = "В запросе отсутствует API-ключ или секретный ключ\n" +
                    "Или в запросе указан несуществующий ключ",
                    content = { @Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "403", description = "Не подтверждена почта\n" +
                    "или недостаточно средств для обработки запроса, пополните баланс",
                    content = { @Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "5xx", description = "Произошла внутренняя ошибка сервиса", content = @Content)})
    @PostMapping("/findById/delivery")
    public Mono<Suggestion> findDelivery(@RequestBody String body) {
        log.info("findDelivery request :: {}", body);
        var result = addressService.findDeliveryId(body);
        log.info("findDelivery response :: {}", result);
        return result;
    }

    /**
     * Отделения Почты России
     * @param body Координаты (индекс, адрес)
     * @return Справочник почтовых отделений с точным адресом,
     * координатами и часы работы отделения, а ещё отметка, если оно временно закрыто
     */
    @Operation(summary = "Отделения Почты России")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос успешно обработан",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Suggestion.class)) }),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = { @Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "401", description = "В запросе отсутствует API-ключ или секретный ключ\n" +
                    "Или в запросе указан несуществующий ключ",
                    content = { @Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "403", description = "Не подтверждена почта\n" +
                    "или недостаточно средств для обработки запроса, пополните баланс",
                    content = { @Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class)) }),
            @ApiResponse(responseCode = "5xx", description = "Произошла внутренняя ошибка сервиса", content = @Content)})
    @PostMapping("/findById/postal_unit")
    public Mono<Suggestion> findPostalOffice(@RequestBody String body) {
        log.info("findPostalOffice request :: {}", body);
        var result = addressService.findPostalOffice(body);
        log.info("findPostalOffice response :: {}", result);
        return result;
    }
}
