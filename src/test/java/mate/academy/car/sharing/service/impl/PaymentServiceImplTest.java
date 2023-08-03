package mate.academy.car.sharing.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.car.sharing.entity.Payment;
import mate.academy.car.sharing.entity.User;
import mate.academy.car.sharing.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addPaymentTest() {
        Payment payment = new Payment();
        payment.setId(1L);
        when(paymentRepository.save(payment)).thenReturn(payment);
        Payment addedPayment = paymentService.add(payment);
        assertNotNull(addedPayment);
        assertEquals(payment, addedPayment);
    }

    @Test
    void getByIdExistingPaymentTest() {
        Long paymentId = 1L;
        Payment payment = new Payment();
        payment.setId(paymentId);
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));
        Payment retrievedPayment = paymentService.getById(paymentId);
        assertNotNull(retrievedPayment);
        assertEquals(payment, retrievedPayment);
    }

    @Test
    void getByIdNonExistingPaymentTest() {
        Long paymentId = 1L;
        when(paymentRepository.findById(paymentId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> paymentService.getById(paymentId));
    }

    @Test
    void getAllTest() {
        Payment payment1 = new Payment();
        Payment payment2 = new Payment();
        List<Payment> paymentList = List.of(payment1, payment2);
        when(paymentRepository.findAll()).thenReturn(paymentList);
        List<Payment> retrievedPayments = paymentService.getAll();
        assertNotNull(retrievedPayments);
        assertEquals(2, retrievedPayments.size());
        assertEquals(payment1, retrievedPayments.get(0));
        assertEquals(payment2, retrievedPayments.get(1));
    }

    @Test
    void deleteTest() {
        Long paymentId = 1L;
        paymentService.delete(paymentId);
        verify(paymentRepository).deleteById(paymentId);
    }

    @Test
    void updatePaymentTest() {
        Payment payment = new Payment();
        payment.setId(1L);
        when(paymentRepository.save(payment)).thenReturn(payment);
        Payment updatedPayment = paymentService.update(payment);
        assertNotNull(updatedPayment);
        assertEquals(payment, updatedPayment);
    }

    @Test
    void getByUserExistingPaymentTest() {
        User user = new User();
        Payment payment = new Payment();
        payment.setId(1L);
        when(paymentRepository.getByUser(user)).thenReturn(Optional.of(payment));
        Payment retrievedPayment = paymentService.getByUser(user);
        assertNotNull(retrievedPayment);
        assertEquals(payment, retrievedPayment);
    }

    @Test
    void getByUserNonExistingPaymentTest() {
        User user = new User();
        when(paymentRepository.getByUser(user)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> paymentService.getByUser(user));
    }

    @Test
    void findBySessionIdExistingPaymentTest() {
        String sessionId = "testSessionId";
        Payment payment = new Payment();
        payment.setId(1L);
        when(paymentRepository.findBySessionId(sessionId)).thenReturn(Optional.of(payment));
        Payment retrievedPayment = paymentService.findBySessionId(sessionId);
        assertNotNull(retrievedPayment);
        assertEquals(payment, retrievedPayment);
    }

    @Test
    void findBySessionIdNonExistingPaymentTest() {
        String sessionId = "testSessionId";
        when(paymentRepository.findBySessionId(sessionId)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> paymentService.findBySessionId(sessionId));
    }
}
