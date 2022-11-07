package me.hero.cleanarchitecture.buckpal.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Account {
  private AccountId id;
  private Money baselineBalance;
  private ActivityWindow activityWindow;

  public Money calculateBalance() {
    return Money.add(
        this.baselineBalance,
        this.activityWindow.calculateBalance(this.id)
    );
  }

  public boolean withdraw(Money money, AccountId targetAccountId) {
    if (!mayWithDraw(money)) {
      return false;
    }

    Activity withdrawal = new Activity(
      this.id,
      this.id,
      targetAccountId,
      LocalDateTime.now(),
      money
    );
    this.activityWindow.addActivity(withdrawal);
    return true;
  }

  private boolean mayWithDraw(Money money) {
    return Money.add(
        this.calculateBalance(),
        money.negate()
    ).isPositive();
  }

  public boolean deposit(Money money, AccountId sourceAccountId) {
    Activity deposit = new Activity(
        this.id,
        sourceAccountId,
        this.id,
        LocalDateTime.now(),
        money
    );
    this.activityWindow.addActivity(deposit);
    return true;
  }
}
