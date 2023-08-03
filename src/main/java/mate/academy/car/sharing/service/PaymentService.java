package mate.academy.car.sharing.service;

import mate.academy.car.sharing.entity.Payment;
import mate.academy.car.sharing.entity.User;

public interface PaymentService extends AbstractService<Payment> {
    Payment getByUser(User user);

    Payment findBySessionId(String sessionId);
}
