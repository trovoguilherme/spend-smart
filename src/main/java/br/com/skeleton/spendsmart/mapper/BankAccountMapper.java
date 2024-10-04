package br.com.skeleton.spendsmart.mapper;

import br.com.skeleton.spendsmart.entity.BankAccount;
import br.com.skeleton.spendsmart.resource.request.BankAccountRequest;
import br.com.skeleton.spendsmart.resource.response.BankAccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankAccountMapper {

    BankAccount toEntity(BankAccountRequest source);

    BankAccountResponse toResponse(BankAccount source);

}
