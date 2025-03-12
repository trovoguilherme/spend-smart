package br.com.skeleton.spendsmart.service;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.Installment;
import br.com.skeleton.spendsmart.entity.enums.ExpenseStatus;
import br.com.skeleton.spendsmart.entity.enums.ExpenseType;
import br.com.skeleton.spendsmart.entity.enums.PaymentType;
import br.com.skeleton.spendsmart.model.TopPayoffItem;
import br.com.skeleton.spendsmart.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private InstallmentService installmentService;

    @Mock
    private ExpenseHistoryService expenseHistoryService;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    private ExpenseService expenseService;

    @Test
    void test() {
        Mockito.when(expenseRepository.findAll()).thenReturn(List.of(
            new Expense(1L, "Logitech Superlight 2", 999.90, List.of(
                    new Installment(1, null, BigDecimal.valueOf(999.90/10), true, LocalDateTime.now(), LocalDateTime.now()),
                    new Installment(1, null, BigDecimal.valueOf(999.90/10), true, LocalDateTime.now(), LocalDateTime.now()),
                    new Installment(1, null, BigDecimal.valueOf(999.90/10), true, LocalDateTime.now(), LocalDateTime.now()),
                    new Installment(1, null, BigDecimal.valueOf(999.90/10), false, LocalDateTime.now(), LocalDateTime.now()),
                    new Installment(1, null, BigDecimal.valueOf(999.90/10), false, LocalDateTime.now(), LocalDateTime.now()),
                    new Installment(1, null, BigDecimal.valueOf(999.90/10), false, LocalDateTime.now(), LocalDateTime.now()),
                    new Installment(1, null, BigDecimal.valueOf(999.90/10), false, LocalDateTime.now(), LocalDateTime.now()),
                    new Installment(1, null, BigDecimal.valueOf(999.90/10), false, LocalDateTime.now(), LocalDateTime.now()),
                    new Installment(1, null, BigDecimal.valueOf(999.90/10), false, LocalDateTime.now(), LocalDateTime.now()),
                    new Installment(1, null, BigDecimal.valueOf(999.90/10), false, LocalDateTime.now(), LocalDateTime.now())), null, ExpenseStatus.PENDING, ExpenseType.CREDIT_CARD_BILLS, PaymentType.ITAU, LocalDateTime.now(), LocalDateTime.now()),
                new Expense(2L, "Nike janaski OG", 490.14, List.of(
                        new Installment(1, null, BigDecimal.valueOf(490.14/6), true, LocalDateTime.now(), LocalDateTime.now()),
                        new Installment(1, null, BigDecimal.valueOf(490.14/6), true, LocalDateTime.now(), LocalDateTime.now()),
                        new Installment(1, null, BigDecimal.valueOf(490.14/6), true, LocalDateTime.now(), LocalDateTime.now()),
                        new Installment(1, null, BigDecimal.valueOf(490.14/6), false, LocalDateTime.now(), LocalDateTime.now()),
                        new Installment(1, null, BigDecimal.valueOf(490.14/6), false, LocalDateTime.now(), LocalDateTime.now()),
                        new Installment(1, null, BigDecimal.valueOf(490.14/6), false, LocalDateTime.now(), LocalDateTime.now())), null, ExpenseStatus.PENDING, ExpenseType.CREDIT_CARD_BILLS, PaymentType.ITAU, LocalDateTime.now(), LocalDateTime.now()),
                new Expense(3L, "Zelo Aricanduva", 490.14, List.of(
                        new Installment(1, null, BigDecimal.valueOf(279.90/6), true, LocalDateTime.now(), LocalDateTime.now()),
                        new Installment(1, null, BigDecimal.valueOf(279.90/6), true, LocalDateTime.now(), LocalDateTime.now()),
                        new Installment(1, null, BigDecimal.valueOf(279.90/6), false, LocalDateTime.now(), LocalDateTime.now()),
                        new Installment(1, null, BigDecimal.valueOf(279.90/6), false, LocalDateTime.now(), LocalDateTime.now()),
                        new Installment(1, null, BigDecimal.valueOf(279.90/6), false, LocalDateTime.now(), LocalDateTime.now()),
                        new Installment(1, null, BigDecimal.valueOf(279.90/6), false, LocalDateTime.now(), LocalDateTime.now())), null, ExpenseStatus.PENDING, ExpenseType.CREDIT_CARD_BILLS, PaymentType.ITAU, LocalDateTime.now(), LocalDateTime.now())
                ));

        List<TopPayoffItem> topPayoffItems = expenseService.getTopPayoffItem();

        System.out.println(topPayoffItems);
    }

}
