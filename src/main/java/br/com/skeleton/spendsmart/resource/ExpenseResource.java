package br.com.skeleton.spendsmart.resource;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.enums.ExpenseStatus;
import br.com.skeleton.spendsmart.entity.enums.ExpenseType;
import br.com.skeleton.spendsmart.entity.enums.PaymentType;
import br.com.skeleton.spendsmart.mapper.ExpenseHistoryMapper;
import br.com.skeleton.spendsmart.mapper.ExpenseMapper;
import br.com.skeleton.spendsmart.resource.request.ExpenseRequest;
import br.com.skeleton.spendsmart.resource.request.UpdateExpenseRequest;
import br.com.skeleton.spendsmart.resource.response.ExpenseDetailResponse;
import br.com.skeleton.spendsmart.resource.response.ExpenseResponse;
import br.com.skeleton.spendsmart.service.ExpenseHistoryService;
import br.com.skeleton.spendsmart.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseResource {

    private final ExpenseMapper expenseMapper;
    private final ExpenseService service;

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> findAll(@RequestParam(required = false) ExpenseStatus expenseStatus,
                                                         @RequestParam(required = false) ExpenseType expenseType,
                                                         @RequestParam(required = false) PaymentType paymentType) {
        return ResponseEntity.ok(service.findAll(expenseStatus, expenseType, paymentType)
                .stream().map(expenseMapper::toExpenseResponse).toList());
    }

    @GetMapping("/details")
    public ResponseEntity<List<ExpenseDetailResponse>> findAllDetails(@RequestParam(required = false) ExpenseStatus expenseStatus,
                                                                      @RequestParam(required = false) ExpenseType expenseType,
                                                                      @RequestParam(required = false) PaymentType paymentType) {
        return ResponseEntity.ok(service.findAll(expenseStatus, expenseType, paymentType).stream()
                .map(expenseMapper::toExpenseDetailResponse).toList());
    }

    @GetMapping("/{name}")
    public ResponseEntity<ExpenseResponse> findByName(@PathVariable String name) {
        return ResponseEntity.ok(expenseMapper.toExpenseResponse(service.findByName(name)));
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> save(@RequestBody @Valid ExpenseRequest expenseRequest) {
        Expense expense = service.save(expenseMapper.toExpense(expenseRequest));
        return ResponseEntity.created(URI.create("/" + expense.getId())).body(expenseMapper.toExpenseResponse(expense));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> update(@PathVariable Long id,
                                                  @RequestHeader String changeAgent,
                                                  @RequestBody @Valid UpdateExpenseRequest updateExpenseRequest) {
        Expense expense = service.update(id, changeAgent, expenseMapper.toExpense(updateExpenseRequest));
        return ResponseEntity.ok(expenseMapper.toExpenseResponse(expense));
    }

    @PatchMapping("/{id}/pay")
    public ResponseEntity<ExpenseResponse> pay(@PathVariable final Long id) {
        return ResponseEntity.ok(expenseMapper.toExpenseResponse(service.pay(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
