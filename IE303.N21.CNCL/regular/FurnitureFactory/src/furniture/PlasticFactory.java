package furniture;

public class PlasticFactory extends FurnitureAbstractFactory{

	@Override
	Chair createChair() {
		// TODO Auto-generated method stub
		return new PlasticChair();
	}
	@Override
	Table createTable() {
		// TODO Auto-generated method stub
		return new PlasticTable();
	}

}
