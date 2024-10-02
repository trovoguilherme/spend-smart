package br.com.skeleton.spendsmart.mapper;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.Installment;
import br.com.skeleton.spendsmart.resource.request.ExpenseRequest;
import br.com.skeleton.spendsmart.resource.request.UpdateExpenseRequest;
import br.com.skeleton.spendsmart.resource.response.ExpenseDetailResponse;
import br.com.skeleton.spendsmart.resource.response.ExpenseResponse;
import br.com.skeleton.spendsmart.resource.response.ExpenseSummaryResponse;
import br.com.skeleton.spendsmart.resource.response.InstallmentDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseMapper {

    @Mapping(target = "installments", source = "source", qualifiedByName = "mapInstallments")
    Expense toExpense(ExpenseRequest source);

    Expense toExpense(UpdateExpenseRequest source);

    @Mapping(target = "username", source = "source.user.username")
    ExpenseResponse toExpenseResponse(Expense source);

    @Mapping(target = "installmentDetailResponse", source = "source", qualifiedByName = "mapInstallmentDetailResponse")
    ExpenseDetailResponse toExpenseDetailResponse(Expense source);

    default ExpenseSummaryResponse toExpenseSummaryResponse(List<Expense> source) {
        List<ExpenseDetailResponse> expenseDetailResponseList = source.stream().map(this::toExpenseDetailResponse).toList();

        BigDecimal totalUnpaid = expenseDetailResponseList.stream()
                .map(expenseDetailResponse -> {
                    InstallmentDetailResponse installmentDetailResponse = expenseDetailResponse.getInstallmentDetailResponse();
                    if (installmentDetailResponse != null) {
                        return installmentDetailResponse.getTotalUnpaid();
                    } else {
                        return expenseDetailResponse.getValue();
                    }
                }).reduce(BigDecimal.ZERO, BigDecimal::add);

        return ExpenseSummaryResponse.builder()
                .totalUnpaid(totalUnpaid)
                .expenseDetailResponseList(expenseDetailResponseList)
                .build();
    }

    @Named("mapInstallments")
    default List<Installment> mapInstallments(ExpenseRequest expenseRequest) {

        if (expenseRequest.getInstallment() != null) {
            List<Installment> installments = new ArrayList<>();
            LocalDateTime dueDate = expenseRequest.getInstallment().getDueDay().atTime(LocalTime.MAX);

            IntStream.range(0, expenseRequest.getInstallment().getNumber())
                    .forEach(i -> {
                        installments.add(
                                Installment.builder()
                                        .value(expenseRequest.getValue().divide(BigDecimal.valueOf(expenseRequest.getInstallment().getNumber()), 2, RoundingMode.HALF_UP))
                                        .paid(false)
                                        .dueDate(dueDate.plusMonths(i))
                                        .dayOfPayment(null)
                                        .build()
                        );
                    });

            return installments;
        }

        return null;
    }

    @Named("mapInstallmentDetailResponse")
    default InstallmentDetailResponse mapInstallmentDetailResponse(Expense source) {
        if (source.getInstallments().isEmpty()) {
            return null;
        }
        return InstallmentDetailResponse.builder()
                .value(source.getInstallments().get(0).getValue())
                .amountPaid(source.getPaidInstallmentsCount())
                .amountUnpaid(source.getUnpaidInstallmentsCount())
                .totalPaid(source.getTotalPaidAmount())
                .totalUnpaid(source.getTotalUnpaidAmount())
                .build();
    }

}
