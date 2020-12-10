package woo.core;

import woo.core.exception.InvalidServiceLevelException;
import woo.core.exception.InvalidServiceQualityException;
import woo.core.exception.InvalidPriceException;


public class Container extends Box {
	private enum ServiceQuality {
		B4, C4, C5, DL
	}

	private final static int _deadlineFactor = 8;
	private ServiceQuality _serviceQuality;

	public Container(String id, String s, String quality, Supplier supplier, int price, int crit, int q)
	throws InvalidServiceLevelException, InvalidServiceQualityException {
		super(id, s, supplier, price, crit, q);

		try {
			_serviceQuality = ServiceQuality.valueOf(quality);
		} catch (IllegalArgumentException x) {
			throw new InvalidServiceLevelException(quality);
		}
	}

	public String getServiceQuality() {
		return _serviceQuality.name();
	}

	@Override
	public String getProductType() {
		return "CONTAINER";
	}

	@Override
	public String getExtraInformation() {
		return String.format("%s|%s", super.getExtraInformation(), _serviceQuality);
	}
}