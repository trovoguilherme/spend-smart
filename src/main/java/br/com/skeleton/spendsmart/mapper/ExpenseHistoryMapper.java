package br.com.skeleton.spendsmart.mapper;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.ExpenseHistory;
import br.com.skeleton.spendsmart.entity.enums.HistoryOperationType;
import br.com.skeleton.spendsmart.resource.response.ExpenseHistoryDetailResponse;
import br.com.skeleton.spendsmart.resource.response.ExpenseHistoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExpenseHistoryMapper {

    @Mapping(target = "expenseId", source = "source.expense", qualifiedByName = "mapExpenseId")
//    @Mapping(target = "expenseHistoryDetailResponses", source = "sources", qualifiedByName = "mapExpenseHistoryDetailResponses")
    ExpenseHistoryResponse toExpenseHistoryResponse(ExpenseHistory source);

    @Named("mapExpenseId")
    default Long mapExpenseId(Expense source) {
        return source.getId();
    }

    @Named("mapExpenseHistoryDetailResponses")
    default List<ExpenseHistoryDetailResponse> mapExpenseHistoryDetailResponses(List<ExpenseHistory> sources) {
        Map<HistoryOperationType, List<ExpenseHistory>> groupByOperation = sources.stream().collect(Collectors.groupingBy(ExpenseHistory::getOperation));

        return List.of();
    }




}
