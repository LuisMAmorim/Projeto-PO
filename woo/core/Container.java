enum ServiceQuality {
	B4,
	C4,
	C5,
	DL
}

public class Container extends Box {
	private ServiceQuality _serviceQuality;

	public Container(String id, int price, int critical, int q, ServiceLevel s, ServiceQuality quality) {

	}

	@Override
	public String toString() {

	}
}