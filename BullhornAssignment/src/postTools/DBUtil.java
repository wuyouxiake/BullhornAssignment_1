package postTools;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
private static final EntityManagerFactory emf = 
Persistence.createEntityManagerFactory("BullhornAssignment");
	public static EntityManagerFactory getEmFactory() {
		return emf;
	}
}
