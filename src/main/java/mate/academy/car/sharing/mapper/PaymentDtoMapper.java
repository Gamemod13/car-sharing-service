package mate.academy.car.sharing.mapper;

import mate.academy.car.sharing.dto.request.PaymentRequestDto;
import mate.academy.car.sharing.dto.response.PaymentResponseDto;
import mate.academy.car.sharing.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentDtoMapper {

    public PaymentResponseDto toDto(Payment payment) {
        PaymentResponseDto responseDto = new PaymentResponseDto();
        responseDto.setId(payment.getId());
        responseDto.setStatus(payment.getStatus());
        responseDto.setType(payment.getType());
        responseDto.setSessionUrl(payment.getSessionUrl());
        responseDto.setAmountToPay(payment.getAmountToPay());
        responseDto.setRentalId(payment.getRental().getId());
        return responseDto;
    }

    public Payment toModel(PaymentRequestDto requestDto) {
        Payment payment = new Payment();
        payment.setStatus(requestDto.getStatus());
        payment.setType(requestDto.getType());
        payment.setSessionUrl(requestDto.getSessionUrl());
        payment.setAmountToPay(requestDto.getAmountToPay());
        return payment;
    }
}
