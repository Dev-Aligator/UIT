package furniture;

public class Client {
	public static void main(String[] args) {
		FurnitureAbstractFactory woodFurnitureFactory = FurnitureFactory.getFactory(MaterialType.WOOD);
		Chair woodChair = woodFurnitureFactory.createChair();
		Table woodTable = woodFurnitureFactory.createTable();
		woodChair.create();
		woodTable.create();
		
		System.out.println("=======================");
		FurnitureAbstractFactory plasticFurnitureFactory = FurnitureFactory.getFactory(MaterialType.PLASTIC);
		Chair plasticChair = plasticFurnitureFactory.createChair();
		Table plasticTable = plasticFurnitureFactory.createTable();
		plasticTable.create();
		plasticChair.create();
	}
}
