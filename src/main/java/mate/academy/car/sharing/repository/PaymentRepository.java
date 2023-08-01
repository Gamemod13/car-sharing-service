package mate.academy.car.sharing.repository;

import mate.academy.car.sharing.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
