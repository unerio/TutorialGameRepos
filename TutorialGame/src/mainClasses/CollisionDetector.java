package mainClasses;

public class CollisionDetector {

	Controller objectController;
	
	public CollisionDetector(Controller objectController){
		this.objectController = objectController;
	}
	
	public void checkCollisions(){
		for(int ind1 = 0; ind1 < objectController.getObjects().size(); ind1++){
			for(int ind2 = 0; ind2 < objectController.getObjects().size(); ind2++){
				GameObject go1 = objectController.getObjects().get(ind1);
				GameObject go2 = objectController.getObjects().get(ind2);
				if(go1 != null && go2 != null){
					if(go1 instanceof Enemy && go2 instanceof Bullet){
						if(go1.getBounds().intersects(go2.getBounds())){
							objectController.removeObject(go1);
							objectController.removeObject(go2);
						}
					}
				}
			}
		}
	}
	
}
