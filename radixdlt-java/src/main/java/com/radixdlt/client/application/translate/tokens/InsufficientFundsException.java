package com.radixdlt.client.application.translate.tokens;

import com.radixdlt.client.atommodel.tokens.TokenClassReference;
import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {
	private final TokenClassReference tokenClassReference;
	private final BigDecimal available;
	private final BigDecimal requestedAmount;

	public InsufficientFundsException(TokenClassReference tokenClassReference, BigDecimal available, BigDecimal requestedAmount) {
		super("Requested " + requestedAmount
			+ " but only " + available + " " + tokenClassReference.getSymbol() + " available.");
		this.tokenClassReference = tokenClassReference;
		this.available = available;
		this.requestedAmount = requestedAmount;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public BigDecimal getRequestedAmount() {
		return requestedAmount;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof InsufficientFundsException)) {
			return false;
		}

		InsufficientFundsException o = (InsufficientFundsException) obj;
		return this.tokenClassReference.equals(o.tokenClassReference)
			&& this.available.compareTo(o.available) == 0
			&& this.requestedAmount.compareTo(o.requestedAmount) == 0;
	}

	@Override
	public int hashCode() {
		return this.getMessage().hashCode();
	}
}