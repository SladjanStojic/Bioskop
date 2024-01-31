package logovanje;

import javax.swing.JOptionPane;

import kontroler.Kontroler;
import model.Role;
import model.User;
import view.MedjuForma;

public class ProxyLogin implements IProxy{

	@Override
	public void login(User u) {
		User potvrdjenUser = Kontroler.getInstanca().vratiKorisnika(u);
		if(potvrdjenUser.getId()>0) {
			MedjuForma ip = new MedjuForma();
			ip.setTitle("Projekat Bioskop");
			
			for(Role r:potvrdjenUser.getRole()) {
				
				if(r.getNaziv().equals("ROLE_ADMIN")) {
					ip.login(potvrdjenUser);
					return;
				}
				
				if(r.getNaziv().equals("ROLE_ZAPOSLENI")) {
					ip.login(potvrdjenUser);
					return;
				}
				
				if(r.getNaziv().equals("ROLE_KORISNIK")) {
					ip.login(potvrdjenUser);
					return;
				}
				
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "Username ili password pogresni");
		}
		
	}

}
