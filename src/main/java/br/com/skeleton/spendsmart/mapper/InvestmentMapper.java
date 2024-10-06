package br.com.skeleton.spendsmart.mapper;

import br.com.skeleton.spendsmart.entity.Investment;
import br.com.skeleton.spendsmart.resource.request.InvestmentRequest;
import br.com.skeleton.spendsmart.resource.request.UpdateInvestmentRequest;
import br.com.skeleton.spendsmart.resource.response.InvestmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvestmentMapper {

    Investment toEntity(InvestmentRequest source);

    Investment toEntity(UpdateInvestmentRequest source);

    InvestmentResponse toResponse(Investment source);

}
