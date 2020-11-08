package woo.core;

enum ServiceQuality {
	B4,
	C4,
	C5,
	DL
}

public class Container extends Box {
	private ServiceQuality _serviceQuality;

	public Container(String id, ServiceLevel s, ServiceQuality quality, Supplier supplier, int price, int crit, int q) {
		super(id, s, supplier, price, crit, q);
		_serviceQuality = quality;
	}

	public ServiceQuality getServiceQuality() {
		return _serviceQuality;
	}

	@Override
	public String toString() {
		return "CONTAINER|" + getId() + "|" + getSupplier().getId() + "|" + getPrice() + "|" +
		getCriticalValue() + "|" + getCurrentQuantity() + "|" + getServiceLevel() + "|" + _serviceQuality;
	}
}