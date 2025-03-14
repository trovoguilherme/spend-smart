package br.com.skeleton.spendsmart.resource;

import br.com.skeleton.spendsmart.entity.Expense;
import br.com.skeleton.spendsmart.entity.enums.ExpenseStatus;
import br.com.skeleton.spendsmart.entity.enums.ExpenseType;
import br.com.skeleton.spendsmart.entity.enums.PaymentType;
import br.com.skeleton.spendsmart.mapper.ExpenseMapper;
import br.com.skeleton.spendsmart.resource.request.ExpenseRequest;
import br.com.skeleton.spendsmart.resource.request.UpdateExpenseRequest;
import br.com.skeleton.spendsmart.resource.response.ExpenseResponse;
import br.com.skeleton.spendsmart.resource.response.ExpenseSummaryResponse;
import br.com.skeleton.spendsmart.resource.response.TopPayoffItemsResponse;
import br.com.skeleton.spendsmart.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<List<ExpenseResponse>> findAll(@RequestParam(required = false, defaultValue = "PENDING") ExpenseStatus expenseStatus,
                                                         @RequestParam(required = false) ExpenseType expenseType,
                                                         @RequestParam(required = false) PaymentType paymentType) {
        return ResponseEntity.ok(service.findAll(expenseStatus, expenseType, paymentType)
                .stream().map(expenseMapper::toExpenseResponse).toList());
    }

    @GetMapping("/details")
    public ResponseEntity<ExpenseSummaryResponse> findAllDetails(@RequestParam(required = false, defaultValue = "PENDING") ExpenseStatus expenseStatus,
                                                                 @RequestParam(required = false) ExpenseType expenseType,
                                                                 @RequestParam(required = false) PaymentType paymentType) {
        return ResponseEntity.ok(expenseMapper.toExpenseSummaryResponse(service.findAll(expenseStatus, expenseType, paymentType)));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ExpenseResponse> findByName(@PathVariable String name) {
        return ResponseEntity.ok(expenseMapper.toExpenseResponse(service.findByName(name)));
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> save(@RequestBody @Valid ExpenseRequest expenseRequest) {
        Expense expense = service.save(expenseMapper.toExpense(expenseRequest));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(expense.getId())
                .toUri();

        return ResponseEntity.created(location).body(expenseMapper.toExpenseResponse(expense));
    }

//    @PostMapping
//    public ResponseEntity<List<ExpenseResponse>> saveAll(@RequestBody @Valid List<ExpenseRequest> expenseRequests) {
//        List<Expense> expenses = service.saveAll(expenseRequests.stream().map(expenseMapper::toExpense).toList());
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(expense.getId())
//                .toUri();
//
//        return ResponseEntity.created(null).body(expenses.stream().map(e -> expenseMapper.toExpenseResponse(e)).toList());
//    }

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

    @GetMapping("/top-payoff-item")
    public ResponseEntity<List<TopPayoffItemsResponse>> get() {
        return ResponseEntity.ok(expenseMapper.toPayoffExpense(service.getTopPayoffItem()));
    }

}
