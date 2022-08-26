package model.service;

import model.entities.Alocação;
import model.entities.Fatura;
//////////// Classe Responsável em gerar a classe Fatura, ou seja, ela é entidade de serviço onde vai ser processada as regras de negócio//////////
/////////////                  /////////////////////////            ///////////////////             ////////////////                //////////////

public class GeradoraFatura {
private Double p_hora;
private ZumbiInstrumentos zumbi;
static final Double p_dia= 200.0;
//static final Double R$_diaFsemana=300.0;


public GeradoraFatura(Double p_hora, ZumbiInstrumentos zumbi) {
	super();
	this.p_hora = p_hora;
	this.zumbi = zumbi;
}

public ZumbiInstrumentos getZumbi() {
	return zumbi;
}

public Double getP_hora() {
	return p_hora;
}


public void setP_hora(Double p_hora) {
	this.p_hora = p_hora;
}


public static Double getR$Dianomal() {
	return p_dia;
}


//public static Double getR$Diafsemana() {
	//return R$_diaFsemana;
//}
public void fatura(Alocação alocação){
	
	long diff1 = alocação.getData_inicial().getTime();
	long diff2= alocação.getData_final().getTime();
	
	double p_basico;
	
	double hours =(double)(diff2-diff1)/3.600;
	
	
	
	if(hours<=20) {
		p_basico=Math.ceil(hours*p_hora);
	}
   else {
	   p_basico=Math.ceil(hours/24)*(p_dia);
		
	}
	double tax= zumbi.tax(p_basico);
	
	alocação.setFat(new Fatura(tax,p_basico));
	
}

}
