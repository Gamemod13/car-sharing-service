package mate.academy.car.sharing.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.entity.Payment;
import mate.academy.car.sharing.repository.PaymentRepository;
import mate.academy.car.sharing.service.PaymentService;
import org.springframework.stereotype.Service;

;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Payment add(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find payment by id: " + id));
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment update(Payment payment) {
        //TODO:Check/Update
        return paymentRepository.save(payment);
    }
}
