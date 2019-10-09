package com.example.myapplication.recursos;

public class Partidos {
	private String equipos,  fechaPartido;
	private String[][] datosPartidos = {
			{ "San Pablo Inmobiliaria Burgos\n - \nF.C. Barcelona Lassa",	"02-10-2015"},
			{ "Planasa Navarra\n - \nSan Pablo Inmobiliaria Burgos", "09-10-2015" },
			{ "San Pablo Inmobiliaria Burgos\n - \nActel For�a Lleida","17-10-2015" },
			{ "Quesos Cerrato Palencia\n - \nSan Pablo Inmobiliaria Burgos","23-10-2015" },
			{ "San Pablo Inmobiliaria Burgos\n - \nClub Melilla Baloncesto",	"30-10-2015" },
			{ "Leyma Basquet Coru�a\n - \nSan Pablo Inmobiliaria Burgos","06-11-2015" },
			{ "San Pablo Inmobiliaria Burgos\n - \nCafes Candelas Breogan","11-11-2015" },
			{ "Palma Air Europa\n - \nSan Pablo Inmobiliaria Burgos", "15-11-2015" },
			{ "Club Ourense Baloncesto\n - \nSan Pablo Inmobiliaria Burgos","21-11-2015" },
			{ "San Pablo Inmobiliaria Burgos\n - \nAmics Castell�", "27-11-2015" },
			{ "Pe�as Huesca\n - \nSan Pablo Inmobiliaria Burgos", "04-12-2015" },
			{ "San Pablo Inmobiliaria Burgos\n - \nCB Prat Joventut", "11-12-2015" },
			{ "Cocinas.com\n - \nSan Pablo Inmobiliaria Burgos", "16-12-2015" },
			{"San Pablo Inmobiliaria Burgos\n - \nUnion Financiera Baloncesto Oviedo","20-12-2015" },
			{"Caceres Patrimonio de la Humanidad\n - \nSan Pablo Inmobiliaria Burgos","30-12-2015" },
			{ "F.C. Barcelona LassaSan\n - \nPablo Inmobiliaria Burgos","04-01-2016" },
			{ "San Pablo Inmobiliaria Burgos\n - \nPlanasa Navarra", "08-01-2016" },
			{ "Actel For�a Lleida\n - \nSan Pablo Inmobiliaria Burgos","15-01-2016" },
			{ "San Pablo Inmobiliaria Burgos\n - \nQuesos Cerrato Palencia","22-01-2016" },
			{ "Club Melilla BaloncestoSan Pablo\n - \nInmobiliaria Burgos","05-02-2016" },
			{ "San Pablo Inmobiliaria Burgos\n - \nLeyma Basquet Coru�a","12-02-2016" },
			{ "Cafes Candelas Breogan\n - \nSan Pablo Inmobiliaria Burgos","20-02-2016" },
			{ "San Pablo Inmobiliaria Burgos\n - \nPalma Air Europa", "26-02-2016" },
			{ "San Pablo Inmobiliaria Burgos\n - \nClub Ourense Baloncesto","04-03-2016" },
			{ "Amics Castell�\n - \nSan Pablo Inmobiliaria Burgos", "11-03-2016" },
			{ "San Pablo Inmobiliaria Burgos\n - \nPe�as Huesca", "18-03-2016" },
			{ "CB Prat Joventut\n - \nSan Pablo Inmobiliaria Burgos", "27-03-2016" },
			{ "San Pablo Inmobiliaria Burgos\n - \nCocinas.com", "01-04-2016" },
			{"Union Financiera Baloncesto Oviedo\n - \nSan Pablo Inmobiliaria Burgos","08-04-2016" },
			{"San Pablo Inmobiliaria Burgos\n - \nCaceres Patrimonio de la Humanidad","15-04-2016" }
			};

	public Partidos() {
	}

	public Partidos(String equipos, String fechaPartido) {

		this.equipos = equipos;
		this.fechaPartido = fechaPartido;
	}

	public String[] getPartidos() {
		String[] local = new String[datosPartidos.length];
		for (int i = 0; i < local.length; i++) {
			local[i] = datosPartidos[i][0];
		}
		return local;
	}

	public String getEquipos(int posicion) {
		String eq = datosPartidos[posicion][0];
		return eq;
	}

	public String getFecha(int posicion) {
		String fec = datosPartidos[posicion][1];
		return fec;
	}

}
