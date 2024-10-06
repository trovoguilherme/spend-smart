package br.com.skeleton.spendsmart.resource;

import br.com.skeleton.spendsmart.entity.Investment;
import br.com.skeleton.spendsmart.mapper.InvestmentMapper;
import br.com.skeleton.spendsmart.resource.request.InvestmentRequest;
import br.com.skeleton.spendsmart.resource.request.UpdateInvestmentRequest;
import br.com.skeleton.spendsmart.resource.response.InvestmentResponse;
import br.com.skeleton.spendsmart.service.InvestmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/investment")
public class InvestmentResource {

    private final InvestmentService investmentService;
    private final InvestmentMapper investmentMapper;

    //TODO fazer essa service, e melhorar os erros criando um BadRequest para que outras classes extendam dele para diminuir o handle

    @PostMapping("/deposit")
    public ResponseEntity<InvestmentResponse> deposit(@RequestBody @Valid InvestmentRequest investmentRequest) {

        Investment investment = investmentService.save(investmentMapper.toEntity(investmentRequest));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(investment.getId())
                .toUri();

        return ResponseEntity.created(location).body(investmentMapper.toResponse(investment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvestmentResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateInvestmentRequest updateInvestmentRequest) {
        Investment investment = investmentService.update(id, investmentMapper.toEntity(updateInvestmentRequest));

        return ResponseEntity.ok(investmentMapper.toResponse(investment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        investmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
