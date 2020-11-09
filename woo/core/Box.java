package woo.core;

enum ServiceLevel {
	NORMAL,
	AIR,
	EXPRESS,
	BY_HAND;
}

public class Box extends Product {
	private ServiceLevel _serviceLevel;

	public Box(String id, ServiceLevel s, Supplier supplier, int price, int crit, int q) {
		super(id, supplier, price, crit, q);
		_serviceLevel = s;
	}

	public ServiceLevel getServiceLevel() {
		return _serviceLevel;
	}

	@Override
	public String toString() {
		return String.join("|", "BOX", getId(), getSupplier().getId(), Integer.toString(getPrice()),
		Integer.toString(getCriticalValue()), Integer.toString(getCurrentQuantity()), _serviceLevel.toString());
	}
}