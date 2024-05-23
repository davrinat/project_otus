package ru.project.otus.da_dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.project.otus.da_dataservice.model.Suggestion;
import ru.project.otus.da_dataservice.service.common.CommonService;

@Tag(name = "Common Controller", description = "Общий контроллер для получения информации о банке, таможенных постах и " +
        "налоговых инспекциях, а так же содержит справочники по товарам и валютам")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/common")
public class CommonRestController {
    private final CommonService commonService;

    /**
     * Находит банк по любому из идентификаторов:
     * БИК,
     * SWIFT,
     * ИНН,
     * ИНН + КПП (для филиалов),
     * регистрационному номеру, присвоенному Банком России
     * @param body Любой из перечисленных выше идентификаторов
     * @return Реквизиты банка
     */
    @Operation(summary = "Находит банк по любому из идентификаторов: БИК, SWIFT, ИНН и т.д.")
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
    @PostMapping("/findById/bank")
    public Mono<Suggestion> findBank(@RequestBody String body) {
        return commonService.findBank(body);
    }

    /**
     * Справочник инспекций Налоговой службы
     * @param body Идентификатор (наименование региона, первые 2 цифры КЛАДР-кода региона, идентификатор налоговой)
     * @return Налоговая инспекция соответствующая идентификатору
     */
    @Operation(summary = "Справочник инспекций Налоговой службы")
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
    @PostMapping("/findById/fns_unit")
    public Mono<Suggestion> findFnsUnit(@RequestBody String body) {
        return commonService.findFnsUnit(body);
    }

    /**
     * Справочник таможенных органов и постов
     * @param body Идентификатор (строка адреса, таможенный идентификатор)
     * @return Адреса таможенных постов
     */
    @Operation(summary = "Справочник таможенных органов и постов")
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
    @PostMapping("/findById/fts_unit")
    public Mono<Suggestion> findFtsUnit(@RequestBody String body) {
        return commonService.findFtsUnit(body);
    }

    /**
     * Международный классификатор товаров и услуг на трех языках (русский, английский, французский)
     * @param search Полнотекстовый поиск или поиск по идентификатору
     * @return Описание товара
     */
    @Operation(summary = "Международный классификатор товаров и услуг на трех языках (русский, английский, французский)")
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
    @GetMapping("/findBySource/goods")
    public Mono<Suggestion> findGoodsBySource(@RequestParam("search") String search) {
        return commonService.findGoodsBySource(search);
    }

    /**
     * Справочник валют по стандарту ISO 4217
     * @param search Строка поиска
     * @return Справочная информация по текущей валюте
     */
    @Operation(summary = "Справочник валют по стандарту ISO 4217")
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
    @GetMapping("/findBySource/currency")
    public Mono<Suggestion> findCurrency(@RequestParam("search") String search) {
        return commonService.findCurrency(search);
    }
}
