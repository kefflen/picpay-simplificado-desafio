package com.github.kefflen.picpaysimplificado.transaction;

import com.github.kefflen.picpaysimplificado.wallet.WalletRepository;
import com.github.kefflen.picpaysimplificado.wallet.WalletType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional
    public Transaction create(Transaction transactionData) {

        this.validate(transactionData);

        var transaction = this.transactionRepository.save(transactionData);
        var payerWallet = this.walletRepository.findById(transactionData.payerId()).get();
        var payeeWallet = this.walletRepository.findById(transactionData.PayeeId()).get();

        this.walletRepository.save(payerWallet.debit(transactionData.value()));
        this.walletRepository.save(payeeWallet.credit(transactionData.value()));


        return transaction;
    }

    private void validate(Transaction transaction) {
        var payerWallet = walletRepository.findById(transaction.payerId()).get();
        var payeeWallet = walletRepository.findById(transaction.PayeeId()).get();

        boolean payerWalletIsCommon = payerWallet.type() == WalletType.COMUM.getType();
        boolean payerHasBalance = payerWallet.balance().compareTo(transaction.value()) >= 0;
        boolean payerIsNotPayee = !Objects.equals(payerWallet.id(), payeeWallet.id());
        boolean isValid = payerWalletIsCommon && payerHasBalance && payerIsNotPayee;

        if (!isValid) {
            throw new InvalidTransactionException("Invalid transaction");
        }
    }
}