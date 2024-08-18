package br.com.skeleton.spendsmart.mapper;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.resource.request.ExpenseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseMapper {

    Expense toExpense(ExpenseRequest source);

}
