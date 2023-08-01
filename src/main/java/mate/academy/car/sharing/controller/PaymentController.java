package mate.academy.car.sharing.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.car.sharing.dto.response.PaymentResponseDto;
import mate.academy.car.sharing.mapper.PaymentDtoMapper;
import mate.academy.car.sharing.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentDtoMapper paymentDtoMapper;

    public PaymentController(PaymentService paymentService, PaymentDtoMapper paymentDtoMapper) {
        this.paymentService = paymentService;
        this.paymentDtoMapper = paymentDtoMapper;
    }

    @GetMapping
    public List<PaymentResponseDto> getAll() {
        return paymentService.getAll().stream()
                .map(paymentDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{paymentId}")
    public PaymentResponseDto get(@PathVariable Long paymentId) {
        return paymentDtoMapper.toDto(paymentService.getById(paymentId));
    }
}
