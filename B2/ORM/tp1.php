<?php
use App\Employee; 
use App\Department; 
use App\Title; 
use App\Salary; 

public class Tp1 {

	/**
	 * Trouver les employées de sexe féminin classés par emp_no, limité aux 10 premiers résultats
	 */ 
	public rqt1() {
		return Employee::where('gender', 'F')->orderBy('emp_no')->limit(10)->get()
	}

	public rqt1() {
		return Employees::where('gender', 'F')->skip(0)->take(10)->orderBy('emp_no');
	}
	
	
	/**
	 * Trouver tous les employés dont le prénom est 'Troy'.
	 */
	public rqt2() {
		return = Employees::where('first_name', 'Troy')->get();
	}
	
	/**
	 * 
	 * Trouver tous les employés de sexe masculin nés après le 31 janvier 1965 
	 * 
	 * */
	public rqt3() {
		return Employees::where([['gender', '=', 'M'], ['birth_date', '>', '1965-01-31'])->get();
	}
	
	
	/**
	 * 
	 * Combien y a t'il de départements 
	 * 
	 * */
	public rqt4() {
		return Department::all()->count();
	}
	
	/**
	 * 
	 *  Combien de personnes dont le prénom est 'Richard' sont des femmes
	 * 
	 * */
	public rqt5() {
		return Employees::where([['first_name', 'Richard'], ['gender', 'F']]->get()->count();
	}
	
		
	/**
	 * 
	 * Combien y a t'il de titre différents d'employés 
	 * 
	 * */
	public rqt6() {
		return Title::distinct('title')->count();
	}
	
	
	/**
	 * 
	 * Le salaire moyen de l'employé numéro 287323 toute période confondu 
	 * 
	 * */
	public rqt7() {
		return Salary::where('emp_no', '287323')->avg('salary');
	}
	
	
	/**
	 * 
	 * Quel était le titre de Danny Rando le 12 janvier 1990 
	 * 
	 * */
	public rqt8() {
	
	}
	
	/**
	 * 
	 * L'employé qui a eu le salaire maximum de tous les temps, et quel est le montant de ce salaire
	 * 
	 * */
	 public rqt9() {
		return Employees::max('salary');

		 
	 }
	 
	 /**
	  * 
	  * Combien d'employés travaillaient dans le département 'Sales' le 1er Janvier 2000
	  * 
	  */
	  public rqt10() {
		  
	  } 
	  
	  /**
	   * Qui est le manager de Martine Hambrick actuellement et quel est son titre
	   */  
	  public rqt11() {
		  
	  }
}
