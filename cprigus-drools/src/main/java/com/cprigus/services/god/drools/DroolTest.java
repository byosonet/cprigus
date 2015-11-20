package com.cprigus.services.god.drools;

import com.cprigus.services.god.drools.vo.Empleado;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

public class DroolTest {
 
    public static final void main(String[] args) {
        try {
            //Cargamos la base de reglas     
            leerReglasXLS();
            System.out.println("\n");
            
            final KnowledgeBase kbase = leerReglasDRL();
            final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
 
            //Obtenemos los empleados
            List<Empleado> list = new ArrayList<Empleado>();
            Empleado empleado1 = new Empleado();
            empleado1.setEdad(12);
            empleado1.setNombre("Gustavo");
            ksession.insert(empleado1);
            
            list.add(empleado1);

            Empleado empleado2 = new Empleado();
            empleado2.setEdad(9);
            empleado2.setNombre("David");
            ksession.insert(empleado2);
            list.add(empleado2);
            
            Empleado empleado3 = new Empleado();
            empleado3.setEdad(27);
            empleado3.setNombre("Priscila");
            ksession.insert(empleado3);
            list.add(empleado3);
 
            //Disparamos las reglas de Drools
            ksession.fireAllRules();
            
            System.out.println("\n");
            for (Empleado e : list) {
                System.out.println("Empleado Nombre: " + e.getNombre());
                System.out.println("Empleado Edad: " + e.getEdad());
                System.out.println("Empleado Actividad: " + e.getActivity());
                System.out.println("\n");
            }

        } catch (Exception t) {
            t.printStackTrace();
           
        }
    }
 
    /**  Author: User
  Date: 08/10/2014 19:18:20

  Description: Explain for enclosing_method

  @see 
  @param*/
    private static KnowledgeBase leerReglasDRL() {
        /*DRL*/
        final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newFileResource("C:\\temporal\\god\\god-drools\\src\\main\\java\\com\\rest\\services\\god\\drools\\rule\\generate.drl"), ResourceType.DRL);
        if (kbuilder.hasErrors()) {
            for (KnowledgeBuilderError error : kbuilder.getErrors()) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Imposible crear knowledge con Order.drl");
        }
        final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
        /*DRL*/
    }
    
    
    private static void leerReglasXLS() {
        /*XLS*/
        InputStream is = null;
        try {
            is = new FileInputStream("C:\\temporal\\god\\god-drools\\src\\main\\java\\com\\rest\\services\\god\\drools\\rule\\rule.xls");
            SpreadsheetCompiler sc = new SpreadsheetCompiler();
            StringBuffer drl2 = new StringBuffer(sc.compile(is, InputType.XLS));
            System.out.println(drl2);
            BufferedWriter out = new BufferedWriter(new FileWriter("C:\\temporal\\god\\god-drools\\src\\main\\java\\com\\rest\\services\\god\\drools\\rule\\testRule.drl"));
            out.write(drl2.toString().replace("“", "\"").replace("”", "\""));
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        /*XLS*/
    }
    
}
