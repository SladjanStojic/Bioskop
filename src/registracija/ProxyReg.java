package registracija;

import kontroler.Kontroler;
import model.User;

public class ProxyReg implements IProxyR{

	@Override
	public void regUser(User u) {
		// TODO Auto-generated method stub
		Kontroler.getInstanca().registrujNovogKorisnika(u);
	}

}
