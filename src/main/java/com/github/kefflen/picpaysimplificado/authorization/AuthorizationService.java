package com.github.kefflen.picpaysimplificado.authorization;

import com.github.kefflen.picpaysimplificado.transaction.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class AuthorizationService {
    private final RestClient restClient;

    public AuthorizationService(
            RestClient.Builder builder
    ) {
        this.restClient = builder
                .baseUrl("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc").build();
    }

    public void authorize(Transaction transaction) {
        var response = this.restClient.get()
                .retrieve()
                .toEntity(Authorization.class);
        if (response.getStatusCode().isError() || !response.getBody().isAuthorized()) {
            throw new UnauthorizedTransactionException("Unauthorized transaction");
        }
    }

}
