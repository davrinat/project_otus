package ru.project.otus.da_dataservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.project.otus.da_dataservice.model.Suggestion;
import ru.project.otus.da_dataservice.service.company.CompanyService;

@Slf4j
@Tag(name = "Company Controller", description = "Контроллер для получения информации о компаниях по ИНН или ОГРН, " +
        "а так же по адресу электронной почты")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
public class CompanyRestController {
    private final CompanyService companyService;

    /**
     * Находит компанию или ИП по ИНН или ОГРН
     *
     * @param body ИНН или ОГРН организации
     * @return Организация по ИНН или ОГРН
     */
    @Operation(summary = "Находит компанию или ИП по ИНН или ОГРН)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос успешно обработан",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Suggestion.class))}),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = {@Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "401", description = "В запросе отсутствует API-ключ или секретный ключ\n" +
                    "Или в запросе указан несуществующий ключ",
                    content = {@Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "403", description = "Не подтверждена почта\n" +
                    "или недостаточно средств для обработки запроса, пополните баланс",
                    content = {@Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "5xx", description = "Произошла внутренняя ошибка сервиса", content = @Content)})
    @PostMapping("/findById/party")
    public Mono<Suggestion> findByInn(@RequestBody String body) {
        log.info("findByInn request :: {}", body);
        var result = companyService.findByInn(body);
        log.info("findByInn response :: {}", result);
        return result;
    }

    /**
     * Поиск аффилированных компаний по ИНН учредителей и/или руководителей
     *
     * @param body ИНН учредителя или руководителя компании
     * @return Организации зарегистрированные на учредителей и/или руководителей
     */
    @Operation(summary = "Поиск аффилированных компаний по ИНН учредителей и/или руководителей")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос успешно обработан",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Suggestion.class))}),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = {@Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "401", description = "В запросе отсутствует API-ключ или секретный ключ\n" +
                    "Или в запросе указан несуществующий ключ",
                    content = {@Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "403", description = "Не подтверждена почта\n" +
                    "или недостаточно средств для обработки запроса, пополните баланс",
                    content = {@Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "5xx", description = "Произошла внутренняя ошибка сервиса", content = @Content)})
    @PostMapping("/findAffiliated/party")
    public Mono<Suggestion> findAffiliated(@RequestBody String body) {
        log.info("findAffiliated request :: {}", body);
        var result = companyService.findAffiliated(body);
        log.info("findAffiliated response :: {}", result);
        return result;
    }

    /**
     * Находит компанию по адресу электронной почты
     *
     * @param body Адрес электронной почты
     * @return Компания которой принадлежит данная почта
     */
    @Operation(summary = "Находит компанию по адресу электронной почты")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос успешно обработан",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Suggestion.class))}),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос",
                    content = {@Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "401", description = "В запросе отсутствует API-ключ или секретный ключ\n" +
                    "Или в запросе указан несуществующий ключ",
                    content = {@Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "403", description = "Не подтверждена почта\n" +
                    "или недостаточно средств для обработки запроса, пополните баланс",
                    content = {@Content(mediaType = "application/text",
                            schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "5xx", description = "Произошла внутренняя ошибка сервиса", content = @Content)})
    @PostMapping("/findByEmail/company")
    public Mono<Suggestion> findByEmail(@RequestBody String body) {
        log.info("findByEmail request :: {}", body);
        var result = companyService.findByEmail(body);
        log.info("findByEmail response :: {}", result);
        return result;
    }
}
