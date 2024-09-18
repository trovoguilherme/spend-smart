package br.com.skeleton.spendsmart.mapper;

import br.com.skeleton.spendsmart.entity.ExpenseHistory;
import br.com.skeleton.spendsmart.resource.response.ExpenseHistoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseHistoryMapper {

    @Mapping(target = "expenseId", source = "source.expense.id")
    @Mapping(target = "name", source = "source.expense.name")
    ExpenseHistoryResponse toExpenseHistoryResponse(ExpenseHistory source);

}
