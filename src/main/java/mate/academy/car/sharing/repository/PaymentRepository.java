package mate.academy.car.sharing.repository;

import mate.academy.car.sharing.entity.Payment;
import mate.academy.car.sharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(nativeQuery = false, value = "FROM Payment p "
                                        + "JOIN FETCH p.rental r "
                                        + "JOIN FETCH r.user u "
                                        + "JOIN FETCH r.car c "
                                        + "WHERE u = :user")
    Optional<Payment> getByUser(User user);

    @Query(nativeQuery = false, value = "FROM Payment p "
                                        + "JOIN FETCH p.rental r "
                                        + "JOIN FETCH r.user u "
                                        + "JOIN FETCH r.car c "
                                        + "WHERE p.sessionId = :sessionId")
    Optional<Payment> findBySessionId(String sessionId);
}
