package com.cprigus.services.god.drools;

import com.cprigus.services.god.drools.vo.UserTemp;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

/**
 *
 * @author User
 */
public class DroolRuleAgeImpl implements DroolRuleAge{
    public final UserTemp validarReglasAge(UserTemp user) {
        try {
            //Cargamos la base de reglas     
            final KnowledgeBase kbase = leerReglasAgeDRL();
            final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
            //Insertamos el objeto usuario a la sesion
            ksession.insert(user);
            //Disparamos las reglas de Drools
            ksession.fireAllRules();
        } catch (Exception t) {
            t.printStackTrace();
        }
        return user;
    }
 
    private static KnowledgeBase leerReglasAgeDRL() {
        final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("/com/rest/services/god/drools/config/rulesAgeGodWeb.drl"), ResourceType.DRL);
        if (kbuilder.hasErrors()) {
            for (KnowledgeBuilderError error : kbuilder.getErrors()) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Imposible crear knowledge con Order.drl");
        }
        final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
        return kbase;
    }
}
