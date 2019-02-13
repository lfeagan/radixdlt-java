package com.radixdlt.client.core.network.jsonrpc;

import org.radix.serialization2.SerializerId2;

@SerializerId2("LOCALSYSTEM")
public class RadixLocalSystem extends RadixSystem {
	RadixLocalSystem() {
		// No-arg constructor for serializer
	}

	RadixLocalSystem(long low, long high) {
		super(low, high);
	}
}