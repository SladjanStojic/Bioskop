package kontroler;

import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import broker.Broker;
import model.Bioskop;
import model.Film;
import model.Karta;
import model.Repertoar;
import model.RepertoarAdmin;
import model.RepertoarPrikaz;
import model.RezervacijaModel;
import model.Sala;
import model.User;
import model.Zanr;
import model.Role;
public class Kontroler {
	
	private static Kontroler instanca;
	private Kontroler() {
		
	}
	public static Kontroler getInstanca() {
		if(instanca==null) {
			instanca=new Kontroler();
		}
		return instanca;
	}
	public int upisiNoviBioskop(Bioskop b) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		int id=Broker.getInstanca().upisiNoviBioskop(b);
		Broker.getInstanca().zatvoriKonekciju();
		return id;
	}
	public List<Bioskop> vratiSveBioskope() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Bioskop> lista=Broker.getInstanca().vratiSveBioskope();
		Broker.getInstanca().zatvoriKonekciju();
		return lista;
	}
	public int upisiNovuSalu(Sala s) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		int id=Broker.getInstanca().upisiNovuSalu(s);
		Broker.getInstanca().zatvoriKonekciju();
		return id;
	}
	public List<Sala> vratiSaleZaBioskop(int id) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Sala> lista=Broker.getInstanca().vratiSaleZaBioskop(id);
		Broker.getInstanca().zatvoriKonekciju();
		return lista;
	}
	public boolean promeniStatusSale(int id, Boolean aValue) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		boolean status=Broker.getInstanca().promeniStatusSale(id,aValue);
		Broker.getInstanca().zatvoriKonekciju();
		return status;
	}
	public List<Zanr> vratiSveZanrove() {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Zanr> lista=Broker.getInstanca().vratiSveZanrove();
		Broker.getInstanca().zatvoriKonekciju();
		return lista;
	}
	public int sacuvajNoviFilm(Film f) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		int id=Broker.getInstanca().sacuvajNoviFilm(f);
		Broker.getInstanca().zatvoriKonekciju();
		return id;
	}
	public int vratiUkupanBrojFilmova(String pretraga) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		int vratiUkupanBrojFilmova=Broker.getInstanca().vratiUkupanBrojFilmovaIzBaze(pretraga);
		Broker.getInstanca().zatvoriKonekciju();
		return vratiUkupanBrojFilmova;
	}
	public List<Film> ucitajSveFilmovePaginacija(int brojFilmovaPoStranici, int offset, String pretraga) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Film> lista= Broker.getInstanca().ucitajSveFilmovePaginacijaIzBaze(brojFilmovaPoStranici,offset,pretraga);
		Broker.getInstanca().zatvoriKonekciju();
		return lista;
	}
	public List<Film> vratiSveDostupneFilmoveZaDatum(Date date) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Film> lista= Broker.getInstanca().vratiSveDostupneFilmoveZaDatum(date);
		Broker.getInstanca().zatvoriKonekciju();
		return lista;
	}
	public List<RepertoarAdmin> vratiRepertoarAdminZaDanIBioskop(int idBioskop, Date date) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<RepertoarAdmin> lista= Broker.getInstanca().vratiRepertoarAdminZaDanIBioskop(idBioskop,date);
		Broker.getInstanca().zatvoriKonekciju();
		return lista;
	}
	public int upisiNovoPrikazivanje(Repertoar r) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		int id=Broker.getInstanca().upisiNovoPrikazivanje(r);
		Broker.getInstanca().zatvoriKonekciju();
		return id;
	}
	public List<Sala> vratiAktivneSaleZaBioskop(int id) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<Sala> lista=Broker.getInstanca().vratiAktivneSaleZaBioskop(id);
		Broker.getInstanca().zatvoriKonekciju();
		return lista;
	}
	public List<RepertoarPrikaz> vratiRepertoarZaBioskopIDatum(int idBioskop, Date date) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		List<RepertoarPrikaz> lista= Broker.getInstanca().vratiRepertoarZaBioskopIDatum(idBioskop,date);
		Broker.getInstanca().zatvoriKonekciju();
		return lista;
	}
	public Film vratFilmPoIdRepertoara(int idPrikazivanja) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		Film f= Broker.getInstanca().vratFilmPoIdRepertoara(idPrikazivanja);
		f.setListaZanrova(Broker.getInstanca().vratiSveZanroveZaFilm(f.getId()));
		Broker.getInstanca().zatvoriKonekciju();
		return f;
	}

	public User vratiKorisnika(User u) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		User proverenUser=Broker.getInstanca().vratiProveruUsera(u);
		Broker.getInstanca().zatvoriKonekciju();
		return proverenUser;
	}
	public void registrujNovogKorisnika(User u) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		Role rola=Broker.getInstanca().vratiRoluPoImenu("ROLE_KORISNIK");
		String pass2=BCrypt.hashpw(u.getPass(), BCrypt.gensalt());
		u.setPass(pass2);
		int id=Broker.getInstanca().registrujNovogKorisnika(u);
		Broker.getInstanca().dodeliRoluKorisniku(id,rola.getId());
		Broker.getInstanca().zatvoriKonekciju();
	}
	public RezervacijaModel vratiRezervacijaModel(int idPrikazivanja) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		RezervacijaModel rm=Broker.getInstanca().vratiRezervacijaModel(idPrikazivanja);
		//rm.setLista(Broker.getInstanca().vratiKarteZaProjekciju(idPrikazivanja));
		List<Karta>lista=Broker.getInstanca().vratiKarteZaProjekciju(idPrikazivanja);
		Broker.getInstanca().zatvoriKonekciju();
		
		int[][]raspored=new int[rm.getRedova()][rm.getKolona()];
		
		for(Karta k:lista) {
			raspored[k.getRed()-1][k.getSediste()-1]=1;
		}
		rm.setRaspored(raspored);
		
		
		return rm;
	}
	public void kupiKarte(List<Karta> korpa) {
		// TODO Auto-generated method stub
		Broker.getInstanca().otvoriKonekciju();
		Broker.getInstanca().kupiKarte(korpa);
		Broker.getInstanca().zatvoriKonekciju();
	}
	

}
