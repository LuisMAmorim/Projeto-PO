package woo.core;

import woo.core.exception.InvalidServiceLevelException;
import woo.core.exception.InvalidServiceQualityException;
import woo.core.exception.InvalidPriceException;

enum ServiceQuality {
	B4,
	C4,
	C5,
	DL;

	public static ServiceQuality parse(String s) throws InvalidServiceQualityException {
	    if (s.equals("B4"))
	      return B4;

	    if (s.equals("C4"))
	      return C4;

	    if (s.equals("C5"))
	      return C5;

	    if (s.equals("DL"))
	      return DL;

	    throw new InvalidServiceQualityException(s);
  	}
}

public class Container extends Box {
	private ServiceQuality _serviceQuality;

	public Container(String id, String s, String quality, Supplier supplier, int price, int crit, int q)
	throws InvalidServiceLevelException, InvalidServiceQualityException, InvalidPriceException {
		super(id, s, supplier, price, crit, q);
		_serviceQuality = ServiceQuality.parse(quality);
	}

	public ServiceQuality getServiceQuality() {
		return _serviceQuality;
	}

	/*
	@Override
	public String toString() {
		return String.join("CONTAINER|%s|%s|%d|%d|%d|%s|%s"
			getId(),
			getSupplier().getId(),
			getPrice(),
			getCriticalValue(),
			getCurrentQuantity(),
			getServiceLevel().toString(),
			_serviceQuality.toString()
		);
	}
	*/

	@Override
	public String getProductType() {
		return "CONTAINER";
	}

	@Override
	public String getExtraInformation() {
		return String.format("%s|%s", super.getExtraInformation(), _serviceQuality);
	}
}