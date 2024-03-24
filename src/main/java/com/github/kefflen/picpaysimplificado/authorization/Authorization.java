package com.github.kefflen.picpaysimplificado.authorization;

public record Authorization(
        String message
) {
    public boolean isAuthorized() {
        return this.message.equals("Autorizado");
    }
}
