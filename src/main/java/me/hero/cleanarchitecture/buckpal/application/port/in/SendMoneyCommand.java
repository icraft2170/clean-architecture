package me.hero.cleanarchitecture.buckpal.application.port.in;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import me.hero.cleanarchitecture.shared.SelfValidating;

@Value
@EqualsAndHashCode(callSuper = false)
public
class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {

  @NotNull
  private final AccountId sourceAccountId;

  @NotNull
  private final AccountId targetAccountId;

  @NotNull
  private final Money money;

  public SendMoneyCommand(
      AccountId sourceAccountId,
      AccountId targetAccountId,
      Money money) {
    this.sourceAccountId = sourceAccountId;
    this.targetAccountId = targetAccountId;
    this.money = money;
    this.validateSelf();
  }
}