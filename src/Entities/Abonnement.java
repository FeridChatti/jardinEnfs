/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 * @author karim
 */
public class Abonnement {


    private int id;


    private Date date;


    private String type;


    private String etat;


    private float montant;


    private Jardin jardin;


    private Remarque remarques;


    private Enfant enfant;


}
