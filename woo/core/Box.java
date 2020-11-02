package woo.core;

enum ServiceLevel {
	NORMAL,
	AIR,
	EXPRESS,
	BY_HAND
}

public class Box extends Product {
	private ServiceLevel _serviceLevel;

	public Box(String id, int price, int critical, int q, ServiceLevel s) {

	}

	@Override
	public String toString() {

	}
}