package broker;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import model.Bioskop;
import model.Film;
import model.Karta;
import model.Repertoar;
import model.RepertoarAdmin;
import model.RepertoarPrikaz;
import model.RezervacijaModel;
import model.Role;
import model.Sala;
import model.User;
import model.Zanr;

public class Broker {
	
	private static Broker instanca;
	private Connection con;
	
	private Broker() {
		ucitajDrajver();
	}
	
	public static Broker getInstanca() {
		if(instanca==null) {
			instanca=new Broker();
		}
		return instanca;
	}
	

	private void ucitajDrajver() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		}
	}
	
	public void otvoriKonekciju() {
		try {
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop","root","");
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	public void zatvoriKonekciju() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}

	public int upisiNoviBioskop(Bioskop b) {
		// TODO Auto-generated method stub
		
		String sql="INSERT into bioskop (naziv,grad,ulica,telefon,email) VALUES (?,?,?,?,?)";
		PreparedStatement ps;
		int id=0;
		
		try {
			ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, b.getNaziv());
			ps.setString(2, b.getGrad());
			ps.setString(3, b.getUlicu());
			ps.setString(4, b.getTel());
			ps.setString(5, b.getEmail());
			int ar=ps.executeUpdate();
			
			try(ResultSet genKljuc=ps.getGeneratedKeys()){
				if(genKljuc.next()) {
					id=genKljuc.getInt(ar);
				}
			}
			
		} catch (SQLException e) {
			return id;
		}
		return id;
	}

	public List<Bioskop> vratiSveBioskope() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM bioskop";
		List<Bioskop> lista=new ArrayList<Bioskop>();
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Bioskop b=new Bioskop();
				b.setEmail(rs.getString("email"));
				b.setGrad(rs.getString("grad"));
				b.setId(rs.getInt("id"));
				b.setNaziv(rs.getString("naziv"));
				b.setTel(rs.getString("telefon"));
				b.setUlicu(rs.getString("ulica"));
				lista.add(b);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return lista;
	}

	public int upisiNovuSalu(Sala s) {
		// TODO Auto-generated method stub
		String sql="INSERT into sala (naziv,kolona,redova,id_bioskopa,aktivna) VALUES (?,?,?,?,?)";
		PreparedStatement ps;
		int id=0;
		
		try {
			ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, s.getNaziv());
			ps.setInt(2, s.getKolona());
			ps.setInt(3, s.getRedova());
			ps.setInt(4, s.getIdBioskopa());
			ps.setBoolean(5, s.isAktivna());
			int ar=ps.executeUpdate();
			
			try(ResultSet genKljuc=ps.getGeneratedKeys()){
				if(genKljuc.next()) {
					id=genKljuc.getInt(ar);
				}
			}
		} catch (SQLException e) {
			return id;
		}
		return id;
	}

	public List<Sala> vratiSaleZaBioskop(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM sala WHERE sala.id_bioskopa="+id;
		List<Sala> lista=new ArrayList<Sala>();
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Sala b=new Sala();
				b.setId(rs.getInt("id"));
				b.setNaziv(rs.getString("naziv"));
				b.setKolona(rs.getInt("kolona"));
				b.setRedova(rs.getInt("redova"));
				b.setIdBioskopa(rs.getInt("id_bioskopa"));
				b.setAktivna(rs.getBoolean("aktivna"));
				lista.add(b);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return lista;
	}

	public boolean promeniStatusSale(int id, Boolean aValue) {
		// TODO Auto-generated method stub
		String sql="UPDATE sala SET aktivna = ? WHERE id= ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setBoolean(1, aValue);
			ps.setInt(2, id);
			ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public List<Zanr> vratiSveZanrove() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM zanr";
		List<Zanr> lista=new ArrayList<Zanr>();
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Zanr b=new Zanr();
				b.setId(rs.getInt("id"));
				b.setZanr(rs.getString("zanr"));
				lista.add(b);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return lista;
	}

	public int sacuvajNoviFilm(Film f) {
		// TODO Auto-generated method stub
		int idRacuna=0;
		try {
			String sql="INSERT into film (naslov,opis,poster,trajanje,datumPremijere,datumPoslednje) values(?,?,?,?,?,?)";
			con.setAutoCommit(false);
			PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,f.getNaslov());
			ps.setString(2, f.getOpis());
			ps.setString(3, f.getPoster());
			ps.setInt(4, f.getDuzinu());
			ps.setDate(5, new Date(f.getPremijera().getTime()) );
			ps.setDate(6,  new Date(f.getKraj().getTime()));
			
			int affectedRows=ps.executeUpdate();
			System.out.println(affectedRows);
			
			try(ResultSet genKljuc=ps.getGeneratedKeys()){
				if(genKljuc.next()) {
					idRacuna=genKljuc.getInt(affectedRows);
				}
			}
			
			String zanr="INSERT into film_zanr (id_filma,id_zanra) values (?,?)";
			
			for(Zanr rs:f.getListaZanrova()) {
				ps=con.prepareStatement(zanr);
				ps.setInt(1, idRacuna);
				ps.setInt(2, rs.getId());
				ps.executeUpdate();
			}
			
			con.commit();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e);
			try {
				con.rollback();
			} catch (SQLException e2) {
				// TODO: handle exception
			}
		}
		System.out.println(idRacuna);
		return idRacuna;
		
	}

	public int vratiUkupanBrojFilmovaIzBaze(String pretraga) {
		// TODO Auto-generated method stub
		String sql="Select COUNT(film.id) as broj FROM film";
		if(pretraga.length()>0) {
			sql="Select COUNT(film.id) as broj FROM film WHERE naslov LIKE ?";
		}
		int ukupno=0;
		try {
			
			PreparedStatement ps=con.prepareStatement(sql);
			if(pretraga.length()>0) {
				ps.setString(1,"%"+pretraga+"%");
				}
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				ukupno = (rs.getInt("broj"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ukupno;
	}

	public List<Film> ucitajSveFilmovePaginacijaIzBaze(int brojFilmovaPoStranici, int offset, String pretraga) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM film ORDER BY datumPremijere DESC LIMIT "+brojFilmovaPoStranici+" OFFSET "+offset;
		if(pretraga.length()>0) {
			sql="SELECT * FROM film WHERE naslov LIKE ? ORDER BY datumPremijere DESC LIMIT "+brojFilmovaPoStranici+" OFFSET "+offset;
		}
		List<Film> lista= new ArrayList<Film>();
		
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			if(pretraga.length()>0) {
			ps.setString(1,"%"+pretraga+"%");
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Film k= new Film();
				k.setId(rs.getInt("id"));
				k.setNaslov(rs.getString("naslov"));
				k.setOpis(rs.getString("opis"));
				k.setDuzinu(rs.getInt("trajanje"));
				k.setPoster(rs.getString("poster"));
				k.setPremijera(rs.getDate("datumPremijere"));
				k.setKraj(rs.getDate("datumPoslednje"));
				lista.add(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<Film> vratiSveDostupneFilmoveZaDatum(java.util.Date date) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM film WHERE datumPremijere <= ? AND datumPoslednje >= ?";
		
		List<Film> lista= new ArrayList<Film>();
		
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1,new Date(date.getTime()));
			ps.setDate(2,new Date(date.getTime()));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Film k= new Film();
				k.setId(rs.getInt("id"));
				k.setNaslov(rs.getString("naslov"));
				k.setOpis(rs.getString("opis"));
				k.setDuzinu(rs.getInt("trajanje"));
				k.setPoster(rs.getString("poster"));
				k.setPremijera(rs.getDate("datumPremijere"));
				k.setKraj(rs.getDate("datumPoslednje"));
				lista.add(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public List<RepertoarAdmin> vratiRepertoarAdminZaDanIBioskop(int idBioskop, java.util.Date date) {
		// TODO Auto-generated method stub
		String sql="SELECT repertoar.id as id, repertoar.cena as cena, repertoar.vreme as vreme, "
				+ "film.naslov as naslov, film.trajanje as trajanje FROM repertoar INNER JOIN film ON repertoar.id_filma=film.id "
				+ "INNER JOIN sala ON repertoar.id_sale=sala.id INNER JOIN bioskop ON sala.id_bioskopa=bioskop.id WHERE bioskop.id=? "
				+ "AND repertoar.datum = ?";
		
		List<RepertoarAdmin> lista= new ArrayList<RepertoarAdmin>();
		
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,idBioskop);
			ps.setDate(2,new Date(date.getTime()));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RepertoarAdmin k= new RepertoarAdmin();
				k.setId(rs.getInt("id"));
				k.setNaziv(rs.getString("naslov"));
				k.setVreme(rs.getTime("vreme"));
				k.setDuzina(rs.getInt("trajanje"));
				k.setCena(rs.getInt("cena"));
				lista.add(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return lista;
	}

	public int upisiNovoPrikazivanje(Repertoar r) {
		// TODO Auto-generated method stub
		String sql="INSERT into repertoar (id_sale,id_filma,cena,datum,vreme) VALUES (?,?,?,?,?)";
		PreparedStatement ps;
		int id=0;
		
		try {
			ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, r.getIdSale());
			ps.setInt(2, r.getIdFilma());
			ps.setInt(3, r.getCena());
			ps.setDate(4, new Date(r.getDatum().getTime()));
			ps.setTime(5, r.getVreme());
			int ar=ps.executeUpdate();
			
			try(ResultSet genKljuc=ps.getGeneratedKeys()){
				if(genKljuc.next()) {
					id=genKljuc.getInt(ar);
				}
			}
		} catch (SQLException e) {
			return id;
		}
		return id;
	}

	public List<Sala> vratiAktivneSaleZaBioskop(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM sala WHERE sala.id_bioskopa="+id+" AND aktivna is true";
		List<Sala> lista=new ArrayList<Sala>();
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Sala b=new Sala();
				b.setId(rs.getInt("id"));
				b.setNaziv(rs.getString("naziv"));
				b.setKolona(rs.getInt("kolona"));
				b.setRedova(rs.getInt("redova"));
				b.setIdBioskopa(rs.getInt("id_bioskopa"));
				b.setAktivna(rs.getBoolean("aktivna"));
				lista.add(b);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return lista;
	}

	public List<RepertoarPrikaz> vratiRepertoarZaBioskopIDatum(int idBioskop, java.util.Date date) {
		// TODO Auto-generated method stub
		String sql="SELECT repertoar.id as id, repertoar.cena as cena, repertoar.vreme as vreme, "
				+ "((sala.kolona*sala.redova)-COUNT(karta.id_repertoara)) as slobodno, "
				+ "film.naslov as naslov, film.trajanje as trajanje "
				+ "FROM repertoar INNER JOIN film ON repertoar.id_filma=film.id "
				+ "INNER JOIN sala ON repertoar.id_sale=sala.id "
				+ "INNER JOIN bioskop ON sala.id_bioskopa=bioskop.id "
				+ "LEFT JOIN karta ON repertoar.id=karta.id_repertoara "
				+ "WHERE bioskop.id=? AND repertoar.datum = ? GROUP BY repertoar.id";
		
		List<RepertoarPrikaz> lista= new ArrayList<RepertoarPrikaz>();
		
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,idBioskop);
			ps.setDate(2,new Date(date.getTime()));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RepertoarPrikaz k= new RepertoarPrikaz();
				k.setId(rs.getInt("id"));
				k.setNaziv(rs.getString("naslov"));
				k.setVreme(rs.getTime("vreme"));
				k.setCena(rs.getInt("cena"));
				k.setSlobodno(rs.getInt("slobodno"));
				lista.add(k);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return lista;
	}

	public Film vratFilmPoIdRepertoara(int idPrikazivanja) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM film INNER JOIN repertoar ON repertoar.id_filma=film.id WHERE repertoar.id = "+idPrikazivanja;
		Film f= new Film();
		
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				f.setId(rs.getInt("id"));
				f.setNaslov(rs.getString("naslov"));
				f.setOpis(rs.getString("opis"));
				f.setDuzinu(rs.getInt("trajanje"));
				f.setPoster(rs.getString("poster"));
				f.setPremijera(rs.getDate("datumPremijere"));
				f.setKraj(rs.getDate("datumPoslednje"));
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	public List<Zanr> vratiSveZanroveZaFilm(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM zanr INNER JOIN film_zanr on film_zanr.id_zanra=zanr.id WHERE film_zanr.id_filma="+id;
		List<Zanr> lista=new ArrayList<Zanr>();
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Zanr b=new Zanr();
				b.setId(rs.getInt("id"));
				b.setZanr(rs.getString("zanr"));
				lista.add(b);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return lista;
	}

	public User vratiProveruUsera(User u) {
		
		String sql="SELECT * FROM user WHERE email = ?";
		User rezultat=new User();
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getEmail());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				rezultat.setId(rs.getInt("id"));
				rezultat.setIme(rs.getString("ime"));
				rezultat.setPrezime(rs.getString("prezime"));
				rezultat.setEmail(rs.getString("email"));
				rezultat.setPass(rs.getString("pass"));
			}
		
			if(rezultat.getPass()!=null && BCrypt.checkpw(u.getPass(), rezultat.getPass())) {
					String sql2="SELECT * FROM user_role INNER JOIN role ON user_role.id_role=role.id WHERE id_user=?";
					 ps = con.prepareStatement(sql2);
					 ps.setInt(1, rezultat.getId());
					 rs = ps.executeQuery();
					
					while(rs.next()) {
						Role r= new Role();
						r.setId(rs.getInt("id"));
						r.setNaziv(rs.getString("naziv"));
						r.setOpis(rs.getString("opis"));
						rezultat.getRole().add(r);
					}
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rezultat;
	}

	public Role vratiRoluPoImenu(String string) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM role  WHERE role.naziv=?";
		Role r= new Role();
		
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, string);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				r.setId(rs.getInt("id"));
				r.setNaziv(rs.getString("naziv"));
				r.setOpis(rs.getString("opis"));
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public int registrujNovogKorisnika(User u) {
		// TODO Auto-generated method stub
		String sql="INSERT into user (ime,prezime,email,pass) VALUES (?,?,?,?)";
		PreparedStatement ps;
		int id=0;
		
		try {
			ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, u.getIme());
			ps.setString(2, u.getPrezime());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getPass());
			int ar=ps.executeUpdate();
			
			try(ResultSet genKljuc=ps.getGeneratedKeys()){
				if(genKljuc.next()) {
					id=genKljuc.getInt(ar);
				}
			}
			
		} catch (SQLException e) {
			return id;
		}
		return id;
	}

	public void dodeliRoluKorisniku(int id, int idRole) {
		// TODO Auto-generated method stub
		String sql="INSERT into user_role (id_user,id_role) VALUES (?,?)";
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, idRole);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
		
		}
	}

	public RezervacijaModel vratiRezervacijaModel(int idPrikazivanja) {
		// TODO Auto-generated method stub
		String sql="SELECT sala.kolona as kolona, sala.redova as redova, film.naslov as naslov, repertoar.cena as cena,"
				+ " repertoar.datum as datum, repertoar.vreme as vreme, bioskop.naziv as bioskop,"
				+ " sala.naziv as sala FROM repertoar INNER JOIN film ON repertoar.id_filma=film.id"
				+ " INNER JOIN sala ON sala.id=repertoar.id_sale "
				+ " INNER JOIN bioskop ON bioskop.id=sala.id_bioskopa WHERE repertoar.id="+idPrikazivanja;
		RezervacijaModel r= new RezervacijaModel();
		
		try {
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				r.setBioskop(rs.getString("bioskop"));
				r.setCena(rs.getInt("cena"));
				r.setDatum(rs.getDate("datum"));
				r.setKolona(rs.getInt("kolona"));
				r.setNaslov(rs.getString("naslov"));
				r.setRedova(rs.getInt("redova"));
				r.setSala(rs.getString("sala"));
				r.setVreme(rs.getTime("vreme"));
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	public List<Karta> vratiKarteZaProjekciju(int idPrikazivanja) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM karta WHERE id_repertoara="+idPrikazivanja;
		List<Karta> lista=new ArrayList<Karta>();
		
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Karta k=new Karta();
				k.setId(rs.getInt("id"));
				k.setIdKorisnik(rs.getInt("id_usera"));
				k.setIdRepertoar(rs.getInt("id_repertoara"));
				k.setRed(rs.getInt("red"));
				k.setSediste(rs.getInt("kolona"));
				lista.add(k);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return lista;
	}

	public void kupiKarte(List<Karta> korpa) {
		// TODO Auto-generated method stub
		
		PreparedStatement ps;
		try {
			
			con.setAutoCommit(false);
			String sql="INSERT into karta (id_repertoara,id_usera,red,kolona) values(?,?,?,?)";
			for(Karta k:korpa) {
				ps=con.prepareStatement(sql);
				ps.setInt(1, k.getIdRepertoar());
				ps.setInt(2, k.getIdKorisnik());
				ps.setInt(3, k.getRed());
				ps.setInt(4, k.getSediste());
				ps.executeUpdate();
			}
			con.commit();
		} catch (SQLException e) {
			// TODO: handle exception
			try {
				con.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}

}
