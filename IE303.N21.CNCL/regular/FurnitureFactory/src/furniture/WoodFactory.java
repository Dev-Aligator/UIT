package furniture;

public class WoodFactory extends FurnitureAbstractFactory{

	@Override
	Chair createChair() {
		// TODO Auto-generated method stub
		return new WoodChair();
	}

	@Override
	Table createTable() {
		// TODO Auto-generated method stub
		return new WoodTable();
	}

}
