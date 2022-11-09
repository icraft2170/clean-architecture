package me.hero.cleanarchitecture.buckpal.application.port.in;

public interface SendMoneyUseCase {
  boolean sendMoney(SendMoneyCommand command);
}
