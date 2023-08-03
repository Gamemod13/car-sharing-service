package mate.academy.car.sharing.service.impl;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.car.sharing.entity.Rental;
import mate.academy.car.sharing.service.RentalService;
import mate.academy.car.sharing.service.TelegramNotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledTelegramNotificationServiceImpl {
    private static final String SCHEDULE = "0 0 9 * * *";

    private final TelegramNotificationService telegramNotificationService;
    private final RentalService rentalService;

    @Scheduled(cron = SCHEDULE)
    public void reportCurrentTime() {
        String overdueRentals = rentalService.getOverdueRentals().stream()
                .map(this::createRentalInfo)
                .collect(Collectors.joining(",\n"));
        String actualMessage = (overdueRentals.isBlank()) ? "No rentals overdue today!" :
                overdueRentals;
        telegramNotificationService.sendMessageToAdminChat(actualMessage);
    }

    private String createRentalInfo(Rental rental) {
        return rental.getCar().getBrand() + " " + rental.getCar().getModel() + " "
                + "had to be returned at " + rental.getReturnDate() + " by user "
                + rental.getUser().getFirstName() + " " + rental.getUser().getLastName()
                + " with email '" + rental.getUser().getEmail() + "'";
    }
}
