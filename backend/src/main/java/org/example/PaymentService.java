package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void generateBill(User user, Barber barber, Offer offer) {
        Payment payment = new Payment();
        payment.generateBill(user, barber, offer);
        paymentRepository.save(payment);
    }
}
