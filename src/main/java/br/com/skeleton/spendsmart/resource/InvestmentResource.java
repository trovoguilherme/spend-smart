package br.com.skeleton.spendsmart.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/investment")
public class InvestmentResource {

    //TODO fazer essa service, e melhorar os erros criando um BadRequest para que outras classes extendam dele para diminuir o handle

}
