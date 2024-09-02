package br.com.skeleton.spendsmart.mapper;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.Installment;
import br.com.skeleton.spendsmart.resource.request.ExpenseRequest;
import br.com.skeleton.spendsmart.resource.request.UpdateExpenseRequest;
import br.com.skeleton.spendsmart.resource.response.ExpenseDetailResponse;
import br.com.skeleton.spendsmart.resource.response.ExpenseResponse;
import br.com.skeleton.spendsmart.resource.response.InstallmentDetailResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseMapper {

    @Mapping(target = "installments", source = "source", qualifiedByName = "mapInstallments")
    Expense toExpense(ExpenseRequest source);

    Expense toExpense(UpdateExpenseRequest source);

    ExpenseResponse toExpenseResponse(Expense source);

    @Mapping(target = "installmentDetailResponse", source = "source", qualifiedByName = "mapInstallmentDetailResponse")
    ExpenseDetailResponse toExpenseDetailResponse(Expense source);

    @Named("mapInstallments")
    default List<Installment> mapInstallments(ExpenseRequest expenseRequest) {

        if (expenseRequest.getInstallment() != null) {
            List<Installment> installments = new ArrayList<>();

            for (int i = 0; i < expenseRequest.getInstallment().getNumber(); i++) {
                installments.add(Installment.builder()
                        .value(expenseRequest.getValue().divide(BigDecimal.valueOf(expenseRequest.getInstallment().getNumber()), 2, RoundingMode.HALF_UP))
                        .paid(false).build());
            }
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
                .amountPaid(source.getPaidInstallmentsCount())
                .amountUnpaid(source.getUnpaidInstallmentsCount())
                .totalPaid(source.getTotalPaidAmount())
                .totalUnpaid(source.getTotalUnpaidAmount())
                .build();
    }

}
