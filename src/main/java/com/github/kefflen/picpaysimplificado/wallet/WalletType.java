package com.github.kefflen.picpaysimplificado.wallet;

public enum WalletType {
    COMUM(1), LOJISTA(2);

    private final int type;

    WalletType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
