package woo.core;

enum ServiceQuality {
	B4,
	C4,
	C5,
	DL
}

public class Container extends Box {
	private ServiceQuality _serviceQuality;

	public Container(String id, int price, int crit, Supplier supplier, ServiceLevel s, ServiceQuality quality) {
		super(id, price, crit, supplier, s);
		_serviceQuality = quality;
	}

	public ServiceLevel getServiceQuality() {
		return _serviceQuality;
	}

	@Override
	public String toString() {
		return "CONTAINER|" + getId() + "|" + getSupplier().getId() + "|" + getPrice(); + "|" +
		getCriticalValue() + "|" + getCurrentQuantity() + "|" + getServiceLevel() + "|" + _serviceQuality;
	}
}