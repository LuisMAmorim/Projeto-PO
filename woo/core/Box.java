package woo.core;

enum ServiceLevel {
	NORMAL,
	AIR,
	EXPRESS,
	BY_HAND;
}

public class Box extends Product {
	private ServiceLevel _serviceLevel;

	public Box(String id, int price, int crit, Supplier supplier, ServiceLevel s) {
		super(id, price, crit, supplier);
		_serviceLevel = s;
	}

	public ServiceLevel getServiceLevel() {
		return _serviceLevel;
	}

	@Override
	public String toString() {
		return "BOX|" + getId() + "|" + getSupplier().getId() + "|" + getPrice() + "|" +
		getCriticalValue() + "|" + getCurrentQuantity() + "|" + _serviceLevel;
	}
}