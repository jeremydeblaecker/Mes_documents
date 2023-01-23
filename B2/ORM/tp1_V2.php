<?php
use App\Employee; 
use App\Department; 
use App\Title; 
use App\Salary; 

public class Tp1 {

	/**
	 * Trouver les employées de sexe féminin classés par emp_no, limité aux 10 premiers résultats
	 */ 
	public function rqt1() {
		return Employee::where('gender', 'F')->orderBy('emp_no')->limit(10)->get(); 
	}
	
	/**
	 * Trouver tous les employés dont le prénom est 'Troy'.
	 */
	public function rqt2() {
		return Employee::where('first_name', 'Troy')->get();
	}
	
	/**
	 * 
	 * Trouver tous les employés de sexe masculin nés après le 31 janvier 1965 
	 * 
	 * */
	public function rqt3() {
		return Employee::where('gender', 'M')->where('birth_date', '>', '1965-01-31')->get();
	}
	
	
	/**
	 * 
	 * Combien y a t'il de départements 
	 * 
	 * */
	public function rqt4() {
		return Department::count();
	}
	
	/**
	 * 
	 *  Combien de personnes dont le prénom est 'Richard' sont des femmes = 94
	 * 
	 * */
	public function rqt5() {
		return Employee::where('first_name', 'Richard')->where('gender','F')->get()->count();  
	}
	
		
	/**
	 * 
	 * Combien y a t'il de titre différents d'employés = 7
	 * 
	 * */
	public function rqt6() {
		return Title::distinct('title')->count();
	}
	
	
	/**
	 * 
	 * Le salaire moyen de l'employé numéro 287323 toute période confondu 
	 * 
	 * */
	public function rqt7() {
		return Salary::where('emp_no', '287323')->avg('salary'); 
	}
	
	
	/**
	 * 
	 * Quel était le titre de Danny Rando le 12 janvier 1990  = Staff
	 * 
	 * */
	public function rqt8() {
		return Employee::select('title')->join('titles', 'employees.emp_no', 'titles.emp_no')
		->where('first_name', 'Danny')->where('last_name', 'Rando')
		->where('from_date', '<=', '1990-01-12')->where('to_date', '>=', '1990-01-12')->get(); 
	}
	
	/**
	 * 
	 * L'employé qui a eu le salaire maximum de tous les temps, et quel est le montant de ce salaire
	 * 
	 * */
	 public function rqt9() {
		return Salary::join('employees', 'employees.emp_no', '=', 'salaries.emp_no')->orderby('salary', 'desc')->first();	 
	}
	 
	 /**
	  * 
	  * Combien d'employés travaillaient dans le département 'Sales' le 1er Janvier 2000
	  * 
	  */
	  public function rqt10() {
		return Department::join('dept_emp', 'departments.dept_no', 'dept_emp.dept_no')
		->where('dept_name', 'Sales')
		->where('dept_emp.from_date', '<=', '2000-01-01')
		->where('dept_emp.to_date', '>=', '2000-01-01')->count();	  
	} 
	  
	  /**
	   * Qui est le manager de Martine Hambrick actuellement et quel est son titre
	   */  
	  public function rqt11() {
		return Employee::join('dept_emp as dp', 'employees.emp_no', 'dp.emp_no')
		->join('departments as d', 'd.dept_no', 'dp.dept_no')
		->join('dept_manager as dm', 'dm.dept_no', 'd.dept_no')
		->join('employees as e', 'e.emp_no', 'dm.emp_no')
		->join('titles as t', 'dm.emp_no', 't.emp_no')
		->where('employees.first_name', 'Martine')
		->where('employees.last_name', 'Hambrick')
		->whereDate('dm.to_date', '>=', now())->whereDate('t.to_date', '>=', now())->get();
	  }
}